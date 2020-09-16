package cn.framework.system.web.role;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.system.dao.auto.entity.SysRoleEntity;
import cn.framework.system.service.inter.role.BusinessRoleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RoleMoldDeleteController
 * @Desc
 * @Author 柯雷
 * @Date 2020-09-15 17:24
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api/system/role")
public class RoleMoldDeleteController {

    /** 角色业务处理service */
    @Autowired
    BusinessRoleServiceInterface businessRoleServiceInterface;

    /**
     * @Description: 删除角色信息
     * @Params: [sysRoleEntity]
     * @return: ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 18:00
     */
    @DeleteMapping(value = "/role")
    public ApiResponseResultEntity deleteRole(SysRoleEntity sysRoleEntity) throws AppException {
        return businessRoleServiceInterface.deleteRole(sysRoleEntity);
    }
}
