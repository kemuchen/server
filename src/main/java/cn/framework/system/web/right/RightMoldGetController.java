package cn.framework.system.web.right;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.system.service.inter.right.BusinessRightServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName RightMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-28 15:45
 * @Version 1.0
 */
@Api(tags = "菜单信息查询接口")
@RestController
@RequestMapping("/api/system/")
public class RightMoldGetController {

    /**
     * 菜单业务操作service
     */
    @Autowired
    BusinessRightServiceInterface businessRightService;

    /**
     * @Description:
     * @Params: []
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 15:46
     */
    @ApiOperation("1.查询用户权限信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("rights/{user_id}")
    public ApiResponseResultEntity rights(@PathVariable(value = "user_id") Integer user_id) throws AppException {
        return businessRightService.getRightsByUserId(user_id);
    }
}
