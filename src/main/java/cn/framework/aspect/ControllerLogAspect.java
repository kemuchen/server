/**
 * flies
 */
package cn.framework.aspect;

import cn.framework.util.sys.SysUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * controller日志切面处理
 *
 * @ClassName PageQueryAspect
 * @Desc
 * @Author 柯雷
 * @Date 2020/1/16 11:19
 * @Version 1.0
 */
@Aspect
@Component
public class ControllerLogAspect {

    /**
     * 日志输出对象
     */
    Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    /**
     * 以 controller 包下定义的所有请求为切入点
     */
    @Pointcut("execution(public * cn.xpms..*..web..*.*(..))")
    public void controllerLog() {
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 打印请求相关参数
        logger.info("========================================== Controller-Start ==========================================");
        // 打印请求 url
        logger.info("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        logger.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        logger.info("Class Method   : {}.{}", joinPoint.getSignature()
                .getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        logger.info("IP             : {}", SysUtil.getIpAddr(request));
        // 打印请求入参
        logger.info("Request Args   : {}", joinPoint.getArgs());
    }

    /**
     * 在切点之后织入
     *
     * @throws Throwable
     */
    @After("controllerLog()")
    public void doAfter() throws Throwable {
        logger.info("=========================================== Controller-End ===========================================");
        // 每个请求之间空一行
        logger.info("");
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("controllerLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        logger.info("Response Args  : {}", result);
        // 执行耗时
        logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }
}
