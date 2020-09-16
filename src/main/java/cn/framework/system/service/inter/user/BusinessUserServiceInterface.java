package cn.framework.system.service.inter.user;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.common.pojo.BasePojo;
import cn.framework.security.exception.AppException;
import cn.framework.system.dao.auto.entity.SysUserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName BusinessUserServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-27 18:22
 * @Version 1.0
 */
public interface BusinessUserServiceInterface {

    /**
     * @Description: 用户登录
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 9:35
     */
    ApiResponseResultEntity login(SysUserEntity sysUserEntity, HttpServletRequest request) throws AppException;


    /**
     * @Description: 根据登录id查询用户信息
     * @Params: [loginId]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 11:18
     */
    ApiResponseResultEntity getUserByLoginId(String loginId) throws AppException;

    /**
     * @Description: 保存用户信息
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 9:36
     */
    ApiResponseResultEntity saveUser(SysUserEntity sysUserEntity, BasePojo basePojo) throws AppException;

    /**
     * @Description: 删除用户信息
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 9:36
     */
    ApiResponseResultEntity deleteUser(List<Integer> ids, BasePojo basePojo) throws AppException;

    /**
     * @Description: 获取用户
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 9:36
     */
    ApiResponseResultEntity getUsersPage(SysUserEntity sysUserEntity) throws AppException;

    /**
     * @Description: 获取用户列表(含角色信息)
     * @Params: []
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 17:28
     */
    ApiResponseResultEntity getUsersWithRole(SysUserEntity sysUserEntity) throws AppException;

    /**
     * @Description: 保存用户信息(含角色信息)
     * @Params: [sysUserEntity, roleIds]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 17:42
     */
    ApiResponseResultEntity saveUsersWithRole(SysUserEntity sysUserEntity, String roleIds) throws AppException;

    /**
     * @Description: 重置密码
     * @Params: [userId, oldPassword, password]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-16 9:01
     */
    ApiResponseResultEntity setPassword(Integer userId, String oldPassword, String password) throws AppException;
}
