package cn.framework.common.dao.auto.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import cn.framework.common.entity.BaseEntity;

/***
 * @Description: 表记录修改日志
 * @Author: Administrator
 * @Date: 2020/04/28 03:10
 */
@ApiModel(value = "表记录修改日志", description = "数据库表反向工具生成对应表table_update_log")
@Getter
@Setter
public class TableUpdateLogEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // ID
    @ApiModelProperty(value = "ID", hidden = true)
    private Integer id;

    // 修改表名
    @ApiModelProperty(value = "修改表名", hidden = true)
    private String table_name;

    // 表名中文描述
    @ApiModelProperty(value = "表名中文描述", hidden = true)
    private String table_name_desc;

    // 修改表的主键  外键，对应修改表的主键
    @ApiModelProperty(value = "修改表的主键  外键，对应修改表的主键", hidden = true)
    private Integer update_table_id;

}