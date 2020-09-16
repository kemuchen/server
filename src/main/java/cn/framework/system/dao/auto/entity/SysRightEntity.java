package cn.framework.system.dao.auto.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import cn.framework.common.entity.BaseEntity;

/***
 * @Description: 权限信息表
 * @Author: Administrator
 * @Date: 2020/04/27 05:18
 */
@ApiModel(value = "权限信息表", description = "数据库表反向工具生成对应表sys_right")
@Getter
@Setter
public class SysRightEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 是否校验权限
    @ApiModelProperty(value = "是否校验权限", hidden = true)
    private String authed;

    // 权限图标
    @ApiModelProperty(value = "权限图标", hidden = true)
    private String icon;

    // 主键
    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;

    // 父菜单id
    @ApiModelProperty(value = "父菜单id", hidden = true)
    private Integer parent_id;

    // 权限名称
    @ApiModelProperty(value = "权限名称", hidden = true)
    private String right_name;

    // 权限类别
    @ApiModelProperty(value = "权限类别", hidden = true)
    private String right_type;

    // 权限链接
    @ApiModelProperty(value = "权限链接", hidden = true)
    private String right_url;

    // 权限排序
    @ApiModelProperty(value = "权限排序", hidden = true)
    private Integer sort_no;

    // 子系统类别
    @ApiModelProperty(value = "子系统类别", hidden = true)
    private String sub_sys_type;

}