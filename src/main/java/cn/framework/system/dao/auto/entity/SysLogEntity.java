package cn.framework.system.dao.auto.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import cn.framework.common.entity.BaseEntity;

/***
 * @Description: 系统日志表
 * @Author: Administrator
 * @Date: 2020/04/27 05:18
 */
@ApiModel(value = "系统日志表", description = "数据库表反向工具生成对应表sys_log")
@Getter
@Setter
public class SysLogEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 错误代码
    @ApiModelProperty(value = "错误代码", hidden = true)
    private String error_code;

    // 错误描述
    @ApiModelProperty(value = "错误描述", hidden = true)
    private String error_desc;

    // 主键
    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;

    // ip地址
    @ApiModelProperty(value = "ip地址", hidden = true)
    private String ip;

    // 调用方法
    @ApiModelProperty(value = "调用方法", hidden = true)
    private String method;

    // 用户操作
    @ApiModelProperty(value = "用户操作", hidden = true)
    private String operation;

    // 执行结果
    @ApiModelProperty(value = "执行结果", hidden = true)
    private String operation_result;

    // 返回信息
    @ApiModelProperty(value = "返回信息", hidden = true)
    private String operation_return;

    // 执行时长
    @ApiModelProperty(value = "执行时长", hidden = true)
    private long operation_time;

    // 请求参数
    @ApiModelProperty(value = "请求参数", hidden = true)
    private String params;

    // 用户名
    @ApiModelProperty(value = "用户名", hidden = true)
    private String username;

}