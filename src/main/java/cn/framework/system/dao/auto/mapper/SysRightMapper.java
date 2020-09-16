package cn.framework.system.dao.auto.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.framework.system.dao.auto.entity.SysRightEntity;

/**
 * @author ：Administrator
 * @ClassName:：SysRightMapper
 * @Description：权限信息表Dao
 * @date ：2020/04/27 05:18
 */
@Repository
public interface SysRightMapper {
    /***
     * @Description 新增权限信息表
     * @Date 2020/04/27 05:18
     * @Param [params]
     * @return void
     **/
    void insertSysRight(SysRightEntity sys_right);

    /**
     * @return void
     * @Description 删除权限信息表
     * @Date 2020/04/27 05:18
     * @Param [id]
     **/
    void deleteSysRight(Integer id);

    /**
     * @return void
     * @Description 更新权限信息表
     * @Date 2020/04/27 05:18
     * @Param [params]
     **/
    void updateSysRight(SysRightEntity sys_right);

    /**
     * @return java.util.Map<java.lang.Integer>
     * @Description 根据id查询权限信息表: 单条
     * @Date 2020/04/27 05:18
     * @Param [id]
     **/
    SysRightEntity selectSysRightById(Integer id);

    /**
     * @return java.util.List
     * @Description 根据条件查询权限信息表：多条
     * @Date 2020/04/27 05:18
     * @Param [params]
     **/
    List<SysRightEntity> getSysRights(SysRightEntity sys_right);

    /***
     * @Description 查询数量
     * @Date 2020/04/27 05:18
     * @Param [params]
     * @return java.lang.Integer
     **/
    Integer getSysRightsCount(SysRightEntity sys_right);
}