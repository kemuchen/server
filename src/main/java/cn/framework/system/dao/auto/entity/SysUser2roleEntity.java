package cn.framework.system.dao.auto.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import cn.framework.common.entity.BaseEntity;

/***
 * @Description: 用户角色表
 * @Author: Administrator
 * @Date: 2020/05/12 05:32
 */
@ApiModel(value = "用户角色表", description = "数据库表反向工具生成对应表sys_user2role")
@Getter
@Setter
public class SysUser2roleEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;

    // 用户id
    @ApiModelProperty(value = "用户id", hidden = true)
    private Integer userid;

    // 角色id
    @ApiModelProperty(value = "角色id", hidden = true)
    private Integer roleid;

}