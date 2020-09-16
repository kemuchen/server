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
 * @Description: 系统消息表
 * @Author: Administrator
 * @Date: 2020/05/09 10:41
 */
@ApiModel(value = "系统消息表", description = "数据库表反向工具生成对应表sys_message")
@Getter
@Setter
public class SysMessageEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // ID
    @ApiModelProperty(value = "ID", hidden = true)
    private Integer id;

    // 消息类别 sys_dict
    @ApiModelProperty(value = "消息类别 sys_dict", hidden = true)
    private String message_type;

    // 消息标题
    @ApiModelProperty(value = "消息标题", hidden = true)
    private String title;

    // 消息内容
    @ApiModelProperty(value = "消息内容", hidden = true)
    private String content;

    // 是否发送
    @ApiModelProperty(value = "是否发送", hidden = true)
    private String is_send;

    // 消息发送时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "消息发送时间", hidden = true)
    private Date send_time;

    // 消息发送时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "消息发送时间对比时间 消息发送时间 >= 传入时间", hidden = true)
    private Date send_time_sta;

    // 消息发送时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "消息发送时间对比时间 消息发送时间 <= 传入时间", hidden = true)
    private Date send_time_end;

    // 消息接收人 外键，对应sys_user表主键
    @ApiModelProperty(value = "消息接收人 外键，对应sys_user表主键", hidden = true)
    private Integer receive_man;

    // 是否阅读 1-是，0-否
    @ApiModelProperty(value = "是否阅读 1-是，0-否", hidden = true)
    private String is_read;

    // 阅读时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "阅读时间", hidden = true)
    private Date read_time;

    // 阅读时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "阅读时间对比时间 阅读时间 >= 传入时间", hidden = true)
    private Date read_time_sta;

    // 阅读时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "阅读时间对比时间 阅读时间 <= 传入时间", hidden = true)
    private Date read_time_end;

}