package cn.framework.annotation;

import java.lang.annotation.*;

/**
 * 分页查询
 *
 * @ClassName PageQuery
 * @Desc
 * @Author 柯雷
 * @Date 2020/1/16 11:16
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PageQuery {

    /***
     * @Description: 页面大小
     * @Params: []
     * @return: int
     * @Author: 柯雷
     * @Date: 2020/1/16 11:18
     */
    int pageSize() default 10;

    /***
     * @Description: 当前页
     * @Params: []
     * @return: int
     * @Author: 柯雷
     * @Date: 2020/1/16 11:18
     */
    int current() default 1;
}
