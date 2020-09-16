package cn.framework.security.handler;

import cn.framework.security.exception.codes.SysErrorCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.util.sys.SysUtil;
import cn.framework.system.dao.auto.entity.SysLogEntity;
import cn.framework.system.dao.auto.entity.SysUserEntity;
import cn.framework.system.dao.auto.mapper.SysLogMapper;

/**
 * @ClassName GlobalExceptionHandler
 * @Desc
 * @Author 柯雷
 * @Date 2020/3/12 12:20
 * @Version 1.0
 */
@Controller     // 以rest形式返回异常信息
@ControllerAdvice   // 全局异常处理器
public class GlobalExceptionHandler {

    /**
     * 日志输出对象
     */
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 异常信息保存DAO
     */
    @Autowired
    SysLogMapper sysLogMapper;

    /**
     * @Description: 权限不足异常处理
     * @Params: [req, e]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/3/12 12:21
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String exceptionHandler(Exception exception) {
        logger.error("【GlobalExceptionHandler.exceptionHandlerx】系统异常：" + SysUtil.getExceptionString(exception));
        ApiResponseResultEntity apiResponseResultEntity = new ApiResponseResultEntity();
        apiResponseResultEntity.setCode(SysErrorCode.SYSTEM_ERROR.getError_code());
        apiResponseResultEntity.setMessage("系统异常");
        return apiResponseResultEntity.toString();
    }

    /**
     * @Description: 权限不足
     * @Params: [ex]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-05-04 15:14
     */
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationException(AuthorizationException ex) {
        logger.info("【GlobalExceptionHandler.AuthorizationException】没有操作权限");
        ApiResponseResultEntity apiResponseResultEntity = new ApiResponseResultEntity();
        apiResponseResultEntity.setCode(SysErrorCode.SERVICE_CHECK_ERROR.getError_code());
        apiResponseResultEntity.setMessage("权限不足");
        return apiResponseResultEntity.toString();
    }

    /**
     * @Description:
     * @Params: [appException]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/3/12 12:55
     */
    @ExceptionHandler(value = AppException.class)
    @ResponseBody
    public String appExceptionHandler(AppException appException) {
        logger.info("-------------------------------------------Exception-STA-------------------------------------------------------------------");
        logger.info("【GlobalExceptionHandler.appExceptionHandler】" + SysUtil.getExceptionString(appException));
        logger.info("-------------------------------------------Exception-END-------------------------------------------------------------------");
        ApiResponseResultEntity apiResponseResultEntity = new ApiResponseResultEntity();
        apiResponseResultEntity.setCode(appException.getError_code());
        apiResponseResultEntity.setMessage(appException.getError_tip());
        // TODO 保存异常日志
        saveSysLog(appException);
        return apiResponseResultEntity.toString();
    }

    /**
     * @return void
     * @Description 保存错误日志信息
     * @Date 17:25 2020/3/28
     * @Param [appException]
     **/
    private void saveSysLog(AppException appException) {
        logger.info("【GlobalExceptionHandler.saveSysLog】保存错误日志信息");
        SysLogEntity sysLogEntity = new SysLogEntity();
        sysLogEntity.setOperation(SysUtil.getExceptionMethod(appException));   // 操作
        sysLogEntity.setError_code(appException.getError_code());   // 操作代码
        sysLogEntity.setError_desc(appException.getError_message());  // 错误描述
        sysLogEntity.setOperation_result(SysUtil.getExceptionString(appException).substring(1, 1000));    // 执行结果

        ApiResponseResultEntity apiResponseResultEntity = new ApiResponseResultEntity();
        apiResponseResultEntity.setCode(appException.getError_code());
        apiResponseResultEntity.setMessage(appException.getError_tip());
        sysLogEntity.setOperation_return(apiResponseResultEntity.toString());   // 执行返回
        sysLogEntity.setMethod(SysUtil.getExceptinLocation(appException));  // 异常方法

        // 用户名
        SysUserEntity userEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        sysLogEntity.setUsername(SysUtil.isEmpty(userEntity) ? "" : userEntity.getUsername());
        sysLogEntity.setCreate_user(SysUtil.isEmpty(userEntity) ? 1 : userEntity.getId());

        sysLogEntity.setIp("127.0.0.1");
        sysLogMapper.insertSysLog(sysLogEntity);
    }
}
