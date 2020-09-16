package cn.framework.common.service.inter;

import cn.framework.common.entity.BaseEntity;
import cn.framework.util.sys.SysUtil;

/**
 * @ClassName SaveTableUpdateHistoryServiceInter
 * @Desc
 * @Author 柯雷
 * @Date 2020/3/5 11:13
 * @Version 1.0
 */
public abstract class SaveTableUpdateHistoryServiceInter {

    /**
     * @Description: 获取类名全称（含包）
     * @Params: [baseEntity]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/3/5 11:37
     */
    public String getEntityFullName(BaseEntity baseEntity) {
        return baseEntity.getClass().getName();
    }

    /**
     * @Description: 获取Entity名称
     * @Params: [baseEntity]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/3/5 11:15
     */
    public String getEntityName(BaseEntity baseEntity) {
        return baseEntity.getClass().getSimpleName();
    }

    /**
     * @Description: 获取方法名
     * @Params: [entityName]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/3/5 11:17
     */
    public String getMethodName(String entityName) {
        // 去掉结尾的Entity
        String tableName = entityName.substring(0, entityName.length() - 6);
        return "select" + tableName + "ById";
    }

    /**
     * @Description: 获取Mapper名称
     * @Params: [entityName]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/3/5 11:20
     */
    public String getMapperName(String entityName) {
        // 去掉结尾的Entity
        String tableName = entityName.substring(0, entityName.length() - 6);
        return tableName + "Mapper";
    }

    /**
     * @Description: 获取完整的Mapper名称
     * @Params: [fullEntityName]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/3/5 11:46
     */
    public String getFullMapperName(String fullEntityName) {
        String entityPackage = fullEntityName.substring(0, fullEntityName.lastIndexOf('.'));
        String mapperPackage = entityPackage.replaceAll("entity", "mapper");
        String entityName = fullEntityName.substring(fullEntityName.lastIndexOf('.') + 1);
        return mapperPackage + "." + this.getMapperName(entityName);
    }

    /**
     * @Description: 获取表名
     * @Params: [entityName]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/3/5 11:56
     */
    public String getTableName(String entityName) {
        String tableName = entityName.substring(0, entityName.length() - 6);
        tableName = tableName.substring(0, 1).toLowerCase() + tableName.substring(1);
        return SysUtil.humpToLine(tableName, true);
    }
}
