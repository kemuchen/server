package cn.framework.system.dao.auto.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import cn.framework.common.entity.BaseEntity;

/***
 * @Description: 字典信息表
 * @Author: Administrator
 * @Date: 2020/04/27 05:18
 */
@ApiModel(value = "字典信息表", description = "数据库表反向工具生成对应表sys_dict")
@Getter
@Setter
public class SysDictEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 字典项代码描述
    @ApiModelProperty(value = "字典项代码描述", hidden = true)
    private String description;

    // 字典项代码
    @ApiModelProperty(value = "字典项代码", hidden = true)
    private String dictcode;

    // 字典项类别
    @ApiModelProperty(value = "字典项类别", hidden = true)
    private String dicttype;

    // 主键
    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;

    // 字典项类型名称
    @ApiModelProperty(value = "字典项类型名称", hidden = true)
    private String typename;

}