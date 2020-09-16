package cn.framework.common.dao.auto.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import cn.framework.common.entity.BaseEntity;

/***
 * @Description: 动态字典配置表
 * @Author: Administrator
 * @Date: 2020/04/29 12:18
 */
@ApiModel(value = "动态字典配置表", description = "数据库表反向工具生成对应表sys_dynamic_dict_config")
@Getter
@Setter
public class SysDynamicDictConfigEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;

    // 配置id
    @ApiModelProperty(value = "配置id", hidden = true)
    private String configid;

    // 多记录查询语句
    @ApiModelProperty(value = "多记录查询语句", hidden = true)
    private String multsql;

    // 查询条件参数
    @ApiModelProperty(value = "查询条件参数", hidden = true)
    private String params;

    // 动态字典描述
    @ApiModelProperty(value = "动态字典描述", hidden = true)
    private String description;

}