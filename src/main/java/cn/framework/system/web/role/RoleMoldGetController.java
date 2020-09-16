package cn.framework.system.web.role;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.system.dao.auto.entity.SysRoleEntity;
import cn.framework.system.service.inter.role.BusinessRoleServiceInterface;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RoleMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020-09-15 17:21
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api/system/role")
public class RoleMoldGetController {

    /** 角色管理业务处理 */
    @Autowired
    BusinessRoleServiceInterface businessRoleServiceInterface;

    /**
     * @Description: 获取所有角色
     * @Params: []
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 17:37
     */
    @ApiOperation("1.获取所有角色")
    @ApiImplicitParams({})
    @GetMapping("roles")
    public ApiResponseResultEntity getAllRoles() throws AppException {
        return businessRoleServiceInterface.getAllRoles();
    }

    /**
     * @Description: 获取用户角色组
     * @Params: [user_id]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 17:38
     */
    @ApiOperation("2.获取用户角色组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("roles/{user_id}")
    public ApiResponseResultEntity getAllRoles(@PathVariable(value = "user_id") Integer user_id) throws AppException {
        return businessRoleServiceInterface.getUserRoles(user_id);
    }

    /**
     * @Description: 获取角色权限
     * @Params: [role_id]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 17:39
     */
    @ApiOperation("3.获取角色权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role_id", value = "角色id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("roleRights/{role_id}")
    public ApiResponseResultEntity getRoleRights(@PathVariable(value = "role_id") Integer role_id) throws AppException {
        return businessRoleServiceInterface.getRoleRights(role_id);
    }

    /** 
     * @Description: 分页查询
     * @Params: [sysRoleEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 18:01
     */ 
    @RequestMapping(value = "/rolePage")
    public ApiResponseResultEntity queryRoles(SysRoleEntity sysRoleEntity) throws AppException {
        return businessRoleServiceInterface.queryRoles(sysRoleEntity);
    }
}
