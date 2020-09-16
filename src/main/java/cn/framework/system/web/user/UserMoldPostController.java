package cn.framework.system.web.user;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.system.dao.auto.entity.SysUserEntity;
import cn.framework.system.service.inter.user.BusinessUserServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserMoldPostController
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-28 9:47
 * @Version 1.0
 */
@Api(tags = "用户信息新建接口")
@RestController
@RequestMapping("/api/system/")
public class UserMoldPostController {

    /**
     * 用户信息业务service
     */
    @Autowired
    BusinessUserServiceInterface businessUserService;

    /**
     * @Description: 用户登录
     * @Params: [sysUserEntity, request]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 10:05
     */
    @ApiOperation("1.用户登录")
    @PostMapping("login")
    public ApiResponseResultEntity login(@RequestBody SysUserEntity sysUserEntity, HttpServletRequest request) throws AppException {
        return businessUserService.login(sysUserEntity, request);
    }
}
