package cn.framework.system.dao.customize.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName SysRightSelectPojo
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-28 11:59
 * @Version 1.0
 */
@ApiModel(value = "SysRightSelectVo", description = "菜单查询条件POJO")
@Getter
@Setter
public class SysRightSelectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id", example = "1")
    private Integer user_id;

    @ApiModelProperty(value = "父菜单id", example = "1")
    private Integer parent_id;

    @ApiModelProperty(value = "菜单类型", example = "1")
    private String right_type;
}
