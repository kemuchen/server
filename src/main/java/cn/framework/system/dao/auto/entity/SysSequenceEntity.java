package cn.framework.system.dao.auto.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import cn.framework.common.entity.BaseEntity;

/***
 * @Description: 序列信息表
 * @Author: Administrator
 * @Date: 2020/04/27 05:18
 */
@ApiModel(value = "序列信息表", description = "数据库表反向工具生成对应表sys_sequence")
@Getter
@Setter
public class SysSequenceEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 是否循环序列
    @ApiModelProperty(value = "是否循环序列", hidden = true)
    private String circle;

    // 序列当前值
    @ApiModelProperty(value = "序列当前值", hidden = true)
    private Integer current_value;

    // 主键
    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;

    // 序列增长量
    @ApiModelProperty(value = "序列增长量", hidden = true)
    private Integer increment;

    // 序列最大值
    @ApiModelProperty(value = "序列最大值", hidden = true)
    private Integer max_value;

    // 序列名称
    @ApiModelProperty(value = "序列名称", hidden = true)
    private String name;

    // 序列
    @ApiModelProperty(value = "序列", hidden = true)
    private String sequence;

}