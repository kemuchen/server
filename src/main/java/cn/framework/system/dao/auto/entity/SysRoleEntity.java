package cn.framework.system.dao.auto.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import cn.framework.common.entity.BaseEntity;

/***
 * @Description: 角色信息表
 * @Author: Administrator
 * @Date: 2020/04/27 05:18
 */
@ApiModel(value = "角色信息表", description = "数据库表反向工具生成对应表sys_role")
@Getter
@Setter
public class SysRoleEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 角色描述
    @ApiModelProperty(value = "角色描述", hidden = true)
    private String description;

    // 主键
    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;

    // 角色名称
    @ApiModelProperty(value = "角色名称", hidden = true)
    private String name;

}