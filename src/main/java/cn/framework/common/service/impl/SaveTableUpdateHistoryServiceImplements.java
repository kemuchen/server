package cn.framework.common.service.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.framework.common.dao.auto.entity.TableUpdateLogDetailEntity;
import cn.framework.common.dao.auto.entity.TableUpdateLogEntity;
import cn.framework.common.dao.auto.mapper.TableUpdateLogDetailMapper;
import cn.framework.common.dao.auto.mapper.TableUpdateLogMapper;
import cn.framework.common.dao.customize.mapper.CustomizeTableUpdateLogMapper;
import cn.framework.common.service.inter.SaveTableUpdateHistoryServiceInter;
import cn.framework.util.sys.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.framework.common.entity.BaseEntity;
import cn.framework.util.sys.SysUtil;

/**
 * @ClassName SaveTableUpdateHistoryService
 * @Desc： 保存Table修改历史
 * @Author 柯雷
 * @Date 2020/3/5 9:39
 * @Version 1.0
 */
@Service
public class SaveTableUpdateHistoryServiceImplements extends SaveTableUpdateHistoryServiceInter {

    /**
     * 日志输出对象
     */
    Logger logger = LoggerFactory.getLogger(SaveTableUpdateHistoryServiceImplements.class);

    /**
     * 表修改记录基础操作DAO
     */
    @Autowired
    TableUpdateLogMapper tableUpdateLogMapper;

    /**
     * 表修改记录详细信息操作DAO
     */
    @Autowired
    TableUpdateLogDetailMapper tableUpdateLogDetailMapper;

    /**
     * 自定义查询表和字段信息
     */
    @Autowired
    CustomizeTableUpdateLogMapper customizeTableUpdateLogMapper;

    /**
     * @Description: 保存修改历史
     * @Params: [baseEntity]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/3/5 11:21
     */
    public void saveTableUpdateHistory(BaseEntity baseEntity) throws Exception {
        logger.info("【SaveTableUpdateHistoryService.saveTableUpdateHistory】保存修改记录：" + baseEntity);
        String fullEntityName = getEntityFullName(baseEntity);
        String entityName = getEntityName(baseEntity);
        String methodName = getMethodName(entityName);
        String tableName = getTableName(entityName);
        String fullMapperName = getFullMapperName(fullEntityName);
        String mapperName = getMapperName(entityName);

        try {
            // Entity类
            Class<?> entityClass = Class.forName(fullEntityName);
            BaseEntity oldEntity = (BaseEntity) entityClass.newInstance();

            // Mapper类
            Class<?> mapperClass = Class.forName(fullMapperName);
            Object mapper = SpringUtil.getBean(mapperName.substring(0, 1).toLowerCase() + mapperName.substring(1));

            // 获取修改记录的id
            Method method = baseEntity.getClass().getMethod("getId");
            Integer id = (Integer) method.invoke(baseEntity, new Object[]{});

            // 获取修改记录的方法以及修改前信息
            Method selectMethod = mapperClass.getMethod(methodName, Integer.class);
            oldEntity = (BaseEntity) selectMethod.invoke(mapper, new Object[]{id});

            // 保存修改记录
            Integer updateLogId = saveTableUpdateLog(oldEntity, tableName, id);

            // 保存修改记录明细
            saveTableUpdateLogDetail(baseEntity, oldEntity, updateLogId, tableName);
        } catch (Exception e) {
            logger.error("【SaveTableUpdateHistoryService.saveTableUpdateHistory】保存修改记录出错：" + e);
            throw e;
        }
    }

    /**
     * @Description: 保存修改记录
     * @Params: [baseEntity, tableName, id]
     * @return: java.lang.Integer
     * @Author: 柯雷
     * @Date: 2020/3/5 11:59
     */
    public Integer saveTableUpdateLog(BaseEntity baseEntity, String tableName, Integer id) {
        logger.info("【SaveTableUpdateHistoryService.saveTableUpdateLog】保存表修改记录日志");
        TableUpdateLogEntity tableUpdateLogEntity = new TableUpdateLogEntity();
        tableUpdateLogEntity.setTable_name(tableName);
        tableUpdateLogEntity.setTable_name_desc(this.getTableComment(tableName));
        tableUpdateLogEntity.setUpdate_table_id(id);
        tableUpdateLogEntity.setCreate_user(baseEntity.getModify_user());
        tableUpdateLogEntity.setModify_user(baseEntity.getModify_user());
        tableUpdateLogMapper.insertTableUpdateLog(tableUpdateLogEntity);
        return tableUpdateLogEntity.getId();
    }

