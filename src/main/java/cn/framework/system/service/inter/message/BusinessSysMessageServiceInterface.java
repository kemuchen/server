package cn.framework.system.service.inter.message;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.system.dao.auto.entity.SysMessageEntity;

/**
 * @ClassName BusinessSysMessageServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020-05-09 10:20
 * @Version 1.0
 */
public interface BusinessSysMessageServiceInterface {

    /**
     * @Description: 查询所有未读消息
     * @Params: [receive_man]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-09 10:23
     */
    ApiResponseResultEntity getMessagesUnRead(Integer receive_man) throws AppException;

    /**
     * @Description: 查询所有未发送消息
     * @Params: [receive_man]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-09 10:24
     */
    ApiResponseResultEntity getMessagesUnSend(Integer receive_man) throws AppException;

    /**
     * @Description: 阅读消息
     * @Params: [sysMessageEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-09 10:25
     */
    ApiResponseResultEntity readMessage(SysMessageEntity sysMessageEntity) throws AppException;

    /**
     * @Description: 分页查询消息列表
     * @Params: [sysMessageEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-09 10:26
     */
    ApiResponseResultEntity getMessagesPage(SysMessageEntity sysMessageEntity) throws AppException;

    /**
     * @Description: 保存消息
     * @Params: [sysMessageEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-09 10:35
     */
    ApiResponseResultEntity saveMessage(SysMessageEntity sysMessageEntity) throws AppException;
}
