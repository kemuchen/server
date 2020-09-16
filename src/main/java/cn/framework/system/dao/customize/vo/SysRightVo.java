package cn.framework.system.dao.customize.vo;

import cn.framework.system.dao.auto.entity.SysRightEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SysRightPojo
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-28 11:28
 * @Version 1.0
 */
@ApiModel(value = "SysRightVo", description = "系统菜单POJO")
@Getter
@Setter
public class SysRightVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单信息", hidden = true, dataType = "SysRightEntity")
    private SysRightEntity right;

    // 子菜单
    @ApiModelProperty(value = "子菜单", hidden = true, dataType = "SysRightEntity")
    private List<SysRightEntity> sub_sys_right;
}
