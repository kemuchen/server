package cn.framework.system.dao.auto.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import cn.framework.system.dao.auto.entity.SysRole2rightEntity;

/**
* @author ：Administrator
* @ClassName:：SysRole2rightMapper
* @Description：角色权限表Dao
* @date ：2020/09/16 10:11
*/
@Repository
public interface SysRole2rightMapper {
    /***
    * @Description 新增角色权限表
    * @Date 2020/09/16 10:11
    * @Param [params]
    * @return void
    **/
    void insertSysRole2right(SysRole2rightEntity sys_role2right);

    /**
    * @Description 删除角色权限表
    * @Date 2020/09/16 10:11
    * @Param [id]
    * @return void
    **/
    void deleteSysRole2right(Integer id);

    /**
    * @Description 更新角色权限表
    * @Date 2020/09/16 10:11
    * @Param [params]
    * @return void
    **/
    void updateSysRole2right(SysRole2rightEntity sys_role2right);

    /**
    * @Description 根据id查询角色权限表: 单条
    * @Date 2020/09/16 10:11
    * @Param [id]
    * @return java.util.Map
    * <java.lang.Integer>
    **/
    SysRole2rightEntity selectSysRole2rightById(Integer id);

    /**
    * @Description 根据条件查询角色权限表：多条
    * @Date 2020/09/16 10:11
    * @Param [params]
    * @return java.util.List
    **/
    List <SysRole2rightEntity> getSysRole2rights(SysRole2rightEntity sys_role2right);

    /***
    * @Description 查询数量
    * @Date 2020/09/16 10:11
    * @Param [params]
    * @return java.lang.Integer
    **/
    Integer getSysRole2rightsCount(SysRole2rightEntity sys_role2right);
}