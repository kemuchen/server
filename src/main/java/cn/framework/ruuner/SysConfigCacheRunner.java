package cn.framework.ruuner;

import cn.framework.Constants;
import cn.framework.cache.CacheEntity;
import cn.framework.cache.CacheMangerImplements;
import cn.framework.system.dao.auto.entity.SysConfigEntity;
import cn.framework.system.dao.auto.mapper.SysConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName SysConfigCacheRunner
 * @Desc
 * @Author 柯雷
 * @Date 2020-05-04 10:57
 * @Version 1.0
 */
@Component //被spring容器管理
@Order(4) //如果多个自定义ApplicationRunner，用来标明执行顺序
public class SysConfigCacheRunner implements ApplicationRunner {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(SysConfigCacheRunner.class);

    /**
     * 系统配置CURD操作
     */
    @Autowired
    SysConfigMapper sysConfigMapper;

    /**
     * @Description: 初始化系统配置
     * @Params: [args]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020-05-04 11:01
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("【SysConfigCacheRunner.run】加载系统配置信息");
        CacheEntity<List<SysConfigEntity>> sysConfigCache = new CacheEntity<>();

        // 查询有效的系统配置
        SysConfigEntity sysConfigEntity = new SysConfigEntity();
        sysConfigEntity.setValid(Constants.YES);
        // TODO 当前bean还未创建
        sysConfigCache.setData(sysConfigMapper.getSysConfigs(sysConfigEntity));

        CacheMangerImplements.getInstance().putCache("SysConfig", sysConfigCache);
    }
}
