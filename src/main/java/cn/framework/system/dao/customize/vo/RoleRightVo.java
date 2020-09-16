package cn.framework.system.dao.customize.vo;

import cn.framework.system.dao.auto.entity.SysRightEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName RoleRightVo
 * @Desc
 * @Author 柯雷
 * @Date 2020-09-15 17:47
 * @Version 1.0
 */
@ApiModel(value = "RoleRightVo", description = "角色权限VO")
@Getter
@Setter
public class RoleRightVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "删除菜单id列表", hidden = true, dataType = "removeIds")
    private String removeIds;

    @ApiModelProperty(value = "添加菜单id列表", hidden = true, dataType = "addRightIds")
    private String addRightIds;

    @ApiModelProperty(value = "角色id", hidden = true, dataType = "addRoleId")
    private Integer addRoleId;

    @ApiModelProperty(value = "用户id", hidden = true, dataType = "userId")
    private Integer userId;
}
