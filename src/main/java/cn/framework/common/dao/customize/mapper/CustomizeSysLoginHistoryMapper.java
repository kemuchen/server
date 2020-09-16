package cn.framework.common.dao.customize.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName CustomizeSysLoginHistoryMapper
 * @Desc
 * @Author 柯雷
 * @Date 2020-05-12 9:39
 * @Version 1.0
 */
@Repository
public interface CustomizeSysLoginHistoryMapper {

    /**
     * @Description: 查询用户最近一条登录信息的token
     * @Params: [userid]
     * @return: cn.framework.system.dao.auto.entity.SysLoginHistoryEntity
     * @Author: 柯雷
     * @Date: 2020-05-12 9:40
     */
    String getLatestLoginHistory(@Param("userid") Integer userid);
}
