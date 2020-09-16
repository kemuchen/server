package cn.framework.system.dao.auto.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.framework.system.dao.auto.entity.SysRoleEntity;

/**
 * @author ：Administrator
 * @ClassName:：SysRoleMapper
 * @Description：角色信息表Dao
 * @date ：2020/04/27 05:18
 */
@Repository
public interface SysRoleMapper {
    /***
     * @Description 新增角色信息表
     * @Date 2020/04/27 05:18
     * @Param [params]
     * @return void
     **/
    void insertSysRole(SysRoleEntity sys_role);

    /**
     * @return void
     * @Description 删除角色信息表
     * @Date 2020/04/27 05:18
     * @Param [id]
     **/
    void deleteSysRole(Integer id);

    /**
     * @return void
     * @Description 更新角色信息表
     * @Date 2020/04/27 05:18
     * @Param [params]
     **/
    void updateSysRole(SysRoleEntity sys_role);

    /**
     * @return java.util.Map<java.lang.Integer>
     * @Description 根据id查询角色信息表: 单条
     * @Date 2020/04/27 05:18
     * @Param [id]
     **/
    SysRoleEntity selectSysRoleById(Integer id);

    /**
     * @return java.util.List
     * @Description 根据条件查询角色信息表：多条
     * @Date 2020/04/27 05:18
     * @Param [params]
     **/
    List<SysRoleEntity> getSysRoles(SysRoleEntity sys_role);

    /***
     * @Description 查询数量
     * @Date 2020/04/27 05:18
     * @Param [params]
     * @return java.lang.Integer
     **/
    Integer getSysRolesCount(SysRoleEntity sys_role);
}