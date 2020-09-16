package cn.framework.system.dao.auto.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import cn.framework.common.entity.BaseEntity;

/***
 * @Description: 行政区划信息表
 * @Author: Administrator
 * @Date: 2020/04/27 05:18
 */
@ApiModel(value = "行政区划信息表", description = "数据库表反向工具生成对应表sys_administrative_division")
@Getter
@Setter
public class SysAdministrativeDivisionEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 行政区划名称
    @ApiModelProperty(value = "行政区划名称", hidden = true)
    private String division_name;

    // 行政区划号码
    @ApiModelProperty(value = "行政区划号码", hidden = true)
    private String division_no;

    // 全称
    @ApiModelProperty(value = "全称", hidden = true)
    private String full_name;

    // 主键
    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;

    // 精度
    @ApiModelProperty(value = "精度", hidden = true)
    private String latitude;

    // 纬度
    @ApiModelProperty(value = "纬度", hidden = true)
    private String longtitude;

    // 父行政区划id
    @ApiModelProperty(value = "父行政区划id", hidden = true)
    private Integer parent_division_id;

}