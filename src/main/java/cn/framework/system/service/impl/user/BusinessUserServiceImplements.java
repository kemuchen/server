package cn.framework.system.service.impl.user;

import cn.framework.Constants;
import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.common.pojo.BasePojo;
import cn.framework.common.pojo.PageQueryPojo;
import cn.framework.security.exception.AppException;
import cn.framework.security.exception.codes.LoginErrorCode;
import cn.framework.security.exception.codes.SysErrorCode;
import cn.framework.system.dao.auto.entity.SysLoginHistoryEntity;
import cn.framework.system.dao.auto.entity.SysUser2roleEntity;
import cn.framework.system.dao.auto.entity.SysUserEntity;
import cn.framework.system.dao.customize.vo.LoginResultVo;
import cn.framework.system.service.inter.role.SysUser2RoleServiceInterface;
import cn.framework.system.service.inter.user.BusinessUserServiceInterface;
import cn.framework.system.service.inter.user.SysLoginHistoryServiceInterface;
import cn.framework.system.service.inter.user.SysUserServiceInterface;
import cn.framework.util.sys.SysUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName BusinessUserServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-27 18:22
 * @Version 1.0
 */
@Service
public class BusinessUserServiceImplements implements BusinessUserServiceInterface {

    /**
     * 日志输出对象
     */
    Logger logger = LoggerFactory.getLogger(BusinessUserServiceImplements.class);

    /**
     * 用户信息基础操作service
     */
    @Autowired
    SysUserServiceInterface sysUserService;

    /**
     * 登录历史信息基础操作service
     */
    @Autowired
    SysLoginHistoryServiceInterface sysLoginHistoryService;

    /** 用户角色基础操作service */
    @Autowired
    SysUser2RoleServiceInterface sysUser2RoleServiceInterface;

