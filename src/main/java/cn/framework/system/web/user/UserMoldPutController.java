package cn.framework.system.web.user;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.common.pojo.BasePojo;
import cn.framework.security.exception.AppException;
import cn.framework.system.dao.auto.entity.SysUserEntity;
import cn.framework.system.service.inter.user.BusinessUserServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserMoldPutController
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-28 9:47
 * @Version 1.0
 */
@Api(tags = "用户信息更新接口")
@RestController
@RequestMapping("/api/system/")
public class UserMoldPutController {

    /**
     * 用户业务操作service
     */
    @Autowired
    BusinessUserServiceInterface businessUserService;

    /**
     * @Description: 新增用户信息
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-05 11:26
     */
    @ApiOperation("1.新增用户信息")
    @PutMapping("user")
    public ApiResponseResultEntity user(@RequestBody SysUserEntity sysUserEntity, BasePojo basePojo) throws AppException {
        return businessUserService.saveUser(sysUserEntity, basePojo);
    }

    /**
     * @Description: 新增用户-带角色
     * @Params: [sysUserEntity, roleIds]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 17:41
     */
    @ApiOperation("2.保存用户信息(含角色信息)")
    @PutMapping("userWithRole")
    public ApiResponseResultEntity saveUsers(SysUserEntity sysUserEntity, String roleIds) throws AppException {
        return businessUserService.saveUsersWithRole(sysUserEntity, roleIds);
    }

    /**
     * @Description: 重置密码
     * @Params: [userId, oldPassword, password]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-16 9:00
     */
    @RequestMapping(value = "user/resetPassword")
    public ApiResponseResultEntity setPassword(Integer userId, String oldPassword, String password) throws AppException {
        return businessUserService.setPassword(userId, oldPassword, password);
    }
}
