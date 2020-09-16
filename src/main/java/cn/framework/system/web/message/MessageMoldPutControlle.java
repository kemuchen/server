package cn.framework.system.web.message;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.system.dao.auto.entity.SysMessageEntity;
import cn.framework.system.service.inter.message.BusinessSysMessageServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MessageMoldPutControlle
 * @Desc
 * @Author 柯雷
 * @Date 2020-05-09 11:06
 * @Version 1.0
 */
@Api(tags = "系统消息录入接口")
@RestController
@RequestMapping("/api/system/")
public class MessageMoldPutControlle {

    @Autowired
    BusinessSysMessageServiceInterface businessSysMessageService;

    /**
     * @Description: 阅读消息
     * @Params: [sysMessageEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-09 11:10
     */
    @ApiOperation("1.阅读消息")
    @PostMapping("read_message")
    public ApiResponseResultEntity message(SysMessageEntity sysMessageEntity) throws AppException {
        return businessSysMessageService.readMessage(sysMessageEntity);
    }
}
