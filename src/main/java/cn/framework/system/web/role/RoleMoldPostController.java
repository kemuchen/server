package cn.framework.system.web.role;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.system.dao.auto.entity.SysRoleEntity;
import cn.framework.system.service.inter.role.BusinessRoleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RoleMoldPostController
 * @Desc
 * @Author 柯雷
 * @Date 2020-09-15 17:23
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api/system/role")
public class RoleMoldPostController {

    /** 角色业务处理service */
    @Autowired
    BusinessRoleServiceInterface businessRoleServiceInterface;

    /**
     * @Description: 保存角色信息
     * @Params: [sysRoleEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-15 17:58
     */
    @RequestMapping(value = "/role")
    public ApiResponseResultEntity saveRole(SysRoleEntity sysRoleEntity) throws AppException {
        return businessRoleServiceInterface.saveRole(sysRoleEntity);
    }
}
