package cn.framework.common.dao.auto.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.framework.common.dao.auto.entity.SysDynamicDictConfigEntity;

/**
 * @author ：Administrator
 * @ClassName:：SysDynamicDictConfigMapper
 * @Description：动态字典配置表Dao
 * @date ：2020/04/29 12:18
 */
@Repository
public interface SysDynamicDictConfigMapper {
    /***
     * @Description 新增动态字典配置表
     * @Date 2020/04/29 12:18
     * @Param [params]
     * @return void
     **/
    void insertSysDynamicDictConfig(SysDynamicDictConfigEntity sys_dynamic_dict_config);

    /**
     * @return void
     * @Description 删除动态字典配置表
     * @Date 2020/04/29 12:18
     * @Param [id]
     **/
    void deleteSysDynamicDictConfig(Integer id);

    /**
     * @return void
     * @Description 更新动态字典配置表
     * @Date 2020/04/29 12:18
     * @Param [params]
     **/
    void updateSysDynamicDictConfig(SysDynamicDictConfigEntity sys_dynamic_dict_config);

    /**
     * @return java.util.Map<java.lang.Integer>
     * @Description 根据id查询动态字典配置表: 单条
     * @Date 2020/04/29 12:18
     * @Param [id]
     **/
    SysDynamicDictConfigEntity selectSysDynamicDictConfigById(Integer id);

    /**
     * @return java.util.List
     * @Description 根据条件查询动态字典配置表：多条
     * @Date 2020/04/29 12:18
     * @Param [params]
     **/
    List<SysDynamicDictConfigEntity> getSysDynamicDictConfigs(SysDynamicDictConfigEntity sys_dynamic_dict_config);

    /***
     * @Description 查询数量
     * @Date 2020/04/29 12:18
     * @Param [params]
     * @return java.lang.Integer
     **/
    Integer getSysDynamicDictConfigsCount(SysDynamicDictConfigEntity sys_dynamic_dict_config);
}