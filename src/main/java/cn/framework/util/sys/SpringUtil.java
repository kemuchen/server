package cn.framework.util.sys;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName SpringUtil
 * @Desc
 * @Author 柯雷
 * @Date 2019/10/20 17:14
 * @Version 1.0
 */
//@Component
public class SpringUtil implements ApplicationContextAware {

    /**
     * spring上下文
     */
    private static ApplicationContext applicationContext;

    /**
     * @Description: 设置spring上下文
     * @Params: [applicationContext]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020-05-09 15:12
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    public static void setApplication(ApplicationContext application) {
        applicationContext = application;
    }

    // 通过name获取 Bean.
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    // 通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
