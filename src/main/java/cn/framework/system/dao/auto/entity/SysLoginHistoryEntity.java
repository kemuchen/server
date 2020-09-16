package cn.framework.system.dao.auto.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import java.util.Date;

import cn.framework.common.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/***
 * @Description: 登录历史信息表
 * @Author: Administrator
 * @Date: 2020/05/11 09:59
 */
@ApiModel(value = "登录历史信息表", description = "数据库表反向工具生成对应表sys_login_history")
@Getter
@Setter
public class SysLoginHistoryEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;

    // 登录IP
    @ApiModelProperty(value = "登录IP", hidden = true)
    private String login_ip;

    // 登录结果
    @ApiModelProperty(value = "登录结果", hidden = true)
    private String login_result;

    // 登录时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "登录时间", hidden = true)
    private Date login_time;

    // 登录时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "登录时间对比时间 登录时间 >= 传入时间", hidden = true)
    private Date login_time_sta;

    // 登录时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "登录时间对比时间 登录时间 <= 传入时间", hidden = true)
    private Date login_time_end;

    // 会话token
    @ApiModelProperty(value = "会话token", hidden = true)
    private String token;

    // 用户id
    @ApiModelProperty(value = "用户id", hidden = true)
    private Integer userid;

}