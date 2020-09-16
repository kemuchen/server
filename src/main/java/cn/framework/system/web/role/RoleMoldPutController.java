package cn.framework.system.web.role;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.system.dao.customize.vo.RoleRightVo;
import cn.framework.system.dao.customize.vo.UserRoleVo;
import cn.framework.system.service.inter.role.BusinessRoleServiceInterface;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName RoleMoldPutController
 * @Desc
 * @Author 柯雷
 * @Date 2020-09-15 17:23
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api/system/role")
public class RoleMoldPutController {

    /** 角色业务处理service */
    @Autowired
    BusinessRoleServiceInterface businessRoleServiceInterface;

    /**
     * @Description: 保存用户角色信息
     * @Params: [removeIds, addRoleIds, addUserId, userId]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 17:44
     */
    @ApiOperation("1.保存用户角色信息")
    @PutMapping("userRoles")
    public ApiResponseResultEntity saveUserRoles(@RequestBody UserRoleVo userRoleVo) throws AppException {
        return businessRoleServiceInterface.saveUserRoles(userRoleVo);
    }

    /**
     * @Description: 保存角色权限信息
     * @Params: [removeIds, addRightIds, addRoleId, userId]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 17:44
     */
    @ApiOperation("2.保存角色权限信息")
    @PutMapping("roleRights")
    public ApiResponseResultEntity saveRoleRights(@RequestBody RoleRightVo roleRightVo) throws AppException {
        return businessRoleServiceInterface.saveRoleRights(roleRightVo);
    }
}
