package cn.framework.common.service.inter;

import cn.framework.common.dao.auto.entity.SysNoRuleEntity;
import cn.framework.security.exception.AppException;

/**
 * @ClassName SysNoGenerateServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-29 12:03
 * @Version 1.0
 */
public interface SysNoGenerateServiceInterface {

    /**
     * @Description: 生成系统单号
     * @Params: [sysNoRuleEntity]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-04-29 12:03
     */
    String generateNo(SysNoRuleEntity sysNoRuleEntity) throws AppException;
}
