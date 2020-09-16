package cn;

import cn.framework.util.sys.SpringUtil;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @Description: SpringBoot 启动入口
 * @Params:
 * @return:
 * @Author: 柯雷
 * @Date: 2019/12/25 9:32
 */
@SpringBootApplication(scanBasePackages = "cn.**")
@EnableCaching
@EnableTransactionManagement
@MapperScan("cn.**.dao.**")
public class Application extends SpringBootServletInitializer {

    /**
     * spring boot上下文环境
     */
    @Autowired
    private Environment environment;

    /**
     * @Description: 入口main方法
     * @Params: [args]
     * @return: void
     * @Author: 柯雷
     * @Date: 2019/12/25 9:35
     */
    public static void main(String[] args) {
        // 启动时设置spring上下文
        SpringUtil.setApplication(SpringApplication.run(Application.class, args));
    }

    /**
     * @Description: 打成war包时需要修改启动类
     * @Params: [builder]
     * @return: org.springframework.boot.builder.SpringApplicationBuilder
     * @Author: 柯雷
     * @Date: 2020-05-09 15:17
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    /**
     * @Description: 修改启动类后重新设置spring上下文
     * @Params: [servletContext]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020-05-09 15:18
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        SpringUtil.setApplication(WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext));
    }

    /**
     * @Description: 设置Tomcat端口号：http端口和https端口
     * @Params: []
     * @return: org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
     * @Author: 柯雷
     * @Date: 2019/12/25 9:35
     */
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        // 设置http端口
        connector.setPort(environment.getProperty("server.http.port", Integer.class));
        connector.setSecure(false);
        // 设置https端口
        connector.setRedirectPort(environment.getProperty("server.port", Integer.class));
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }
}
