package cn.framework.system.web.user;

import cn.framework.annotation.PageQuery;
import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.system.dao.auto.entity.SysUserEntity;
import cn.framework.system.service.inter.user.BusinessUserServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserMoldGetController
 * @Desc 用户管理controller
 * @Author 柯雷
 * @Date 2020-04-27 17:20
 * @Version 1.0
 */
@Api(tags = "用户信息查询接口")
@RestController
@RequestMapping("/api/system/")
public class UserMoldGetController {

    /**
     * 用户业务操作service
     */
    @Autowired
    BusinessUserServiceInterface businessUserService;

    /**
     * @Description: 查询用户列表
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 17:37
     */
    @ApiOperation(("1.查询用户列表"))
    @GetMapping("users")
    @PageQuery
    public ApiResponseResultEntity users(SysUserEntity sysUserEntity) throws AppException {
        return businessUserService.getUsersPage(sysUserEntity);
    }

    /**
     * @Description: 获取用户列表(含角色信息)
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 17:27
     */
    @ApiOperation("2.获取用户列表(含角色信息)")
    @ApiImplicitParams({})
    @GetMapping("usersWithRole")
    public ApiResponseResultEntity getUsers(SysUserEntity sysUserEntity) throws AppException {
        if (null == sysUserEntity) {
            sysUserEntity = new SysUserEntity();
        }
        return businessUserService.getUsersWithRole(sysUserEntity);
    }
}
