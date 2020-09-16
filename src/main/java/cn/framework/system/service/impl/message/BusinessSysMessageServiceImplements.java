package cn.framework.system.service.impl.message;

import cn.framework.Constants;
import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.common.pojo.PageQueryPojo;
import cn.framework.security.exception.AppException;
import cn.framework.security.exception.codes.SysErrorCode;
import cn.framework.system.dao.auto.entity.SysMessageEntity;
import cn.framework.system.service.inter.message.BusinessSysMessageServiceInterface;
import cn.framework.system.service.inter.message.SysMessageServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName BusinessSysMessageServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020-05-09 10:26
 * @Version 1.0
 */
@Service
public class BusinessSysMessageServiceImplements implements BusinessSysMessageServiceInterface {

    /**
     * 日志输出对象
     */
    Logger logger = LoggerFactory.getLogger(BusinessSysMessageServiceImplements.class);

    /**
     * 系统消息简单操作service
     */
    @Autowired
    SysMessageServiceInterface sysMessageService;

    /**
     * @Description: 查询所有未读消息列表
     * @Params: [receive_man]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-09 10:46
     */
    @Override
    public ApiResponseResultEntity getMessagesUnRead(Integer receive_man) throws AppException {
        try {
            SysMessageEntity sysMessageEntity = new SysMessageEntity();
            sysMessageEntity.setIs_read(Constants.NO);
            sysMessageEntity.setReceive_man(receive_man);
            sysMessageEntity.setValid(Constants.YES);
            return sysMessageService.getMessages(sysMessageEntity);
        } catch (AppException e) {
            logger.error("【BusinessSysMessageServiceImplements.getMessagesUnRead】查询所有未读消息列表出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessSysMessageServiceImplements.getMessagesUnRead】查询所有未读消息列表出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询所有未发送消息列表
     * @Params: [receive_man]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-09 10:49
     */
    @Override
    public ApiResponseResultEntity getMessagesUnSend(Integer receive_man) throws AppException {
        try {
            SysMessageEntity sysMessageEntity = new SysMessageEntity();
            sysMessageEntity.setIs_send(Constants.NO);
            sysMessageEntity.setReceive_man(receive_man);
            sysMessageEntity.setValid(Constants.YES);
            return sysMessageService.getMessages(sysMessageEntity);
        } catch (AppException e) {
            logger.error("【BusinessSysMessageServiceImplements.getMessagesUnSend】查询所有未发送消息列表出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessSysMessageServiceImplements.getMessagesUnSend】查询所有未发送消息列表出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description:阅读消息
     * @Params: [id]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-09 10:49
     */
    @Override
    public ApiResponseResultEntity readMessage(SysMessageEntity sysMessageEntity) throws AppException {
        try {
            sysMessageEntity.setIs_read(Constants.YES);  // 设置已读
            sysMessageEntity.setRead_time(new Date());  // 设置阅读时间
            return this.saveMessage(sysMessageEntity);
        } catch (AppException e) {
            logger.error("【BusinessSysMessageServiceImplements.readMessage】阅读消息出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessSysMessageServiceImplements.readMessage】阅读消息出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 分页查询系统消息列表
     * @Params: [sysMessageEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-09 10:52
     */
    @Override
    public ApiResponseResultEntity getMessagesPage(SysMessageEntity sysMessageEntity) throws AppException {
        try {
            ApiResponseResultEntity apiResponseResultEntity = new ApiResponseResultEntity(SysErrorCode.SUCCESS);
            PageQueryPojo<SysMessageEntity> sysMessageEntityPageQueryPojo = new PageQueryPojo<>(
                    (Integer) sysMessageService.getMessageCount(sysMessageEntity).getData(),
                    (List<SysMessageEntity>) sysMessageService.getMessages(sysMessageEntity).getData());
            apiResponseResultEntity.setData(sysMessageEntityPageQueryPojo);
            return apiResponseResultEntity;
        } catch (AppException e) {
            logger.error("【BusinessSysMessageServiceImplements.getMessagesPage】分页查询系统消息列表出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessSysMessageServiceImplements.getMessagesPage】分页查询系统消息列表息出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存系统消息信息
     * @Params: [sysMessageEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-09 10:51
     */
    @Override
    public ApiResponseResultEntity saveMessage(SysMessageEntity sysMessageEntity) throws AppException {
        try {
            return sysMessageService.saveMessage(sysMessageEntity);
        } catch (AppException e) {
            logger.error("【BusinessSysMessageServiceImplements.saveMessage】保存系统消息信息出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessSysMessageServiceImplements.saveMessage】保存系统消息信息出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
