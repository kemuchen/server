package cn.framework.security.exception.codes;

/**
 * @author ：柯雷
 * @ClassName:：ErrorEnum
 * @Description：
 * @date ：2020/3/28 16:25
 */
public enum SysErrorCode implements BaseErrorCode {

    /**
     * 执行成功
     */
    SUCCESS("00000", "操作成功", "操作成功"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR("50000", "系统程序报错", "未知错误"),

    /**
     * 业务校验异常
     */
    SERVICE_CHECK_ERROR("40000", "业务校验不通过", "业务校验不通过");

    /**
     * 错误代码
     */
    private String error_code;

    /**
     * 错误描述
     */
    private String error_desc;

    /**
     * 前台提示
     */
    private String tip;

    /**
     * @return
     * @Description 构造方法
     * @Date 16:32 2020/3/28
     * @Param [error_code, error_desc]
     **/
    SysErrorCode(String error_code, String error_desc, String tip) {
        this.error_code = error_code;
        this.error_desc = error_desc;
        this.tip = tip;
    }

    @Override
    public String getError_code() {
        return this.error_code;
    }

    @Override
    public String getError_desc() {
        return this.error_desc;
    }

    @Override
    public String getTip() {
        return this.tip;
    }
}
