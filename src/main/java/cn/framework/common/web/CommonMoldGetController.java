package cn.framework.common.web;

import cn.framework.common.dao.auto.entity.SysDynamicDictConfigEntity;
import cn.framework.common.dao.customize.vo.ExportExcelVo;
import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.common.service.inter.BusinessCommonServiceInterface;
import cn.framework.security.exception.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CommonMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-29 11:55
 * @Version 1.0
 */
@Api(tags = "公共查询接口")
@RestController
@RequestMapping("/api/common/")
public class CommonMoldGetController {

    /**
     * 公共业务操作service
     */
    @Autowired
    BusinessCommonServiceInterface businessCommonServiceInterface;

    /**
     * @Description: 查询动态字典信息
     * @Params: [sysDynamicDictConfigEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-29 12:06
     */
    @ApiOperation(("1.查询动态字典信息"))
    @PutMapping(value = "dynamicDict", produces = "application/json")
    public ApiResponseResultEntity getDynamicDict(SysDynamicDictConfigEntity sysDynamicDictConfigEntity) throws AppException {
        return businessCommonServiceInterface.getDynamicDict(sysDynamicDictConfigEntity);
    }

    /**
     * @Description: 查询静态字典
     * @Params: [dicttype]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-29 15:42
     */
    @ApiOperation("2.查询静态字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dicttype", value = "字典类型", required = true, paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping(value = "dict/{dicttype}", produces = "application/json")
    public ApiResponseResultEntity getDict(@PathVariable(value = "dicttype") String dicttype) throws AppException {
        return businessCommonServiceInterface.getDict(dicttype);
    }

    /**
     * @Description:
     * @Params: [dicttype]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-14 11:59
     */
    @ApiOperation("2.导出Excel报表")
    @GetMapping(value = "excel", produces = "application/json")
    public ApiResponseResultEntity excel(ExportExcelVo exportExcelVo) throws AppException {
        return businessCommonServiceInterface.exportExcel(exportExcelVo);
    }
}
