package cn.framework.system.web.user;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.common.pojo.BasePojo;
import cn.framework.security.exception.AppException;
import cn.framework.system.service.inter.user.BusinessUserServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UserMoldDeleteController
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-28 9:48
 * @Version 1.0
 */
@Api(tags = "用户信息删除接口")
@RestController
@RequestMapping("/api/system/")
public class UserMoldDeleteController {

    /**
     * 用户业务操作service
     */
    @Autowired
    BusinessUserServiceInterface businessUserService;

    /**
     * @Description:
     * @Params: [sysUserEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-05 11:26
     */
    @ApiOperation("1.删除用户信息")
    @PostMapping(value = "user", produces = "application/json")
    public ApiResponseResultEntity user(@RequestBody List<Integer> ids, BasePojo basePojo) throws AppException {
        return businessUserService.deleteUser(ids, basePojo);
    }
}
