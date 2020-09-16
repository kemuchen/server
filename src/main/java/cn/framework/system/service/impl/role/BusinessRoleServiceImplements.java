package cn.framework.system.service.impl.role;

import cn.framework.Constants;
import cn.framework.annotation.PageQuery;
import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.security.exception.codes.SysErrorCode;
import cn.framework.system.dao.auto.entity.SysRole2rightEntity;
import cn.framework.system.dao.auto.entity.SysRoleEntity;
import cn.framework.system.dao.auto.entity.SysUser2roleEntity;
import cn.framework.system.dao.customize.vo.RoleRightVo;
import cn.framework.system.dao.customize.vo.UserRoleVo;
import cn.framework.system.service.inter.role.BusinessRoleServiceInterface;
import cn.framework.system.service.inter.role.SysRole2RightServiceInterface;
import cn.framework.system.service.inter.role.SysRoleServiceInterface;
import cn.framework.system.service.inter.role.SysUser2RoleServiceInterface;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BusinessRoleServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020-09-15 17:25
 * @Version 1.0
 */
@Service
public class BusinessRoleServiceImplements implements BusinessRoleServiceInterface {

    /** 日志输出对象 */
    Logger logger = LoggerFactory.getLogger(BusinessRoleServiceImplements.class);

    /** 角色信息处理service */
    @Autowired
    SysRoleServiceInterface sysRoleServiceInterface;

    /** 角色权限处理service */
    @Autowired
    SysRole2RightServiceInterface sysRole2RightServiceInterface;

    /** 用户角色处理service */
    @Autowired
    SysUser2RoleServiceInterface sysUser2RoleServiceInterface;

    /**
     * @Description: 获取所有角色信息
     * @Params: []
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 18:10
     */
    @Override
    public ApiResponseResultEntity getAllRoles() throws AppException {
        return sysRoleServiceInterface.getAllRoles();
    }

    /**
     * @Description: 获取用户角色信息
     * @Params: [user_id]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-16 8:09
     */
    @Override
    public ApiResponseResultEntity getUserRoles(Integer user_id) throws AppException {
        return sysUser2RoleServiceInterface.getUserRoles(user_id);
    }

    /**
     * @Description: 获取角色权限信息
     * @Params: [role_id]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-16 8:10
     */
    @Override
    public ApiResponseResultEntity getRoleRights(Integer role_id) throws AppException {
        return sysRole2RightServiceInterface.getRoleRights(role_id);
    }

    /**
     * @Description: 保存用户角色信息
     * @Params: [userRoleVo]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-16 8:11
     */
    @Override
    public ApiResponseResultEntity saveUserRoles(UserRoleVo userRoleVo) throws AppException {
        try {
            // 删除用户角色信息
            if (StringUtils.isNotEmpty(userRoleVo.getRemoveIds())) {
                String[] removeIdArr = userRoleVo.getRemoveIds().split(",");
                for (String rmId : removeIdArr) {
                    SysUser2roleEntity sysUser2roleEntity = new SysUser2roleEntity();
                    sysUser2roleEntity.setId(Integer.parseInt(rmId));
                    sysUser2roleEntity.setValid(Constants.NO);
                    sysUser2roleEntity.setModify_user(userRoleVo.getUserId());
                    sysUser2RoleServiceInterface.saveSysUser2Role(sysUser2roleEntity);
                }
            }
            // 添加用户角色信息
            if (StringUtils.isNotEmpty(userRoleVo.getAddRoleIds())) {
                String[] roleIdArr = userRoleVo.getAddRoleIds().split(",");
                for (String roleId : roleIdArr) {
                    SysUser2roleEntity sysUser2roleEntity = new SysUser2roleEntity();
                    sysUser2roleEntity.setUserid(userRoleVo.getAddUserId());
                    sysUser2roleEntity.setRoleid(Integer.parseInt(roleId));
                    sysUser2roleEntity.setValid(Constants.YES);
                    sysUser2roleEntity.setCreate_user(userRoleVo.getUserId());
                    sysUser2roleEntity.setModify_user(userRoleVo.getUserId());
                    sysUser2RoleServiceInterface.saveSysUser2Role(sysUser2roleEntity);
                }
            }
            return new ApiResponseResultEntity(SysErrorCode.SUCCESS.getError_code(), "保存成功");
        } catch (Exception e) {
            logger.error("【BusinessRoleServiceImplements.saveUserRoles】保存用户角色出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存角色权限信息
     * @Params: [roleRightVo]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-16 8:16
     */
    @Override
    public ApiResponseResultEntity saveRoleRights(RoleRightVo roleRightVo) throws AppException {
        try {
            // 删除角色权限
            if (StringUtils.isNotEmpty(roleRightVo.getRemoveIds())) {
                String[] removeIdArr = roleRightVo.getRemoveIds().split(",");
                for (String rmId : removeIdArr) {
                    SysRole2rightEntity sysRole2rightEntity = new SysRole2rightEntity();
                    sysRole2rightEntity.setId(Integer.parseInt(rmId));
                    sysRole2rightEntity.setValid(Constants.NO);
                    sysRole2rightEntity.setModify_user(roleRightVo.getUserId());
                    sysRole2RightServiceInterface.saveRole2Right(sysRole2rightEntity);
                }
            }
            // 新增角色权限
            if (StringUtils.isNotEmpty(roleRightVo.getAddRightIds())) {
                String[] rightIdArr = roleRightVo.getAddRightIds().split(",");
                for (String rightId : rightIdArr) {
                    SysRole2rightEntity sysRole2rightEntity = new SysRole2rightEntity();
                    sysRole2rightEntity.setRoleid(roleRightVo.getAddRoleId());
                    sysRole2rightEntity.setRightid(Integer.parseInt(rightId));
                    sysRole2rightEntity.setValid(Constants.YES);
                    sysRole2rightEntity.setCreate_user(roleRightVo.getUserId());
                    sysRole2rightEntity.setModify_user(roleRightVo.getUserId());
                    sysRole2RightServiceInterface.saveRole2Right(sysRole2rightEntity);
                }
            }
            return new ApiResponseResultEntity(SysErrorCode.SUCCESS.getError_code(), "保存成功");
        } catch (Exception e) {
            logger.error("【BusinessRoleServiceImplements.saveRoleRights】保存角色权限信息出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存角色
     * @Params: [sysRoleEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-16 8:24
     */
    @Override
    public ApiResponseResultEntity saveRole(SysRoleEntity sysRoleEntity) throws AppException {
        return sysRoleServiceInterface.saveRole(sysRoleEntity);
    }

    /**
     * @Description: 删除角色信息
     * @Params: [sysRoleEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 18:15
     */
    @Override
    public ApiResponseResultEntity deleteRole(SysRoleEntity sysRoleEntity) throws AppException {
        return sysRoleServiceInterface.deleteRole(sysRoleEntity.getId());
    }

    /**
     * @Description: 分页查询角色信息
     * @Params: [sysRoleEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 18:14
     */
    @PageQuery
    @Override
    public ApiResponseResultEntity queryRoles(SysRoleEntity sysRoleEntity) throws AppException {
        return sysRoleServiceInterface.getAllRoles(sysRoleEntity);
    }
}
