package cn.framework.common.service.impl;

import cn.framework.common.dao.auto.entity.SysNoRuleEntity;
import cn.framework.common.dao.customize.mapper.CustomizeSysNoMapper;
import cn.framework.common.service.inter.SysNoGenerateServiceInterface;
import cn.framework.security.exception.AppException;
//import cn.xpms.xpms.common.entity.SysNoTypeEnum;
import cn.framework.security.exception.codes.SysErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysNoGenerateService
 * @Desc 系统单号生成service
 * @Author 柯雷
 * @Date 2020-04-21 11:49
 * @Version 1.0
 */
@Service
public class SysNoGenerateServiceImplements implements SysNoGenerateServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(SysNoGenerateServiceImplements.class);

    /**
     * 基础DAO操作
     */
    @Autowired
    CustomizeSysNoMapper customizeSysNoMapper;

    /**
     * @Description: 生成单号
     * @Params: [noTypeEnum]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-04-21 14:41
     */
    @Override
    public String generateNo(SysNoRuleEntity sysNoRuleEntity) throws AppException {
        try {
            return customizeSysNoMapper.generateNo(sysNoRuleEntity.getRule());
        } catch (Exception e) {
            logger.error("【SysNoGenerateService.generateNo】生成单号信息出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
