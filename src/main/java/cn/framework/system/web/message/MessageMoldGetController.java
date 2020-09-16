package cn.framework.system.web.message;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.system.service.inter.message.BusinessSysMessageServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MessageMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020-05-09 10:08
 * @Version 1.0
 */
@Api(tags = "系统消息查询接口")
@RestController
@RequestMapping("/api/system/")
public class MessageMoldGetController {

    @Autowired
    BusinessSysMessageServiceInterface businessSysMessageService;

    /**
     * @Description: 获取未读消息列表
     * @Params: [receive_man]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-09 11:05
     */
    @ApiOperation("1.获取未读消息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "receive_man", value = "消息接收人", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("un_read_mssages/{receive_man}")
    public ApiResponseResultEntity unReadMssages(@PathVariable("receive_man") Integer receive_man) throws AppException {
        return businessSysMessageService.getMessagesUnRead(receive_man);
    }
}
