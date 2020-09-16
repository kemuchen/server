package cn.framework.config;

import cn.framework.security.filter.AccessInterceptor;
import cn.framework.security.filter.CheckLoginInterceptor;
import cn.framework.security.filter.ErrorPageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName WebMvcConfig
 * @Desc
 * @Author 柯雷
 * @Date 2019/12/26 12:36
 * @Version 1.0
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * @Description 错误页面拦截去
     */
    @Autowired
    AccessInterceptor accessInterceptor;

    /**
     * @Description 错误页面拦截去
     */
    @Autowired
    ErrorPageInterceptor errorPageInterceptor;

    @Autowired
    CheckLoginInterceptor checxpmsoginInterceptor;

    /**
     * @param ：@param registry
     * @return ：void
     * @throws
     * @Title：addInterceptors
     * @Description：添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(errorPageInterceptor).addPathPatterns("/*");  // 拦截所有
        registry.addInterceptor(accessInterceptor).addPathPatterns("/**");  // 拦截所有
        registry.addInterceptor(checxpmsoginInterceptor).addPathPatterns("/**").
                excludePathPatterns("/api/system/login", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/");
        super.addInterceptors(registry);
    }

    /**
     * <p>Title：addResourceHandlers</p>
     * <p>Description：配置静态资源访问 </p>
     *
     * @param registry
     * @see #addResourceHandlers(ResourceHandlerRegistry)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

}
