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
 * @Description: 用户信息表
 * @Author: Administrator
 * @Date: 2020/05/12 05:32
 */
@ApiModel(value = "用户信息表", description = "数据库表反向工具生成对应表sys_user")
@Getter
@Setter
public class SysUserEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;

    // 用户名
    @ApiModelProperty(value = "用户名", hidden = true)
    private String username;

    // 登录id
    @ApiModelProperty(value = "登录id", hidden = true)
    private String loginid;

    // 登录密码
    @ApiModelProperty(value = "登录密码", hidden = true)
    private String password;

    // 手机号码
    @ApiModelProperty(value = "手机号码", hidden = true)
    private String phone;

    // 用户类型
    @ApiModelProperty(value = "用户类型", hidden = true)
    private String user_type;

    // 邮箱
    @ApiModelProperty(value = "邮箱", hidden = true)
    private String email;

    // 头像
    @ApiModelProperty(value = "头像", hidden = true)
    private String avator;

    // 用户状态
    @ApiModelProperty(value = "用户状态", hidden = true)
    private String status;

    // 登录失败次数
    @ApiModelProperty(value = "登录失败次数", hidden = true)
    private Integer login_fail_count;

    // 最近一次登录时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最近一次登录时间", hidden = true)
    private Date last_login_time;

    // 最近一次登录时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最近一次登录时间对比时间 最近一次登录时间 >= 传入时间", hidden = true)
    private Date last_login_time_sta;

    // 最近一次登录时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最近一次登录时间对比时间 最近一次登录时间 <= 传入时间", hidden = true)
    private Date last_login_time_end;

}