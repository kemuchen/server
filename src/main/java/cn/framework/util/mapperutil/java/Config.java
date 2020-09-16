package cn.framework.util.mapperutil.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @ClassName Config
 * @Desc
 * @Author 柯雷
 * @Date 2020/1/13 16:53
 * @Version 1.0
 */
public class Config {

    /**
     * 配置文件路径
     */
    private static final String CONFIG_FILE_PATH = System
            .getProperty("user.dir")
            + "\\\\src\\\\main\\\\java\\\\cn\\\\framework\\\\util\\\\mapperutil\\\\resources\\\\config.properties";
    static Logger logger = LoggerFactory.getLogger(Config.class);

    /**
     * 数据库URL
     */
    public static String SPRING_DATA_SOURCE_URL = "";

    /**
     * 数据库URL
     */
    public static String SPRING_DATA_SOURCE_USERNAME = "";

    /**
     * 数据库URL
     */
    public static String SPRING_DATA_SOURCE_PASSWORD = "";

    /**
     * mybatis mapper文件参数类型（map | entity）
     */
    public static String MYBATIS_PARAMETER_TYPE = "";

    /**
     * 模板文件路径
     */
    public static String FTL_FILE_PATH = "";

    /**
     * java模板文件名称
     */
    public static String JAVA_FILE_NAME = "";

    /**
     * mapper模板文件名称
     */
    public static String MAPPER_FILE_NAME = "";

    /**
     * entity模板文件名称
     */
    public static String ENTITY_FILE_NAME = "";

    /**
     * 生成dao文件存放路径
     */
    public static String CREATE_DAO_FILE_PATH = "";

    /**
     * 生成mapper文件存放路径
     */
    public static String CREATE_MAPPER_FILE_PATH = "";

    /**
     * 生成entity文件存放路径
     */
    public static String CREATE_ENTITY_FILE_PATH = "";

    /**
     * dao package 配置
     */
    public static String DAO_PACKAGE_PATH = "";

    /**
     * entity package 配置
     */
    public static String ENTITY_PACKAGE_PATH = "";

    /**
     * 作者
     */
    public static String AUTHOR = "";

    /**
     * @Description: 构造函数，读取配置文件
     * @Params: []
     * @return:
     * @Author: 柯雷
     * @Date: 2020/1/13 16:54
     */
    static {
        logger.info("【Config】加载配置文件");
        /** 文件加载 */
        Properties properties;
        /** 文件流读取 */
        FileInputStream inputStream = null;

        BufferedReader bufferedReader = null;
        try {
            properties = new Properties();
            inputStream = new FileInputStream(CONFIG_FILE_PATH);
            bufferedReader = new BufferedReader(new InputStreamReader(
                    inputStream, "GBK"));
            properties.load(bufferedReader);

            SPRING_DATA_SOURCE_URL = properties
                    .getProperty("spring.datasource.url");
            SPRING_DATA_SOURCE_USERNAME = properties
                    .getProperty("spring.datasource.username");
            SPRING_DATA_SOURCE_PASSWORD = properties
                    .getProperty("spring.datasource.password");
            MYBATIS_PARAMETER_TYPE = properties
                    .getProperty("mybatis.parameter.type");
            FTL_FILE_PATH = System.getProperty("user.dir")
                    + properties.getProperty("ftl.file.path");
            JAVA_FILE_NAME = properties.getProperty("java.file.name");
            MAPPER_FILE_NAME = properties.getProperty("mapper.file.name");
            ENTITY_FILE_NAME = properties.getProperty("entity.file.name");
            CREATE_DAO_FILE_PATH = System.getProperty("user.dir")
                    + properties.getProperty("create.dao.file.path");
            CREATE_MAPPER_FILE_PATH = System.getProperty("user.dir")
                    + properties.getProperty("create.mapper.file.path");
            CREATE_ENTITY_FILE_PATH = System.getProperty("user.dir")
                    + properties.getProperty("create.entity.file.path");
            DAO_PACKAGE_PATH = properties.getProperty("dao.package.path");
            ENTITY_PACKAGE_PATH = properties.getProperty("entity.package.path");
            AUTHOR = properties.getProperty("author", "Administrator");
        } catch (Exception e) {
            logger.error("【Config】加载配置文件失败：" + e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    logger.error("【Config】加载配置文件失败：" + e);
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    logger.error("【Config】加载配置文件失败：" + e);
                }
            }
        }
    }
}
