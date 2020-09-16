package cn.framework.system.dao.customize.vo;

import cn.framework.system.dao.auto.entity.SysUserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName LoginResultVo
 * @Desc
 * @Author 柯雷
 * @Date 2020-05-11 10:04
 * @Version 1.0
 */
@ApiModel(value = "LoginResultVo", description = "系统菜单POJO")
@Getter
@Setter
public class LoginResultVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户信息", hidden = true, dataType = "SysUserEntity")
    private SysUserEntity user;

    @ApiModelProperty(value = "会话token", hidden = true)
    private String token;
}
