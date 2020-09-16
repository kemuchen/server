package cn.framework.system.dao.auto.mapper;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cn.framework.system.dao.auto.entity.SysLoginHistoryEntity;

/**
 * @author ：Administrator
 * @ClassName:：SysLoginHistoryMapper
 * @Description：登录历史信息表Dao
 * @date ：2020/04/16 11:32
 */
@Repository
public interface CustomSysLoginHistoryMapper {

    @Update({"UPDATE sys_login_history SET is_logout = '1', valid = '0' WHERE userid = #{userid}"})
    void upLogin(SysLoginHistoryEntity entity);
}