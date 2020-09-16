package cn.framework.system.dao.customize.mapper;

import cn.framework.system.dao.auto.entity.SysUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomizeSysUserMapper
 * @Desc
 * @Author 柯雷
 * @Date 2020-09-15 17:32
 * @Version 1.0
 */
@Repository
public interface CustomizeSysUserMapper {

    /**
     * @Description: 获取用户信息（含角色）
     * @Params: [sysUserEntity]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: 柯雷
     * @Date: 2020-09-15 17:32
     */
    List<Map<String, Object>> getUsersWithRole(SysUserEntity sysUserEntity);
}
