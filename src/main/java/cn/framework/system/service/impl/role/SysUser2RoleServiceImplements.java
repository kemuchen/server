package cn.framework.system.service.impl.role;

import cn.framework.Constants;
import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.security.exception.codes.SysErrorCode;
import cn.framework.system.dao.auto.entity.SysRoleEntity;
import cn.framework.system.dao.auto.entity.SysUser2roleEntity;
import cn.framework.system.dao.auto.mapper.SysRoleMapper;
import cn.framework.system.dao.auto.mapper.SysUser2roleMapper;
import cn.framework.system.service.inter.role.SysUser2RoleServiceInterface;
import cn.framework.util.sys.SysUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SysUser2RoleServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020-09-15 17:25
 * @Version 1.0
 */
@Service
public class SysUser2RoleServiceImplements implements SysUser2RoleServiceInterface {

    /** 日志输出对象 */
    Logger logger = LoggerFactory.getLogger(SysUser2RoleServiceImplements.class);

    /** 用户角色CURD */
    @Autowired
    SysUser2roleMapper sysUser2roleMapper;

    /** 角色信息CURD */
    @Autowired
    SysRoleMapper sysRoleMapper;

    /**
     * @Description: 获取用户角色信息列表
     * @Params: [user_id]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-16 8:27
     */
    @Override
    public ApiResponseResultEntity getUserRoles(Integer user_id) throws AppException {
        try {
            Map<String, Object> result = new HashMap<>();
            SysRoleEntity sysRoleEntity = new SysRoleEntity();
            sysRoleEntity.setValid(Constants.YES);
            List<SysRoleEntity> roles = sysRoleMapper.getSysRoles(sysRoleEntity);
            result.put("all", roles);
            SysUser2roleEntity sysUser2roleEntity = new SysUser2roleEntity();
            sysUser2roleEntity.setUserid(user_id);
            sysUser2roleEntity.setValid(Constants.YES);
            List<SysUser2roleEntity> list = sysUser2roleMapper.getSysUser2roles(sysUser2roleEntity);
            result.put("me", list);
            return new ApiResponseResultEntity(SysErrorCode.SUCCESS.getError_code(), result);
        } catch (Exception e) {
            logger.error("【SysUser2RoleServiceImplements.getUserRoles】获取用户角色信息列表出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存用户角色信息
     * @Params: [sysUser2roleEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-09-16 8:29
     */
    @Override
    public ApiResponseResultEntity saveSysUser2Role(SysUser2roleEntity sysUser2roleEntity) throws AppException {
        try {
            if (SysUtil.isEmpty(sysUser2roleEntity.getId())) {
                sysUser2roleMapper.insertSysUser2role(sysUser2roleEntity);
            } else {
                sysUser2roleMapper.updateSysUser2role(sysUser2roleEntity);
            }
            return new ApiResponseResultEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【SysUser2RoleServiceImplements.saveSysUser2Role】保存用户角色信息出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
