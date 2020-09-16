package cn.framework.security.exception;

import cn.framework.security.exception.codes.BaseErrorCode;

/**
 * @ClassName AppException
 * @Desc
 * @Author 柯雷
 * @Date 2020/3/12 12:38
 * @Version 1.0
 */
public class AppException extends Exception {

    /**
     * 异常代码
     */
    private String error_code;

    /**
     * 异常描述
     */
    private String error_message;

    /**
     * 异常提示信息
     */
    private String error_tip;


    /**
     * @Description: 有参构造函数
     * @Params: AuditEnumError
     * @return:
     * @Author: Yk
     * @Date: 2020/4/17 15:39
     */
    public AppException(BaseErrorCode errorCode) {
        this.error_code = errorCode.getError_code();
        this.error_message = errorCode.getError_desc();
        this.error_tip = errorCode.getTip();
    }

    /**
     * @Description: 有参构造函数
     * @Params: [error_code, error_message, error_tip]
     * @return:
     * @Author: 柯雷
     * @Date: 2020/3/12 12:52
     */
    public AppException(String error_code, String error_message,
                        String error_tip) {
        this.error_code = error_code;
        this.error_message = error_message;
        this.error_tip = error_tip;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public String getError_tip() {
        return error_tip;
    }

    public void setError_tip(String error_tip) {
        this.error_tip = error_tip;
    }
}
