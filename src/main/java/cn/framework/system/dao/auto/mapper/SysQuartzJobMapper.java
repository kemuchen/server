package cn.framework.system.dao.auto.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.framework.system.dao.auto.entity.SysQuartzJobEntity;

/**
 * @author ：Administrator
 * @ClassName:：SysQuartzJobMapper
 * @Description：酒店定时器定时器配置Dao
 * @date ：2020/04/28 03:10
 */
@Repository
public interface SysQuartzJobMapper {
    /***
     * @Description 新增酒店定时器定时器配置
     * @Date 2020/04/28 03:10
     * @Param [params]
     * @return void
     **/
    void insertSysQuartzJob(SysQuartzJobEntity sys_quartz_job);

    /**
     * @return void
     * @Description 删除酒店定时器定时器配置
     * @Date 2020/04/28 03:10
     * @Param [id]
     **/
    void deleteSysQuartzJob(Integer id);

    /**
     * @return void
     * @Description 更新酒店定时器定时器配置
     * @Date 2020/04/28 03:10
     * @Param [params]
     **/
    void updateSysQuartzJob(SysQuartzJobEntity sys_quartz_job);

    /**
     * @return java.util.Map<java.lang.Integer>
     * @Description 根据id查询酒店定时器定时器配置: 单条
     * @Date 2020/04/28 03:10
     * @Param [id]
     **/
    SysQuartzJobEntity selectSysQuartzJobById(Integer id);

    /**
     * @return java.util.List
     * @Description 根据条件查询酒店定时器定时器配置：多条
     * @Date 2020/04/28 03:10
     * @Param [params]
     **/
    List<SysQuartzJobEntity> getSysQuartzJobs(SysQuartzJobEntity sys_quartz_job);

    /***
     * @Description 查询数量
     * @Date 2020/04/28 03:10
     * @Param [params]
     * @return java.lang.Integer
     **/
    Integer getSysQuartzJobsCount(SysQuartzJobEntity sys_quartz_job);
}