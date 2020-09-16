package cn.framework.common.service.inter;

import cn.framework.common.dao.auto.entity.SysDynamicDictConfigEntity;
import cn.framework.common.dao.customize.vo.ExportExcelVo;
import cn.framework.common.dao.customize.vo.ImportExcelVo;
import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @ClassName BusinessCommonServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-29 12:04
 * @Version 1.0
 */
public interface BusinessCommonServiceInterface {

    /**
     * @Description: 查询动态字典信息
     * @Params: [sysDynamicDictConfigEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-29 12:07
     */
    ApiResponseResultEntity getDynamicDict(SysDynamicDictConfigEntity sysDynamicDictConfigEntity) throws AppException;

    /**
     * @Description: 查询静态字典
     * @Params: [dicttype]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-29 15:42
     */
    ApiResponseResultEntity getDict(String dicttype) throws AppException;

    /**
     * @Description: 文件上传
     * @Params: [filePath, files]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-29 17:38
     */
    ApiResponseResultEntity fileUpload(String filePath, MultipartFile[] files) throws AppException;

    /**
     * @Description: 文件上传(Base64格式)
     * @Params: [params]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-29 17:39
     */
    ApiResponseResultEntity fileUploadBase64(Map<String, Object> params) throws AppException;

    /**
     * @Description: 校验token
     * @Params: [token, userid]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-12 9:12
     */
    ApiResponseResultEntity checkToken(String token, Integer userid) throws AppException;

    /**
     * @Description: 导出Excel报表
     * @Params: [exportExcelVo]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-14 12:11
     */
    ApiResponseResultEntity exportExcel(ExportExcelVo exportExcelVo) throws AppException;

    /**
     * @Description: 导入Excel文件数据
     * @Params: [importExcelVo]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-14 12:15
     */
    ApiResponseResultEntity importExcel(ImportExcelVo importExcelVo) throws AppException;
}
