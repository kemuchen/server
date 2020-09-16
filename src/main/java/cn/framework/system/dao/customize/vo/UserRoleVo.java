package cn.framework.system.dao.customize.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName UserRoleVo
 * @Desc
 * @Author 柯雷
 * @Date 2020-09-15 17:47
 * @Version 1.0
 */
@ApiModel(value = "UserRoleVo", description = "用户角色VO")
@Getter
@Setter
public class UserRoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "删除角色id列表", hidden = true, dataType = "removeIds")
    private String removeIds;

    @ApiModelProperty(value = "添加菜单id列表", hidden = true, dataType = "addRoleIds")
    private String addRoleIds;

    @ApiModelProperty(value = "授权用户id", hidden = true, dataType = "addUserId")
    private Integer addUserId;

    @ApiModelProperty(value = "用户id", hidden = true, dataType = "userId")
    private Integer userId;
}
