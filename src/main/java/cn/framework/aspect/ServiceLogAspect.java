package cn.framework.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * service日志切面处理
 *
 * @ClassName PageQueryAspect
 * @Desc
 * @Author 柯雷
 * @Date 2020/1/16 11:19
 * @Version 1.0
 */
@Aspect
@Component
public class ServiceLogAspect {

    /**
     * 日志输出对象
     */
    Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * 以 service 包下定义的所有请求为切入点
     */
    @Pointcut("execution(public * cn.xpms..*..service..*.*(..))")
    public void serviceLog() {
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("serviceLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 打印请求相关参数
        logger.info("========================================== Service-Start ==========================================");
        // 打印调用 controller 的全路径以及执行方法
        logger.info("Class Method   : {}.{}", joinPoint.getSignature()
                .getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求入参
        logger.info("Request Args   : {}", JSONObject.toJSON(joinPoint.getArgs()));
    }

    /**
     * 在切点之后织入
     *
     * @throws Throwable
     */
    @After("serviceLog()")
    public void doAfter() throws Throwable {
        logger.info("=========================================== Service-End ===========================================");
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
    @Around("serviceLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        logger.info("Response Args  : {}", JSONObject.toJSON(result));
        // 执行耗时
        logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }
}
