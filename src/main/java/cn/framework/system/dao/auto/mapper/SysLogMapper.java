package cn.framework.system.dao.auto.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.framework.system.dao.auto.entity.SysLogEntity;

/**
 * @author ：Administrator
 * @ClassName:：SysLogMapper
 * @Description：系统日志表Dao
 * @date ：2020/04/27 05:18
 */
@Repository
public interface SysLogMapper {
    /***
     * @Description 新增系统日志表
     * @Date 2020/04/27 05:18
     * @Param [params]
     * @return void
     **/
    void insertSysLog(SysLogEntity sys_log);

    /**
     * @return void
     * @Description 删除系统日志表
     * @Date 2020/04/27 05:18
     * @Param [id]
     **/
    void deleteSysLog(Integer id);

    /**
     * @return void
     * @Description 更新系统日志表
     * @Date 2020/04/27 05:18
     * @Param [params]
     **/
    void updateSysLog(SysLogEntity sys_log);

    /**
     * @return java.util.Map<java.lang.Integer>
     * @Description 根据id查询系统日志表: 单条
     * @Date 2020/04/27 05:18
     * @Param [id]
     **/
    SysLogEntity selectSysLogById(Integer id);

    /**
     * @return java.util.List
     * @Description 根据条件查询系统日志表：多条
     * @Date 2020/04/27 05:18
     * @Param [params]
     **/
    List<SysLogEntity> getSysLogs(SysLogEntity sys_log);

    /***
     * @Description 查询数量
     * @Date 2020/04/27 05:18
     * @Param [params]
     * @return java.lang.Integer
     **/
    Integer getSysLogsCount(SysLogEntity sys_log);
}