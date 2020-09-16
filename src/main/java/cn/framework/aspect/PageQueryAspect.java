package cn.framework.aspect;

import cn.framework.annotation.PageQuery;
import cn.framework.common.entity.BaseEntity;
import cn.framework.security.exception.AppException;
import cn.framework.security.exception.codes.SysErrorCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 分页查询，切面处理类
 *
 * @ClassName PageQueryAspect
 * @Desc
 * @Author 柯雷
 * @Date 2020/1/16 11:19
 * @Version 1.0
 */
@Aspect
@Component
public class PageQueryAspect {

    Logger logger = LoggerFactory.getLogger(PageQueryAspect.class);

    /**
     * 以 annotation 注解定义的所有请求为切入点
     */
    @Pointcut("@annotation(cn.framework.annotation.PageQuery))")
    public void pageQueryPointCut() {
    }

    /**
     * @Description: 在切点前织入
     * @Params: [point]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020-04-27 16:42
     */
    @Before(value = "pageQueryPointCut() && @annotation(pageQuery)", argNames = "point, pageQuery")
    public void doBefore(JoinPoint point, PageQuery pageQuery) throws Throwable {
        logger.info("【PageQueryAspect.doBefore】处理分页查询请求");
        try {
            // 获取传入参数
            Object[] args = point.getArgs();
            BaseEntity baseEntity = (BaseEntity) args[0];

            // 当前页
            Integer currentPage = pageQuery.current();
            Integer pageSize = pageQuery.pageSize();
            if (baseEntity.getCurrent() == null || baseEntity.getCurrent() == 0 || baseEntity.getCurrent() < 1) {
                baseEntity.setCurrent(currentPage);
            } else {
                currentPage = baseEntity.getCurrent();
            }

            // 页面大小
            if (baseEntity.getPageSize() == null || baseEntity.getPageSize() == 0 || baseEntity.getPageSize() < 1) {
                baseEntity.setPageSize(pageSize);
            } else {
                pageSize = baseEntity.getPageSize();
            }
            // 设置起始页
            baseEntity.setStartRow((currentPage - 1) * pageSize);
        } catch (Exception e) {
            logger.error("【PageQueryAspect.around】分页查询请求失败:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 执行
     * @Params: [joinPoint]
     * @return: java.lang.Object
     * @Author: 柯雷
     * @Date: 2020-05-06 9:38
     */
    @Around("pageQueryPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //执行方法
        Object result = joinPoint.proceed();
        return result;
    }
}
