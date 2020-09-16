package cn.framework.util.mapperutil.java;

import cn.framework.util.date.DateUtil;
import cn.framework.util.sys.SysUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @ClassName MapperUtil
 * @Desc
 * @Author 柯雷
 * @Date 2019/7/10 15:34
 * @Version 1.0
 */
public class MapperUtil {

    static Logger logger = LoggerFactory.getLogger(MapperUtil.class);

    /***
     * @Description: 生成文件入口
     * @Params: [database, tableName]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/1/14 9:13
     */
    public static void generateFile(String database, String tableName) throws Exception {
        logger.info("【MapperUtil.generateFile】开始生成文件");
        List<Map<String, Object>> tables;
        if (SysUtil.isEmpty(tableName)) {
            tables = getTables(database);
        } else {
            tables = getTableByTableName(tableName, database);
        }

        // 生成java文件
        logger.info("【MapperUtil.generateFile】生成java dao文件：" + tables);
        printJavaDaoFile(tables);

        // 生成Entity文件
        if ("entity".equals(Config.MYBATIS_PARAMETER_TYPE)) {
            logger.info("【MapperUtil.generateFile】生成java dao文件：" + tables);
            printJavaEntityFile(tables);
        }

        // 生成mapper文件
        logger.info("【MapperUtil.generateFile】生成java mapper文件：" + tables);
        printMapperFile(tables);
    }

    /**
     * @Description: 获取数据库表信息
     * @Params: []
     * @return: java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @Author: 柯雷
     * @Date: 2019/7/10 15:36
     */
    private static List<Map<String, Object>> getTables(String databaseName) throws SQLException {
        List<Map<String, Object>> tables = new ArrayList<>();
        Connection connection = null;

        try {
            connection = DataBaseUtil.getConnection();
            // 查询表信息（表明，注释）
            String sql = "select table_name, table_comment from information_schema.tables where table_schema = ?";
            PreparedStatement tablestatement = connection.prepareStatement(sql);
            tablestatement.setString(1, databaseName);

            ResultSet tableresultSet = tablestatement.executeQuery();
            Map<String, Object> table = null;
            while (tableresultSet.next()) {
                table = new HashMap<>();
                table.put("table_name", tableresultSet.getString("table_name"));
                table.put("table_name_upper", underline2Camel(tableresultSet.getString("table_name"), true));
                table.put("table_comment", tableresultSet.getString("table_comment"));

                // 查询表字段信息
                sql = "select column_name, column_type, column_comment from information_schema.columns where table_name = ? and table_schema = ?";
                PreparedStatement columnstatement = connection.prepareStatement(sql);
                columnstatement.setString(1, tableresultSet.getString("table_name"));
                columnstatement.setString(2, databaseName);
                ResultSet columnresultset = columnstatement.executeQuery();

                List<Map<String, Object>> columns = new ArrayList<>();
                Map<String, Object> column = null;
                while (columnresultset.next()) {
                    column = new HashMap<>();
                    column.put("column_name", columnresultset.getString("column_name"));
                    column.put("column_type", columnresultset.getString("column_type"));
                    column.put("column_comment", columnresultset.getString("column_comment"));
                    columns.add(column);
                }

                DataBaseUtil.close(null, columnstatement, columnresultset);
                Map<String, Object>[] columnsArray = new Map[columns.size()];
                columns.toArray(columnsArray);
                table.put("columns", columnsArray);
                table.put("startRow", "${startRow}");
                table.put("pageSize", "${pageSize}");
                table.put("id", "#{id}");
                tables.add(table);
            }

            DataBaseUtil.close(null, tablestatement, tableresultSet);
        } catch (Exception e) {
            logger.error("查询表信息失败：" + e);
        } finally {
            DataBaseUtil.close(connection, null, null);
        }
        return tables;
    }

    /**
     * @Description: 获取数据库表信息
     * @Params: []
     * @return: java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @Author: 柯雷
     * @Date: 2019/7/10 15:36
     */
    private static List<Map<String, Object>> getTableByTableName(String tableName, String database) throws SQLException {
        List<Map<String, Object>> tables = new ArrayList<>();
        Connection connection = null;

        try {
            connection = DataBaseUtil.getConnection();
            // 查询表信息（表明，注释）
            String sql = "select table_name, table_comment from information_schema.tables where instr(table_name, ?) = 1 and table_schema = ?";
            PreparedStatement tablestatement = connection.prepareStatement(sql);
            tablestatement.setString(1, tableName);
            tablestatement.setString(2, database);

            ResultSet tableresultSet = tablestatement.executeQuery();
            Map<String, Object> table = null;
            while (tableresultSet.next()) {
                table = new HashMap<>();
                table.put("table_name", tableresultSet.getString("table_name"));
                table.put("table_name_upper", underline2Camel(tableresultSet.getString("table_name"), true));
                table.put("table_comment", tableresultSet.getString("table_comment"));

                // 查询表字段信息
                sql = "select column_name, column_type, column_comment from information_schema.columns where table_name=? and table_schema=?";
                PreparedStatement columnstatement = connection.prepareStatement(sql);
                columnstatement.setString(1, tableresultSet.getString("table_name"));
                columnstatement.setString(2, database);
                ResultSet columnresultset = columnstatement.executeQuery();

                List<Map<String, Object>> columns = new ArrayList<>();
                Map<String, Object> column = null;
                while (columnresultset.next()) {
                    column = new HashMap<>();
                    column.put("column_name", columnresultset.getString("column_name"));
                    column.put("column_type", columnresultset.getString("column_type"));
                    column.put("column_comment", columnresultset.getString("column_comment"));
                    columns.add(column);
                }

                DataBaseUtil.close(null, columnstatement, columnresultset);
                Map<String, Object>[] columnsArray = new Map[columns.size()];
                columns.toArray(columnsArray);
                table.put("columns", columnsArray);
                table.put("startRow", "${startRow}");
                table.put("pageSize", "${pageSize}");
                table.put("id", "#{id}");
                tables.add(table);
            }

            DataBaseUtil.close(null, tablestatement, tableresultSet);
        } catch (Exception e) {
            logger.error("查询表信息失败：" + e);
        } finally {
            DataBaseUtil.close(connection, null, null);
        }
        return tables;
    }

