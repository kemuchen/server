package cn.framework.common.dao.customize.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName ExportExcelVo
 * @Desc 报表导出Excel
 * @Author 柯雷
 * @Date 2020-05-14 12:06
 * @Version 1.0
 */
@ApiModel(value = "ExportExcelVo", description = "报表导出Excel")
@Getter
@Setter
public class ExportExcelVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "导出配置代码", example = "config_code")
    private String config_code;

    @ApiModelProperty(value = "导出数据查询条件参数", example = "{key1 : value1, key2 : value2}")
    private Map params;
}
