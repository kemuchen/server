package cn.framework.common.dao.customize.mapper;


import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @ClassName CustomTableUpdateLogMapper
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-21 10:05
 * @Version 1.0
 */
@Repository
public interface CustomizeTableUpdateLogMapper {

    /**
     * @Description: 获取表注释
     * @Params: [table_name]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-04-21 10:05
     */
    String getTableComment(Map<String, Object> params);

    /**
     * @Description: 获取字段注释
     * @Params: [table_name, column_name]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-04-21 10:06
     */
    String getColumnComment(Map<String, Object> params);
}
