package cn.framework.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName PageQueryVo
 * @Desc 分页查询返回数据封装
 * @Author 柯雷
 * @Date 2020-04-27 17:33
 * @Version 1.0
 */
public class PageQueryPojo<T> implements Serializable {

    /**
     * 总数量
     */
    private Integer total;

    /**
     * 数据列表
     */
    private List<T> data;

    /**
     * @Description: 无参构造函数
     * @Params: []
     * @return:
     * @Author: 柯雷
     * @Date: 2020-05-06 10:30
     */
    public PageQueryPojo() {

    }

    /**
     * @Description: 有参构造函数
     * @Params: [total, data]
     * @return:
     * @Author: 柯雷
     * @Date: 2020-05-06 10:30
     */
    public PageQueryPojo(Integer total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    /**
     * 获取总数
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 设置总数
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 获取列表数据
     */
    public List<T> getData() {
        return data;
    }

    /**
     * 设置列表数据
     */
    public void setData(List<T> data) {
        this.data = data;
    }
}
