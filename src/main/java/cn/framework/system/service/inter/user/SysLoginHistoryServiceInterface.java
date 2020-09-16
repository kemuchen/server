package cn.framework.system.service.inter.user;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.system.dao.auto.entity.SysLoginHistoryEntity;

/**
 * @ClassName SysUserLoginServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-28 10:10
 * @Version 1.0
 */
public interface SysLoginHistoryServiceInterface {

    /**
     * @Description: 保存登录历史
     * @Params: [sysLoginHistoryEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 10:11
     */
    ApiResponseResultEntity saveLoginHistory(SysLoginHistoryEntity sysLoginHistoryEntity) throws AppException;
}
