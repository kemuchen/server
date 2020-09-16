package cn.framework.util.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Environment
 * @Desc
 * @Author 柯雷
 * @Date 2020-05-14 9:48
 * @Version 1.0
 */
public class Environment {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(Environment.class);

    /**
     * 参数map
     */
    private static Map<String, Object> parameters = new HashMap<>();

    /**
     * @param ：@param params
     * @return ：void
     * @throws
     * @Title：setSystemProperties
     * @Description：设置系统参数
     */
    public static void setSystemProperties(List<Map<String, Object>> params) {
        if (SysUtil.isEmpty(params)) {
            for (Map<String, Object> tempMap : params) {
                parameters.put((String) tempMap.get("KEY"), tempMap.get("VALUE"));
            }
        }
    }

    /**
     * @param ：@param key
     * @param ：@param value
     * @return ：void
     * @throws
     * @Title：setParameter
     * @Description：设置系统参数
     */
    public static void setParameter(String key, Object value) {
        parameters.put(key, value);
    }

    /**
     * @param ：@param  key
     * @param ：@return
     * @return ：Object
     * @throws
     * @Title：getParameter
     * @Description：获取系统参数
     */
    public static Object getParameter(String key) {
        return parameters.get(key);
    }

    /**
     * @param ：@param  key
     * @param ：@return
     * @return ：String
     * @throws
     * @Title：getValue
     * @Description：获取系统参数，返回string类型
     */
    public static String getValue(String key) {
        return (String) parameters.get(key);
    }
}
