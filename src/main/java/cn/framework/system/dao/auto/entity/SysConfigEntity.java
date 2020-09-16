package cn.framework.system.dao.auto.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import cn.framework.common.entity.BaseEntity;

/***
 * @Description: 系统参数配置表
 * @Author: Administrator
 * @Date: 2020/04/27 05:18
 */
@ApiModel(value = "系统参数配置表", description = "数据库表反向工具生成对应表sys_config")
@Getter
@Setter
public class SysConfigEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 参数说明
    @ApiModelProperty(value = "参数说明", hidden = true)
    private String config_desc;

    // 参数名称
    @ApiModelProperty(value = "参数名称", hidden = true)
    private String config_name;

    // 参数值
    @ApiModelProperty(value = "参数值", hidden = true)
    private String config_value;

    // 主键
    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;

}