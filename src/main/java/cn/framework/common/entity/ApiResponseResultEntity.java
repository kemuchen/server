package cn.framework.common.entity;


import cn.framework.security.exception.codes.BaseErrorCode;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @ClassName ApiResponseResultEntity 通用的请求返回数据
 * @Desc
 * @Author 柯雷
 * @Date 2019/12/25 14:45
 * @Version 1.0
 */
public class ApiResponseResultEntity {

    /**
     * 请求返回结果
     */
    private String code;

    /**
     * 请求返回提示
     */
    private String message;

    /**
     * 请求返回数据
     */
    private Object data;

    /**
     * @Description: 默认构造函数
     * @Params: []
     * @return:
     * @Author: 柯雷
     * @Date: 2019/12/25 14:53
     */
    public ApiResponseResultEntity() {
    }

    /**
     * @Description: 构造函数
     * @Params: [status, code, message, data]
     * @return:
     * @Author: 柯雷
     * @Date: 2019/12/25 14:54
     */
    public ApiResponseResultEntity(String code, String message, Map<Object, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造函数
     *
     * @param code 成功/失败
     * @param data 数据
     * @Author: YCJ
     */
    public ApiResponseResultEntity(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    /**
     * @return
     * @Description 构造函数
     * @Param [code, message] 代码，信息
     **/
    public ApiResponseResultEntity(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @Description: 构造函数
     * @Params: [errorCode]
     * @return:
     * @Author: 柯雷
     * @Date: 2020-05-09 10:16
     */
    public ApiResponseResultEntity(BaseErrorCode errorCode) {
        this.code = errorCode.getError_code();
        this.message = errorCode.getError_desc();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
