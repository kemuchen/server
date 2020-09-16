package cn.framework.system.dao.auto.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.framework.system.dao.auto.entity.SysUserEntity;

/**
 * @author ：Administrator
 * @ClassName:：SysUserMapper
 * @Description：用户信息表Dao
 * @date ：2020/05/12 05:32
 */
@Repository
public interface SysUserMapper {
    /***
     * @Description 新增用户信息表
     * @Date 2020/05/12 05:32
     * @Param [params]
     * @return void
     **/
    void insertSysUser(SysUserEntity sys_user);

    /**
     * @return void
     * @Description 删除用户信息表
     * @Date 2020/05/12 05:32
     * @Param [id]
     **/
    void deleteSysUser(Integer id);

    /**
     * @return void
     * @Description 更新用户信息表
     * @Date 2020/05/12 05:32
     * @Param [params]
     **/
    void updateSysUser(SysUserEntity sys_user);

    /**
     * @return java.util.Map<java.lang.Integer>
     * @Description 根据id查询用户信息表: 单条
     * @Date 2020/05/12 05:32
     * @Param [id]
     **/
    SysUserEntity selectSysUserById(Integer id);

    /**
     * @return java.util.List
     * @Description 根据条件查询用户信息表：多条
     * @Date 2020/05/12 05:32
     * @Param [params]
     **/
    List<SysUserEntity> getSysUsers(SysUserEntity sys_user);

    /***
     * @Description 查询数量
     * @Date 2020/05/12 05:32
     * @Param [params]
     * @return java.lang.Integer
     **/
    Integer getSysUsersCount(SysUserEntity sys_user);
}