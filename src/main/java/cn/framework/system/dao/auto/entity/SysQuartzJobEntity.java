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
 * @Description: 酒店定时器定时器配置
 * @Author: Administrator
 * @Date: 2020/04/28 03:10
 */
@ApiModel(value = "酒店定时器定时器配置", description = "数据库表反向工具生成对应表sys_quartz_job")
@Getter
@Setter
public class SysQuartzJobEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;

    // 任务组
    @ApiModelProperty(value = "任务组", hidden = true)
    private String job_group;

    // 任务类型
    @ApiModelProperty(value = "任务类型", hidden = true)
    private String job_type;

    // 执行任务
    @ApiModelProperty(value = "执行任务", hidden = true)
    private String task;

    // 执行计划
    @ApiModelProperty(value = "执行计划", hidden = true)
    private String schedule;

    // 描述
    @ApiModelProperty(value = "描述", hidden = true)
    private String description;

    // 参数
    @ApiModelProperty(value = "参数", hidden = true)
    private String params;

    // 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "", hidden = true)
    private Date start_time;

    // 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "对比时间  >= 传入时间", hidden = true)
    private Date start_time_sta;

    // 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "对比时间  <= 传入时间", hidden = true)
    private Date start_time_end;

}