    private static Map<String, Object> formatTable(Map<String, Object> table) {
        // 设置java dao package
        table.put("dao_package", Config.DAO_PACKAGE_PATH);

        // 设置java dao package
        table.put("entity_package", Config.ENTITY_PACKAGE_PATH);

        // 设置时间
        table.put("currentTime", DateUtil.formatDate(new Date(), "yyyy/MM/dd hh:mm"));

        // 设置作者
        table.put("author", Config.AUTHOR);

        // 构造字段
        Map<String, Object>[] columns = (Map<String, Object>[]) table.get("columns");
        for (Map<String, Object> column : columns) {
            column.put("column_name_upper", column.get("column_name").toString().substring(0, 1).toUpperCase() + column.get("column_name").toString().substring(1));

            String column_type = column.get("column_type").toString();
            if (column_type.contains("(")) {
                column_type = column_type.substring(0, column_type.indexOf("("));
            }
            switch (column_type) {
                case "int":
                    column.put("column_java_type", "Integer");
                    break;
                case "double":
                    column.put("column_java_type", "Double");
                    break;
                case "decimal":
                    column.put("column_java_type", "BigDecimal");
                    break;
                case "bigint":
                    column.put("column_java_type", "long");
                    break;
                case "date":
                case "datetime":
                    column.put("column_java_type", "Date");
                    break;
                default:
                    column.put("column_java_type", "String");
            }
        }
        return table;
    }

    /***
     * @Description: 生成java dao文件
     * @Params: [tables]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/1/14 9:13
     */
    private static void printJavaDaoFile(List<Map<String, Object>> tables) {
        for (int i = 0; i < tables.size(); i++) {
            printFile(tables.get(i), Config.JAVA_FILE_NAME, "Mapper.java", Config.CREATE_DAO_FILE_PATH);
        }
    }

    /***
     * @Description: 生成mapper文件
     * @Params: [tables]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/1/14 9:13
     */
    private static void printMapperFile(List<Map<String, Object>> tables) {
        for (int i = 0; i < tables.size(); i++) {
            printFile(tables.get(i), Config.MAPPER_FILE_NAME, "Mapper.xml", Config.CREATE_MAPPER_FILE_PATH);
        }
    }

    /**
     * @Description: 生成Entity文件
     * @Params: [tables]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/1/14 9:21
     */
    private static void printJavaEntityFile(List<Map<String, Object>> tables) {
        for (int i = 0; i < tables.size(); i++) {
            printFile(tables.get(i), Config.ENTITY_FILE_NAME, "Entity.java", Config.CREATE_ENTITY_FILE_PATH);
        }
    }

    /***
     * @Description: 生成文件
     * @Params: [root, templateFileName, createFileName]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/1/14 9:21
     */
    private static void printFile(Map<String, Object> root, String templateFileName, String createFileName, String createFilePaht) {
        try {
            root = formatTable(root);
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
            configuration.setDirectoryForTemplateLoading(new File(Config.FTL_FILE_PATH));
            configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_0));

            // 生成java文件
            Template temp = configuration.getTemplate(templateFileName);
            String fileName = root.get("table_name_upper") + createFileName;
            if (!new File(createFilePaht).exists()) {
                new File(createFilePaht).mkdirs();
            }
            File file = new File(createFilePaht + fileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            temp.process(root, bufferedWriter);
            bufferedWriter.flush();

            fileWriter.close();
        } catch (Exception e) {
            logger.error("生成文件失败：" + e);
            e.printStackTrace();
        }
    }

    /**
     * 下划线转换为驼峰
     *
     * @param line             下划线字符串
     * @param firstIsUpperCase 首字母是否转换为大写
     * @return
     */
    private static String underline2Camel(String line, boolean... firstIsUpperCase) {
        String str = "";

        if (SysUtil.isEmpty(line)) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder();
            String[] strArr;
            // 不包含下划线，且第二个参数是空的
            if (!line.contains("_") && firstIsUpperCase.length == 0) {
                sb.append(line.substring(0, 1).toLowerCase()).append(line.substring(1));
                str = sb.toString();
            } else if (!line.contains("_") && firstIsUpperCase.length != 0) {
                if (!firstIsUpperCase[0]) {
                    sb.append(line.substring(0, 1).toLowerCase()).append(line.substring(1));
                    str = sb.toString();
                } else {
                    sb.append(line.substring(0, 1).toUpperCase()).append(line.substring(1));
                    str = sb.toString();
                }
            } else if (line.contains("_") && firstIsUpperCase.length == 0) {
                strArr = line.split("_");
                for (String s : strArr) {
                    sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
                }
                str = sb.toString();
                str = str.substring(0, 1).toLowerCase() + str.substring(1);
            } else if (line.contains("_") && firstIsUpperCase.length != 0) {
                strArr = line.split("_");
                for (String s : strArr) {
                    sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
                }
                if (!firstIsUpperCase[0]) {
                    str = sb.toString();
                    str = str.substring(0, 1).toLowerCase() + str.substring(1);
                } else {
                    str = sb.toString();
                }
            }
        }
        return str;
    }
}
