package cn.framework.common.dao.auto.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import cn.framework.common.entity.BaseEntity;

/***
 * @Description:
 * @Author: Administrator
 * @Date: 2020/04/29 09:36
 */
@ApiModel(value = "", description = "数据库表反向工具生成对应表sys_no_rule")
@Getter
@Setter
public class SysNoRuleEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;

    // 单号代码
    @ApiModelProperty(value = "单号代码", hidden = true)
    private String code;

    // 单号描述
    @ApiModelProperty(value = "单号描述", hidden = true)
    private String description;

    // 生成规则
    @ApiModelProperty(value = "生成规则", hidden = true)
    private String rule;

    // 前缀规则
    @ApiModelProperty(value = "前缀规则", hidden = true)
    private String prefix_rule;

    // 后缀规则
    @ApiModelProperty(value = "后缀规则", hidden = true)
    private String suffix_rule;

}