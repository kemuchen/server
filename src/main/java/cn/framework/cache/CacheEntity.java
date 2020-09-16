package cn.framework.cache;

/**
 * @author ：柯雷
 * @ClassName:：CacheEntity
 * @Description：缓存实体
 * @date ：2020/3/28 14:34
 */
public class CacheEntity<T> {

    /**
     * 缓存数据
     */
    private T data;

    /**
     * 数据失效时间
     */
    private Long times;

    /**
     * 最后刷新时间
     */
    private Long lastRefreshTime;

    /**
     * @return
     * @Description 无参构造函数
     * @Date 14:36 2020/3/28
     * @Param []
     **/
    public CacheEntity() {
    }

    /**
     * @return
     * @Description 有参构造函数
     * @Date 14:38 2020/3/28
     * @Param [key, datas, times]
     **/
    public CacheEntity(T data, Long times, Long lastRefreshTime) {
        this.data = data;
        this.times = times;
        this.lastRefreshTime = lastRefreshTime;
    }

    /**
     * 获取：数据
     */
    public T getData() {
        return data;
    }

    /**
     * 设置：数据
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * 获取：失效时间
     */
    public Long getTimes() {
        return times;
    }

    /**
     * 设置：失效时间
     */
    public void setTimes(Long times) {
        this.times = times;
    }

    /**
     * 获取：最后刷新时间
     */
    public Long getLastRefreshTime() {
        return lastRefreshTime;
    }

    /**
     * 设置：最后刷新时间
     */
    public void setLastRefreshTime(Long lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }
}
