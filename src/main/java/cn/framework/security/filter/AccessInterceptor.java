package cn.framework.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName AccessInterceptor 跨域请求拦截器
 * @Desc
 * @Author 柯雷
 * @Date 2019/12/26 12:33
 * @Version 1.0
 */
@Component
public class AccessInterceptor extends HandlerInterceptorAdapter {

    /**
     * @Description 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(AccessInterceptor.class);

    /**
     * <p>Title：preHandle</p>
     * <p>Description：拦截器处理 </p>
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     * @see HandlerInterceptorAdapter#preHandle(HttpServletRequest, HttpServletResponse, Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "HEAD, POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Credentials", "true"); //是否支持cookie跨域
        return super.preHandle(request, response, handler);
    }
}