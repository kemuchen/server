package cn.framework.security.filter;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.codes.HttpErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author ：柯雷
 * @ClassName:：ErrorPageInterceptor
 * @Description： 错误页面拦截器
 * @date ：2018年9月6日 上午10:50:48
 */
@Component
public class ErrorPageInterceptor extends HandlerInterceptorAdapter {

    /**
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
        String error_code = new StringBuilder(response.getStatus()).toString();
        if (HttpErrorCode.contains(error_code)) {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            ApiResponseResultEntity apiResponseResultEntity = new ApiResponseResultEntity();

            HttpErrorCode httpErrorCode = HttpErrorCode.getHttpErrorCode(error_code);
            apiResponseResultEntity.setCode(httpErrorCode.getError_code());
            apiResponseResultEntity.setMessage(httpErrorCode.getTip());
            out.print(apiResponseResultEntity.toString());
            response.setStatus(200);
            out.close();
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
