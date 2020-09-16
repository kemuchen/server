package cn.framework.ruuner;

import java.util.List;

import cn.framework.Constants;
import cn.framework.system.dao.auto.entity.SysDictEntity;
import cn.framework.system.dao.auto.mapper.SysDictMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import cn.framework.cache.CacheEntity;
import cn.framework.cache.CacheMangerImplements;

/**
 * @author ：柯雷
 * @ClassName:：SysDictCacheRunner
 * @Description：
 * @date ：2020/3/28 17:38
 */
@Component //被spring容器管理
@Order(1) //如果多个自定义ApplicationRunner，用来标明执行顺序
public class SysDictCacheRunner implements ApplicationRunner {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(SysDictCacheRunner.class);

    /**
     * 静态字典项DAO
     */
    @Autowired
    SysDictMapper sysDictMapper;

    /**
     * @return void
     * @Description 加载系统字典项缓存
     * @Date 17:42 2020/3/28
     * @Param [args]
     **/
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("【SysDictCacheRunner.run】加载静态字典项缓存");
        CacheEntity<List<SysDictEntity>> sysDictCache = new CacheEntity<>();

        // 查询所有有效的酒店配置
        SysDictEntity sysDictEntity = new SysDictEntity();
        sysDictEntity.setValid(Constants.YES);
        sysDictCache.setData(sysDictMapper.getSysDicts(sysDictEntity));

        CacheMangerImplements.getInstance().putCache("SysDict", sysDictCache);
    }
}
