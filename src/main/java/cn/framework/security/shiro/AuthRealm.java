package cn.framework.security.shiro;

import cn.framework.security.exception.AppException;
import cn.framework.security.exception.codes.LoginErrorCode;
import cn.framework.security.exception.codes.SysErrorCode;
import cn.framework.system.dao.auto.entity.SysRightEntity;
import cn.framework.system.dao.auto.entity.SysUserEntity;
import cn.framework.system.dao.customize.vo.SysRightVo;
import cn.framework.system.service.inter.right.BusinessRightServiceInterface;
import cn.framework.system.service.inter.user.BusinessUserServiceInterface;
import cn.framework.util.sys.SysUtil;
import lombok.SneakyThrows;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：柯雷
 * @ClassName:：AuthRealm
 * @Description： shiro框架验证
 * @date ：2018年9月5日 下午4:46:58
 */
public class AuthRealm extends AuthorizingRealm {

    /**
     * @Description 日志打印对象
     */
    private static final Logger logger = LoggerFactory.getLogger(AuthRealm.class);

    /**
     * @Description 用户业务处理类
     */
    @Autowired
    BusinessUserServiceInterface businessUserService;

    /**
     * @Description 菜单业务处理类
     */
    @Autowired
    BusinessRightServiceInterface businessRightService;

    /**
     * <p>Title：doGetAuthorizationInfo</p>
     * <p>Description：  权限认证</p>
     *
     * @param principals
     * @return
     * @see AuthorizingRealm#doGetAuthorizationInfo(PrincipalCollection)
     */
    @SneakyThrows
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /** 用户信息，包括权限 */
        SysUserEntity userEntity = (SysUserEntity) principals.fromRealm(this.getClass().getName()).iterator().next();
        try {
            /** 获取用户权限列表 */
            List<SysRightVo> rightList = (List<SysRightVo>) businessRightService.getRightsByUserId(userEntity.getId()).getData();
            List<String> permissions = new ArrayList<>();
            /** 遍历菜单列表，获取权限url */
            for (SysRightVo right : rightList) {
                if (!SysUtil.isEmpty(right.getRight().getRight_url())) {
                    permissions.add(right.getRight().getRight_url());
                }
                // 子菜单
                List<SysRightEntity> subRights = right.getSub_sys_right();
                if (!SysUtil.isEmpty(subRights) && subRights.size() > 0) {
                    for (SysRightEntity child : subRights) {
                        if (!SysUtil.isEmpty(child.getRight_url())) {
                            permissions.add(child.getRight_url());
                        }
                    }
                }
            }
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addStringPermissions(permissions);//将权限放入shiro中.
            return info;
        } catch (Exception e) {
            logger.error("【AuthRealm.doGetAuthorizationInfo】权限校验出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * <p>Title：doGetAuthenticationInfo</p>
     * <p>Description：登录认证 </p>
     *
     * @param token
     * @return
     * @throws AuthenticationException
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;//获取用户输入的token
        String login_id = usernamePasswordToken.getUsername();
        logger.info("【AuthRealm.doGetAuthenticationInfo】用户登录认证：username=" + login_id);
        try {
            try {
                List<SysUserEntity> userEntities = (List<SysUserEntity>) businessUserService.getUserByLoginId(login_id).getData();
                if (SysUtil.isEmpty(userEntities) || userEntities.size() == 0) {
                    throw new AppException(LoginErrorCode.LOGIN_ID_ERROR);
                }
                SysUserEntity user = userEntities.get(0);
                return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
            } catch (AppException e) {
                logger.error("【AuthRealm.doGetAuthenticationInfo】登录校验出错：" + e);
                throw new UnknownAccountException(e.getError_tip());
            }
        } catch (UnknownAccountException e) {
            throw new UnknownAccountException(e.getMessage());
        }
    }

}
