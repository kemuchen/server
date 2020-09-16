package cn.framework.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName BasePojo
 * @Desc
 * @Author 柯雷
 * @Date 2020-05-05 17:57
 * @Version 1.0
 */
@ApiModel(value = "前端基础实体", description = "前端基础实体")
@Getter
@Setter
public class BasePojo implements Serializable {

    private static final long serialVersionUID = 1L;

    // 修改人
    @ApiModelProperty(value = "修改人", example = "1")
    private Integer modify_user;
}
