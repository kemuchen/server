package cn.framework.system.dao.auto.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.framework.system.dao.auto.entity.SysMessageEntity;

/**
 * @author ：Administrator
 * @ClassName:：SysMessageMapper
 * @Description：系统消息表Dao
 * @date ：2020/05/09 10:41
 */
@Repository
public interface SysMessageMapper {
    /***
     * @Description 新增系统消息表
     * @Date 2020/05/09 10:41
     * @Param [params]
     * @return void
     **/
    void insertSysMessage(SysMessageEntity sys_message);

    /**
     * @return void
     * @Description 删除系统消息表
     * @Date 2020/05/09 10:41
     * @Param [id]
     **/
    void deleteSysMessage(Integer id);

    /**
     * @return void
     * @Description 更新系统消息表
     * @Date 2020/05/09 10:41
     * @Param [params]
     **/
    void updateSysMessage(SysMessageEntity sys_message);

    /**
     * @return java.util.Map<java.lang.Integer>
     * @Description 根据id查询系统消息表: 单条
     * @Date 2020/05/09 10:41
     * @Param [id]
     **/
    SysMessageEntity selectSysMessageById(Integer id);

    /**
     * @return java.util.List
     * @Description 根据条件查询系统消息表：多条
     * @Date 2020/05/09 10:41
     * @Param [params]
     **/
    List<SysMessageEntity> getSysMessages(SysMessageEntity sys_message);

    /***
     * @Description 查询数量
     * @Date 2020/05/09 10:41
     * @Param [params]
     * @return java.lang.Integer
     **/
    Integer getSysMessagesCount(SysMessageEntity sys_message);
}