    /**
     * @Description: 保存修改记录明细
     * @Params: [newEntity, oldEntity, logId]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/3/5 12:04
     */
    public void saveTableUpdateLogDetail(BaseEntity newEntity, BaseEntity oldEntity, Integer logId, String tableName) throws Exception {
        logger.info("【SaveTableUpdateHistoryService.saveTableUpdateLogDetail】保存表修改记录详细信息");
        try {
            Map<String, Object> oldEntityMap = SysUtil.entityToMap(oldEntity);
            Map<String, Object> newEntityMap = SysUtil.entityToMap(newEntity);

            Set<String> keySet = oldEntityMap.keySet();
            TableUpdateLogDetailEntity tableUpdateLogDetailEntity = new TableUpdateLogDetailEntity();
            tableUpdateLogDetailEntity.setTable_update_log_id(logId);
            tableUpdateLogDetailEntity.setCreate_user(newEntity.getModify_user());
            tableUpdateLogDetailEntity.setModify_user(newEntity.getModify_user());
            tableUpdateLogDetailEntity.setIs_dict("0");

            boolean isUpdate = false;
            for (String key : keySet) {
                if (!"create_user".equals(key) && !"create_time".equals(key) && !"modify_time".equals(key)
                        && !"modify_user".equals(key)) {
                    if (SysUtil.isEmpty(oldEntityMap.get(key))) {
                        // 原值为空
                        if (!SysUtil.isEmpty(newEntityMap.get(key)) && !"null".equals(newEntityMap.get(key))) {
                            // 新值不为空
                            isUpdate = true;
                        }
                    } else if (!SysUtil.isEmpty(newEntityMap.get(key)) && !oldEntityMap.get(key).equals(newEntityMap.get(key)) && !"null".equals(newEntityMap.get(key))) {
                        // 新值不为空且原值和新值不相等
                        isUpdate = true;
                    }
                }
                if (isUpdate) {
                    tableUpdateLogDetailEntity.setField(key);
                    tableUpdateLogDetailEntity.setField_name(this.getColumnComment(tableName, key));
                    tableUpdateLogDetailEntity.setOld_value(oldEntityMap.get(key) + "");
                    tableUpdateLogDetailEntity.setNew_value(newEntityMap.get(key) + "");
                    tableUpdateLogDetailEntity.setId(null);
                    tableUpdateLogDetailMapper.insertTableUpdateLogDetail(tableUpdateLogDetailEntity);
                }
                isUpdate = false;
            }
        } catch (Exception e) {
            logger.error("【SaveTableUpdateHistoryService.saveTableUpdateLogDetail】保存详细修改记录出错:" + e);
            throw e;
        }
    }

    /**
     * @Description: 查询表注释
     * @Params: [table_name]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-04-21 10:01
     */
    public String getTableComment(String table_name) {
        logger.info("【SaveTableUpdateHistoryService.getTableComment】获取表注释信息：" + table_name);
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("table_name", table_name);
            params.put("table_schema", "xpms_dev");
            return customizeTableUpdateLogMapper.getTableComment(params);
        } catch (Exception e) {
            logger.error("【SaveTableUpdateHistoryService.getTableComment】获取表注释信息出错：" + e);
            return "";
        }
    }

    /**
     * @Description: 获取表字段注释信息
     * @Params: [table_name, column_name]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-04-21 10:14
     */
    public String getColumnComment(String table_name, String column_name) {
        logger.info("【SaveTableUpdateHistoryService.getColumnComment】获取表字段注释信息：" + table_name);
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("table_name", table_name);
            params.put("column_name", column_name);
            params.put("table_schema", "xpms_dev");
            return customizeTableUpdateLogMapper.getColumnComment(params);
        } catch (Exception e) {
            logger.error("【SaveTableUpdateHistoryService.getColumnComment】获取表字段注释信息出错：" + e);
            return "";
        }
    }
}
