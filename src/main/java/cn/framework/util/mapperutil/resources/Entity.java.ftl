package ${entity_package};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

import java.util.Date;
import java.math.BigDecimal;
import cn.framework.common.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/***
* @Description: ${table_comment}
* @Author: ${author}
* @Date: ${currentTime}
*/
@ApiModel(value = "${table_comment}", description = "数据库表反向工具生成对应表${table_name}")
@Getter
@Setter
public class ${table_name_upper}Entity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

<#list columns as column>
    <#if column.column_name != "create_time" && column.column_name != "create_user" && column.column_name != "memo" && column.column_name != "modify_time" && column.column_name != "modify_user" && column.column_name != "valid">
        <#if column.column_java_type == 'Date'>
    // ${column.column_comment}
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "${column.column_comment}", hidden=true)
    private ${column.column_java_type} ${column.column_name};

    // ${column.column_comment}
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "${column.column_comment}对比时间 ${column.column_comment} >= 传入时间", hidden=true)
    private ${column.column_java_type} ${column.column_name}_sta;

    // ${column.column_comment}
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "${column.column_comment}对比时间 ${column.column_comment} <= 传入时间", hidden=true)
    private ${column.column_java_type} ${column.column_name}_end;

        <#else>
    // ${column.column_comment}
    @ApiModelProperty(value = "${column.column_comment}", hidden=true)
    private ${column.column_java_type} ${column.column_name};

        </#if>
    </#if>
</#list>
}