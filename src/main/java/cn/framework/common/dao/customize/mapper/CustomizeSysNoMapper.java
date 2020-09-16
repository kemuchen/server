package cn.framework.common.dao.customize.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName CustomSysNoMapper
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-21 14:22
 * @Version 1.0
 */
@Repository
public interface CustomizeSysNoMapper {

    /**
     * @Description: 生成订单号
     * @Params: []
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-04-21 14:23
     */
    String generateNo(@Param("rule") String rule);
}
