package cn.framework.common.dao.customize.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @ClassName ImportExcelVo
 * @Desc
 * @Author 柯雷
 * @Date 2020-05-14 12:13
 * @Version 1.0
 */
@ApiModel(value = "ImportExcelVo", description = "导入Excel文件报表数据")
@Getter
@Setter
public class ImportExcelVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "导出配置代码", example = "config_code")
    private String config_code;

    @ApiModelProperty(value = "上传的Excel文件", example = "")
    MultipartFile[] files;
}
