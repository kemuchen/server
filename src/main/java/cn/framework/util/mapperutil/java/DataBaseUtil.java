package cn.framework.util.mapperutil.java;

import cn.framework.util.sys.SysUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @ClassName DataBaseUtil
 * @Desc 数据操作
 * @Author 柯雷
 * @Date 2019/7/10 15:25
 * @Version 1.0
 */
public class DataBaseUtil {

    static Logger logger = LoggerFactory.getLogger(DataBaseUtil.class);

    /**
     * @Description: 获取数据库连接
     * @Params: []
     * @return: java.sql.Connection
     * @Author: 柯雷
     * @Date: 2019/7/10 15:28
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(Config.SPRING_DATA_SOURCE_URL, Config.SPRING_DATA_SOURCE_USERNAME,
                    Config.SPRING_DATA_SOURCE_PASSWORD);
            return connection;
        } catch (Exception e) {
            logger.error("获取数据库连接失败：" + e);
            throw e;
        }
    }

    /***
     * @Description: 关闭数据库资源
     * @Params: [connection, statement, resultSet]
     * @return: void
     * @Author: 柯雷
     * @Date: 2019/7/10 15:32
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (!SysUtil.isEmpty(connection)) {
            connection.close();
        }
        if (!SysUtil.isEmpty(statement)) {
            statement.close();
        }
        if (!SysUtil.isEmpty(resultSet)) {
            resultSet.close();
        }
    }
}
