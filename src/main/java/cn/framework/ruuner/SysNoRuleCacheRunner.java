package cn.framework.ruuner;

import cn.framework.Constants;
import cn.framework.cache.CacheEntity;
import cn.framework.cache.CacheMangerImplements;
import cn.framework.common.dao.auto.entity.SysNoRuleEntity;
import cn.framework.common.dao.auto.mapper.SysNoRuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName SysNoRuleCacheRunner
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-29 9:37
 * @Version 1.0
 */
@Component //被spring容器管理
@Order(3) //如果多个自定义ApplicationRunner，用来标明执行顺序
public class SysNoRuleCacheRunner implements ApplicationRunner {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(SysNoRuleCacheRunner.class);

    /**
     * 静态字典项DAO
     */
    @Autowired
    SysNoRuleMapper sysNoRuleMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("【SysNoRuleCacheRunner.run】加载系统单号生成规则缓存");
        CacheEntity<List<SysNoRuleEntity>> sysNoRuleCache = new CacheEntity<>();

        // 查询所有有效的酒店配置
        SysNoRuleEntity sysNoRuleEntity = new SysNoRuleEntity();
        sysNoRuleEntity.setValid(Constants.YES);
        sysNoRuleCache.setData(sysNoRuleMapper.getSysNoRules(sysNoRuleEntity));

        CacheMangerImplements.getInstance().putCache("SysRule", sysNoRuleCache);
    }
}
