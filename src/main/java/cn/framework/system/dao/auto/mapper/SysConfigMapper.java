package cn.framework.system.dao.auto.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.framework.system.dao.auto.entity.SysConfigEntity;

/**
 * @author ：Administrator
 * @ClassName:：SysConfigMapper
 * @Description：系统参数配置表Dao
 * @date ：2020/04/27 05:18
 */
@Repository
public interface SysConfigMapper {
    /***
     * @Description 新增系统参数配置表
     * @Date 2020/04/27 05:18
     * @Param [params]
     * @return void
     **/
    void insertSysConfig(SysConfigEntity sys_config);

    /**
     * @return void
     * @Description 删除系统参数配置表
     * @Date 2020/04/27 05:18
     * @Param [id]
     **/
    void deleteSysConfig(Integer id);

    /**
     * @return void
     * @Description 更新系统参数配置表
     * @Date 2020/04/27 05:18
     * @Param [params]
     **/
    void updateSysConfig(SysConfigEntity sys_config);

    /**
     * @return java.util.Map<java.lang.Integer>
     * @Description 根据id查询系统参数配置表: 单条
     * @Date 2020/04/27 05:18
     * @Param [id]
     **/
    SysConfigEntity selectSysConfigById(Integer id);

    /**
     * @return java.util.List
     * @Description 根据条件查询系统参数配置表：多条
     * @Date 2020/04/27 05:18
     * @Param [params]
     **/
    List<SysConfigEntity> getSysConfigs(SysConfigEntity sys_config);

    /***
     * @Description 查询数量
     * @Date 2020/04/27 05:18
     * @Param [params]
     * @return java.lang.Integer
     **/
    Integer getSysConfigsCount(SysConfigEntity sys_config);
}