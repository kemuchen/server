package cn.framework.cache;

import java.util.Map;
import java.util.Set;

/**
 * @author ：柯雷
 * @ClassName:：CacheManagerInterface
 * @Description：
 * @date ：2020/3/28 14:42
 */
public interface CacheManagerInterface {

    /**
     * @return void
     * @Description 存入缓存
     * @Date 14:44 2020/3/28
     * @Param [key, data, cacheEntity]
     **/
    void putCache(String key, CacheEntity cacheEntity);

    /**
     * @return void
     * @Description 存入缓存
     * @Date 14:58 2020/3/28
     * @Param [key, datas, timeOut]
     **/
    void putCache(String key, Object datas, long timeOut);

    /**
     * @return cn.xpms.xpms.common.cache.CacheEntity
     * @Description 获取对应缓存
     * @Date 14:45 2020/3/28
     * @Param [key]
     **/
    CacheEntity getCacheByKey(String key);

    /**
     * @return java.lang.Object
     * @Description 获取对应缓存数据
     * @Date 15:02 2020/3/28
     * @Param [key]
     **/
    Object getCacheDataByKey(String key);

    /**
     * @return java.util.Map<java.lang.String, cn.xpms.xpms.common.cache.CacheEntity>
     * @Description 获取所有缓存
     * @Date 14:46 2020/3/28
     * @Param []
     **/
    Map<String, CacheEntity> getCacheAll();

    /**
     * @return boolean
     * @Description 判断是否在缓存中
     * @Date 14:46 2020/3/28
     * @Param [key]
     **/
    boolean isContains(String key);

    /**
     * @return void
     * @Description 清除所有缓存
     * @Date 14:47 2020/3/28
     * @Param []
     **/
    void clearAll();

    /**
     * @return void
     * @Description 清除对应缓存
     * @Date 14:47 2020/3/28
     * @Param [key]
     **/
    void clearByKey(String key);

    /**
     * @return boolean
     * @Description 缓存是否超时失效
     * @Date 14:48 2020/3/28
     * @Param [key]
     **/
    boolean isTimeOut(String key);

    /**
     * @return java.util.Set<java.lang.String>
     * @Description 获取所有key
     * @Date 14:48 2020/3/28
     * @Param []
     **/
    Set<String> getAllKeys();
}
