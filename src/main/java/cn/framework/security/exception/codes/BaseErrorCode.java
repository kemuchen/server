package cn.framework.security.exception.codes;

/**
 * @ClassName BaseErrorCode
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-28 10:50
 * @Version 1.0
 */
public interface BaseErrorCode {

    String getError_code();

    String getError_desc();

    String getTip();
}
