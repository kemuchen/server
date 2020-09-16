package cn.framework.system.service.inter.role;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.system.dao.auto.entity.SysRole2rightEntity;

/**
 * @ClassName SysRole2RightServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020-09-15 18:07
 * @Version 1.0
 */
public interface SysRole2RightServiceInterface {

    /**
     * @Description: 获取角色权限信息
     * @Params: [role_id]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-16 8:11
     */
    ApiResponseResultEntity getRoleRights(Integer role_id) throws AppException;
    
    /** 
     * @Description: 保存角色权限信息
     * @Params: [sysRole2rightEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-16 8:23
     */ 
    ApiResponseResultEntity saveRole2Right(SysRole2rightEntity sysRole2rightEntity) throws AppException;
}
