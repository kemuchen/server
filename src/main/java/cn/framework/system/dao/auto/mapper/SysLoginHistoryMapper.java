package cn.framework.system.dao.auto.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.framework.system.dao.auto.entity.SysLoginHistoryEntity;

/**
 * @author ：Administrator
 * @ClassName:：SysLoginHistoryMapper
 * @Description：登录历史信息表Dao
 * @date ：2020/05/11 09:59
 */
@Repository
public interface SysLoginHistoryMapper {
    /***
     * @Description 新增登录历史信息表
     * @Date 2020/05/11 09:59
     * @Param [params]
     * @return void
     **/
    void insertSysLoginHistory(SysLoginHistoryEntity sys_login_history);

    /**
     * @return void
     * @Description 删除登录历史信息表
     * @Date 2020/05/11 09:59
     * @Param [id]
     **/
    void deleteSysLoginHistory(Integer id);

    /**
     * @return void
     * @Description 更新登录历史信息表
     * @Date 2020/05/11 09:59
     * @Param [params]
     **/
    void updateSysLoginHistory(SysLoginHistoryEntity sys_login_history);

    /**
     * @return java.util.Map<java.lang.Integer>
     * @Description 根据id查询登录历史信息表: 单条
     * @Date 2020/05/11 09:59
     * @Param [id]
     **/
    SysLoginHistoryEntity selectSysLoginHistoryById(Integer id);

    /**
     * @return java.util.List
     * @Description 根据条件查询登录历史信息表：多条
     * @Date 2020/05/11 09:59
     * @Param [params]
     **/
    List<SysLoginHistoryEntity> getSysLoginHistorys(SysLoginHistoryEntity sys_login_history);

    /***
     * @Description 查询数量
     * @Date 2020/05/11 09:59
     * @Param [params]
     * @return java.lang.Integer
     **/
    Integer getSysLoginHistorysCount(SysLoginHistoryEntity sys_login_history);
}