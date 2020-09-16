package cn.framework.common.dao.auto.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import cn.framework.common.entity.BaseEntity;

/***
 * @Description: 表记录修改明细
 * @Author: Administrator
 * @Date: 2020/04/28 03:10
 */
@ApiModel(value = "表记录修改明细", description = "数据库表反向工具生成对应表table_update_log_detail")
@Getter
@Setter
public class TableUpdateLogDetailEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // ID
    @ApiModelProperty(value = "ID", hidden = true)
    private Integer id;

    // 代码操作日志表id 外键，对应code_update_log表主键
    @ApiModelProperty(value = "代码操作日志表id 外键，对应code_update_log表主键", hidden = true)
    private Integer table_update_log_id;

    // 修改字段
    @ApiModelProperty(value = "修改字段", hidden = true)
    private String field;

    // 字段中文描述
    @ApiModelProperty(value = "字段中文描述", hidden = true)
    private String field_name;

    // 是否字典项 1-是，0-否
    @ApiModelProperty(value = "是否字典项 1-是，0-否", hidden = true)
    private String is_dict;

    // 修改前值
    @ApiModelProperty(value = "修改前值", hidden = true)
    private String old_value;

    // 修改后值
    @ApiModelProperty(value = "修改后值", hidden = true)
    private String new_value;

    // 修改前字典中文描述
    @ApiModelProperty(value = "修改前字典中文描述", hidden = true)
    private String old_value_name;

    // 修改后字典中文描述
    @ApiModelProperty(value = "修改后字典中文描述", hidden = true)
    private String new_value_name;

}