    /**
     * @Description: 用户登录
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 9:38
     */
    @Override
    public ApiResponseResultEntity login(SysUserEntity sysUserEntity, HttpServletRequest request) throws AppException {
        try {
            return this.doLogin(sysUserEntity, request);
        } catch (AppException e) {
            logger.error("【BusinessUserServiceImplements.login】登录异常： " + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessUserServiceImplements.login】登录失败： " + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }


    /**
     * @Description: 根据登录id查询用户信息
     * @Params: [loginId]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 11:18
     */
    @Override
    public ApiResponseResultEntity getUserByLoginId(String loginId) throws AppException {
        try {
            SysUserEntity sysUserEntity = new SysUserEntity();
            sysUserEntity.setLoginid(loginId);
            sysUserEntity.setValid(Constants.YES);
            return sysUserService.getUsers(sysUserEntity);
        } catch (AppException e) {
            logger.error("【BusinessUserServiceImplements.getUserByLoginId】根据登录id查询用户信息出错： " + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessUserServiceImplements.getUserByLoginId】根据登录id查询用户信息出错： " + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description:
     * @Params: []
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 10:17
     */
    private ApiResponseResultEntity doLogin(SysUserEntity sysUserEntity, HttpServletRequest request) throws AppException {
        try {
            ApiResponseResultEntity apiResponseResult = new ApiResponseResultEntity(SysErrorCode.SUCCESS);
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(sysUserEntity.getLoginid(), DigestUtils.md5Hex(sysUserEntity.getPassword()));
            Subject subject = SecurityUtils.getSubject();
            subject.login(usernamePasswordToken);   // 完成登录

            // 保存登录历史
            LoginResultVo loginResultVo = new LoginResultVo();
            loginResultVo.setToken(this.saveLoginHistory((SysUserEntity) subject.getPrincipal(), request));
            loginResultVo.setUser((SysUserEntity) subject.getPrincipal());
            apiResponseResult.setData(loginResultVo);

            return apiResponseResult;
        } catch (UnknownAccountException uae) {
            throw new AppException(LoginErrorCode.LOGIN_ID_ERROR);
        } catch (IncorrectCredentialsException ice) {
            throw new AppException(LoginErrorCode.LOGIN_ID_ERROR);
        } catch (LockedAccountException lae) {
            throw new AppException(LoginErrorCode.USERNAME_LOCKED);
        } catch (AuthenticationException ae) {
            throw new AppException(LoginErrorCode.LOGIN_ID_ERROR);
        } catch (Exception e) {
            logger.error("【BusinessUserServiceImplements.doLogin】登录失败： " + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存登录历史信息
     * @Params: [sysUserEntity, request]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 11:05
     */
    private String saveLoginHistory(SysUserEntity sysUserEntity, HttpServletRequest request) throws AppException {
        try {
            SysLoginHistoryEntity logEntity = new SysLoginHistoryEntity();
            logEntity.setUserid(sysUserEntity.getId());
            logEntity.setLogin_ip(SysUtil.getIpAddr(request));
            logEntity.setCreate_user(sysUserEntity.getId());
            logEntity.setModify_user(sysUserEntity.getId());

            String token = UUID.randomUUID().toString();
            logEntity.setToken(token);
            logEntity.setLogin_result("登录成功!");
            sysLoginHistoryService.saveLoginHistory(logEntity);

            return token;
        } catch (AppException e) {
            logger.error("【BusinessUserServiceImplements.doLogin】保存登录历史信息出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessUserServiceImplements.doLogin】保存登录历史信息出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存用户信息
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-05 11:46
     */
    @Override
    public ApiResponseResultEntity saveUser(SysUserEntity sysUserEntity, BasePojo basePojo) throws AppException {
        try {
            sysUserEntity.setModify_user(basePojo.getModify_user());
            return sysUserService.saveUser(sysUserEntity);
        } catch (AppException e) {
            logger.error("【BusinessUserServiceImplements.saveUser】保存用户信息出错： " + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessUserServiceImplements.saveUser】保存用户信息出错： " + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除用户信息
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-05 11:48
     */
    @Override
    public ApiResponseResultEntity deleteUser(List<Integer> ids, BasePojo basePojo) throws AppException {
        try {
            // 遍历用户并设置用户有效标志为无效
            SysUserEntity sysUserEntity = new SysUserEntity();
            sysUserEntity.setValid(Constants.NO);
            for (Integer id : ids) {
                sysUserEntity.setId(id);
                sysUserEntity.setModify_user(basePojo.getModify_user());
                sysUserEntity.setModify_time(new Date());
                sysUserService.saveUser(sysUserEntity);
            }
            ApiResponseResultEntity apiResponseResultEntity = new ApiResponseResultEntity();
            apiResponseResultEntity.setCode(SysErrorCode.SUCCESS.getError_code());
            apiResponseResultEntity.setMessage("删除用户成功");
            return apiResponseResultEntity;
        } catch (AppException e) {
            logger.error("【BusinessUserServiceImplements.deleteUser】删除用户信息出错： " + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessUserServiceImplements.deleteUser】删除用户信息出错： " + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 分页查询用户信息
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 17:35
     */
    @Override
    public ApiResponseResultEntity getUsersPage(SysUserEntity sysUserEntity) throws AppException {
        try {
            sysUserEntity.setModify_user(null);
            ApiResponseResultEntity apiResponseResultEntity = new ApiResponseResultEntity(SysErrorCode.SUCCESS);
            PageQueryPojo<SysUserEntity> sysUserEntityPageQueryPojo = new PageQueryPojo<>(
                    (Integer) sysUserService.getUserCount(sysUserEntity).getData(),
                    (List<SysUserEntity>) sysUserService.getUsers(sysUserEntity).getData());
            apiResponseResultEntity.setData(sysUserEntityPageQueryPojo);
            return apiResponseResultEntity;
        } catch (AppException e) {
            logger.error("【BusinessUserServiceImplements.getUsersPage】分页查询用户信息出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessUserServiceImplements.getUsersPage】分页查询用户信息出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取用户列表(含角色信息)
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 17:28
     */
    @Override
    public ApiResponseResultEntity getUsersWithRole(SysUserEntity sysUserEntity) throws AppException {
        try {
            return sysUserService.getUsersWithRole(sysUserEntity);
        } catch (Exception e) {
            logger.error("【BusinessUserServiceImplements.getUsersWithRole】获取用户列表出错："
                    + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存用户-带角色
     * @Params: [sysUserEntity, roleIds]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-16 8:46
     */
    @Override
    public ApiResponseResultEntity saveUsersWithRole(SysUserEntity sysUserEntity, String roleIds) throws AppException {
        try {
            sysUserEntity.setPassword(DigestUtils.md5Hex("a"));
            sysUserService.saveUser(sysUserEntity);
            if (StringUtils.isNotEmpty(roleIds)) {
                String[] roleIdArr = roleIds.split(",");
                for (String roleId : roleIdArr) {
                    SysUser2roleEntity sysUser2roleEntity = new SysUser2roleEntity();
                    sysUser2roleEntity.setUserid(sysUserEntity.getId());
                    sysUser2roleEntity.setRoleid(Integer.parseInt(roleId));
                    sysUser2roleEntity.setValid(Constants.YES);
                    sysUser2roleEntity.setCreate_user(sysUserEntity.getCreate_user());
                    sysUser2roleEntity.setModify_user(sysUserEntity.getModify_user());
                    sysUser2RoleServiceInterface.saveSysUser2Role(sysUser2roleEntity);
                }
            }
            return new ApiResponseResultEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【BusinessUserServiceImplements.saveUsersWithRole】保存用户-带角色出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 重置密码
     * @Params: [userId, oldPassword, password]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-16 9:01
     */
    @Override
    public ApiResponseResultEntity setPassword(Integer userId, String oldPassword, String password) throws AppException {
        try {
            if (null == userId || StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(password)) {
                return new ApiResponseResultEntity(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "入参不全");
            }
            SysUserEntity user = (SysUserEntity) sysUserService.getUserById(userId).getData();
            if (null == user) {
                return new ApiResponseResultEntity(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "未查询到该用户");
            }
            String oldPwMd5 = DigestUtils.md5Hex(oldPassword);
            if (!oldPwMd5.equals(user.getPassword())) {
                return new ApiResponseResultEntity(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "原密码不正确");
            }

            String pwMd5 = DigestUtils.md5Hex(password);
            SysUserEntity sysUserEntity = new SysUserEntity();
            sysUserEntity.setId(userId);
            sysUserEntity.setPassword(pwMd5);
            sysUserEntity.setModify_user(userId);
            sysUserService.saveUser(sysUserEntity);

            return new ApiResponseResultEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【BusinessUserServiceImplements.setPassword】设置用户密码出错:" + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessUserServiceImplements.setPassword】设置用户密码出错:" + e.getMessage());
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
