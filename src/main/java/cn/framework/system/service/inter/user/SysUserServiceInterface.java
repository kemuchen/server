package cn.framework.system.service.inter.user;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.system.dao.auto.entity.SysUserEntity;

/**
 * @ClassName SysUserServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-27 17:21
 * @Version 1.0
 */
public interface SysUserServiceInterface {
    /**
     * @Description: 保存用户信息
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-27 17:27
     */
    ApiResponseResultEntity saveUser(SysUserEntity sysUserEntity) throws AppException;

    /**
     * @Description: 根据id查询用户信息
     * @Params: [id]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-27 17:27
     */
    ApiResponseResultEntity getUserById(Integer id) throws AppException;

    /**
     * @Description: 查询用户
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 11:22
     */
    ApiResponseResultEntity getUsers(SysUserEntity sysUserEntity) throws AppException;

    /**
     * @Description: 查询用户数量
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-09 10:59
     */
    ApiResponseResultEntity getUserCount(SysUserEntity sysUserEntity) throws AppException;

    /**
     * @Description: 获取用户列表(含角色信息)
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 17:30
     */
    ApiResponseResultEntity getUsersWithRole(SysUserEntity sysUserEntity) throws AppException;
}
