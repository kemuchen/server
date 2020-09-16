package cn.framework.common.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.framework.Constants;
import cn.framework.common.dao.auto.entity.SysDynamicDictConfigEntity;
import cn.framework.common.dao.auto.mapper.SysDynamicDictConfigMapper;
import cn.framework.common.dao.customize.mapper.CustomizeSysLoginHistoryMapper;
import cn.framework.common.dao.customize.vo.ExportExcelVo;
import cn.framework.common.dao.customize.vo.ImportExcelVo;
import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.common.service.inter.BusinessCommonServiceInterface;
import cn.framework.security.exception.AppException;
import cn.framework.security.exception.codes.LoginErrorCode;
import cn.framework.security.exception.codes.SysErrorCode;
import cn.framework.system.dao.auto.entity.SysDictEntity;
import cn.framework.system.dao.auto.mapper.SysDictMapper;
import cn.framework.system.dao.customize.mapper.CustomizeSysCommonMapper;
import cn.framework.util.sys.SysUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName BusinessCommonServcieImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-29 12:04
 * @Version 1.0
 */
@Service
public class BusinessCommonServiceImplements implements BusinessCommonServiceInterface {

    /**
     * 日志输出对象
     */
    Logger logger = LoggerFactory.getLogger(BusinessCommonServiceImplements.class);

    /**
     * 动态字典项CURD操作对象
     */
    @Autowired
    SysDynamicDictConfigMapper sysDynamicDictConfigMapper;

    /**
     * 自定义CURD操作
     */
    @Autowired
    CustomizeSysCommonMapper customizeSysCommonMapper;

    /**
     * 静态字典CURD操作对象
     */
    @Autowired
    SysDictMapper sysDictMapper;

    /**
     * spring boot上下文环境
     */
    @Autowired
    Environment environment;

    @Autowired
    CustomizeSysLoginHistoryMapper customizeSysLoginHistoryMapper;

    /**
     * @Description: 获取动态字典信息
     * @Params: [sysDynamicDictConfigEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-29 12:23
     */
    @Override
    public ApiResponseResultEntity getDynamicDict(SysDynamicDictConfigEntity sysDynamicDictConfigEntity) throws AppException {
        ApiResponseResultEntity apiResponseResultEntity = new ApiResponseResultEntity();

        try {
            String params = SysUtil.nvl(sysDynamicDictConfigEntity.getParams(), "");
            /**
             * 校验查询条件中是否包含增删改查关键字，如有则不允许进行操作，防止sql注入
             */
            if (params.toLowerCase().indexOf("insert") > 0 || params.toLowerCase().indexOf("delete") > 0 ||
                    params.toLowerCase().indexOf("update") > 0 || params.toLowerCase().indexOf("select") > 0) {
                throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(),
                        SysErrorCode.SERVICE_CHECK_ERROR.getError_desc() + ":查询条件中包含非法字符，请检查!", "查询条件中包含非法字符，请检查!");
            } else {
                sysDynamicDictConfigEntity.setParams(null);
                sysDynamicDictConfigEntity.setModify_user(null);

                // 获取查询语句
                List<SysDynamicDictConfigEntity> sysDynamicDictConfigEntities = sysDynamicDictConfigMapper.getSysDynamicDictConfigs(sysDynamicDictConfigEntity);

                /** 没有配置的动态语句 */
                if (SysUtil.isEmpty(sysDynamicDictConfigEntities) || sysDynamicDictConfigEntities.size() == 0) {
                    throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(),
                            SysErrorCode.SERVICE_CHECK_ERROR.getError_desc() + ":无此配置的动态字典", "无此配置的动态字典");
                }
                /** 动态字典配置重复 */
                if (sysDynamicDictConfigEntities.size() > 1) {
                    throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(),
                            SysErrorCode.SERVICE_CHECK_ERROR.getError_desc() + ":动态字典配置重复", "动态字典配置重复");
                }
                sysDynamicDictConfigEntity = sysDynamicDictConfigEntities.get(0);

                // 构造sql语句
                String sql = sysDynamicDictConfigEntity.getMultsql();
                Integer paramsCount = StrUtil.count(sql, "?");

                // 如果有参数则设置参数
                if (paramsCount > 0) {
                    Map<String, Object> parameters = (Map<String, Object>) JSONObject.parse(params);
                    if (parameters.keySet().size() != paramsCount) {
                        throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(),
                                SysErrorCode.SERVICE_CHECK_ERROR.getError_desc() + ":动态字典查询参数不正确", "动态字典查询参数不正确");
                    }
                    Set<String> keys = parameters.keySet();
                    for (String key : keys) {
                        if (parameters.get(key) instanceof String) {
                            sql = sql.replaceFirst("\\?", new StringBuilder("'").append(parameters.get(key)).append("'").toString());
                        } else {
                            sql = sql.replaceFirst("\\?", parameters.get(key).toString());
                        }
                    }
                }

                apiResponseResultEntity.setData(customizeSysCommonMapper.execDynamicSql(sql));
                apiResponseResultEntity.setCode(SysErrorCode.SUCCESS.getError_code());

            }
            return apiResponseResultEntity;
        } catch (AppException e) {
            logger.error("【BusinessCommonServiceImplements.getDynamicDict】获取动态字典出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessCommonServiceImplements.getDynamicDict】获取动态字典出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询静态字典
     * @Params: [dicttype]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-29 15:43
     */
    @Override
    public ApiResponseResultEntity getDict(String dicttype) throws AppException {
        ApiResponseResultEntity apiResponseResultEntity = new ApiResponseResultEntity();
        try {
            apiResponseResultEntity.setCode(SysErrorCode.SUCCESS.getError_code());
            SysDictEntity sysDictEntity = new SysDictEntity();
            sysDictEntity.setValid(Constants.YES);
            sysDictEntity.setDicttype(dicttype);
            apiResponseResultEntity.setData(sysDictMapper.getSysDicts(sysDictEntity));
            return apiResponseResultEntity;
        } catch (Exception e) {
            logger.error("【BusinessCommonServiceImplements.getDict】获取静态字典出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 文件上传
     * @Params: [filePath, files]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-29 17:40
     */
    @Override
    public ApiResponseResultEntity fileUpload(String filePath, MultipartFile[] files) throws AppException {
        ApiResponseResultEntity apiResponseResultEntity = new ApiResponseResultEntity();

        String root = environment.getProperty("file.location");
        String web = environment.getProperty("file.fileweb");

        if (SysUtil.isEmpty(root) || SysUtil.isEmpty(web)) {
            throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(),
                    SysErrorCode.SERVICE_CHECK_ERROR.getError_desc() + ":未设置上传文件存储路径", "请设置上传文件路径");
        }
        // 文件输出流
        OutputStream outputStream = null;
        // 文件输入流
        InputStream inputStream = null;
        try {
            // 判断文件夹是否存在，不存在则新增
            File dir = new File(root + filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            List<String> images = new ArrayList<>();
            for (MultipartFile file : files) {
                //获取输出流
                outputStream = new FileOutputStream(root + filePath + file.getOriginalFilename());
                //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
                inputStream = file.getInputStream();
                int temp;
                //一个一个字节的读取并写入
                while ((temp = inputStream.read()) != -1) {
                    outputStream.write(temp);
                }
                outputStream.flush();
                images.add(web + filePath + file.getOriginalFilename());
            }

            // 构造返回数据
            apiResponseResultEntity.setCode(SysErrorCode.SUCCESS.getError_code());
            apiResponseResultEntity.setMessage("上传成功");
            apiResponseResultEntity.setData(images);
            return apiResponseResultEntity;
        } catch (Exception e) {
            logger.error("【BusinessCommonServiceImplements.fileUpload】文件上传出错:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                logger.error("【BusinessCommonServiceImplements.fileUpload】文件上传出错:" + e);
                throw new AppException(SysErrorCode.SYSTEM_ERROR);
            }
        }
    }

    /**
     * @Description: 文件上传(以Base64格式上传)
     * @Params: [params]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-29 17:40
     */
    @Override
    public ApiResponseResultEntity fileUploadBase64(Map<String, Object> params) throws AppException {
        ApiResponseResultEntity apiResponseResultEntity = new ApiResponseResultEntity();

        String root = environment.getProperty("file.location");
        String web = environment.getProperty("file.fileweb");
        if (SysUtil.isEmpty(root) || SysUtil.isEmpty(web)) {
            throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(),
                    SysErrorCode.SERVICE_CHECK_ERROR.getError_desc() + ":未设置上传文件存储路径", "请设置上传文件路径");
        }
        // 文件输出流
        OutputStream outputStream = null;
        // 文件输入流
        InputStream inputStream = null;
        try {
            // 判断文件夹是否存在，不存在则新增
            File dir = new File(root + params.get("filePath"));
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String image = root + params.get("filePath") + params.get("imageName");
            SysUtil.base64ToImage(params.get("image").toString(), image);
            // 构造返回数据
            apiResponseResultEntity.setCode(SysErrorCode.SUCCESS.getError_code());
            apiResponseResultEntity.setMessage("上传成功");
            apiResponseResultEntity.setData(root + params.get("filePath") + params.get("imageName"));
            return apiResponseResultEntity;
        } catch (Exception e) {
            logger.error("【BusinessCommonServiceImplements.fileUploadBase64】文件上传出错:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                logger.error("【BusinessCommonServiceImplements.fileUploadBase64】文件上传出错:" + e);
                throw new AppException(SysErrorCode.SYSTEM_ERROR);
            }
        }
    }

    /**
     * @Description: 校验token
     * @Params: [token, userid]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-12 9:17
     */
    @Override
    public ApiResponseResultEntity checkToken(String token, Integer userid) throws AppException {
        try {
            String loginToken = customizeSysLoginHistoryMapper.getLatestLoginHistory(userid);
            if (SysUtil.isEmpty(token) || SysUtil.isEmpty(loginToken)) {
                // 未登录
                return new ApiResponseResultEntity(LoginErrorCode.UN_LOGIN);
            }
            if (!token.equals(loginToken)) {
                // 已在其他地方登录，强制退出
                return new ApiResponseResultEntity(LoginErrorCode.MORE_THEN_ONE_LOGIN);
            }
            return new ApiResponseResultEntity(LoginErrorCode.LOGIN_SUCCESS);
        } catch (Exception e) {
            logger.error("【BusinessCommonServiceImplements.checkToken】校验token是否正确出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 导出Excel报表数据
     * @Params: [exportExcelVo]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-14 12:16
     */
    @Override
    public ApiResponseResultEntity exportExcel(ExportExcelVo exportExcelVo) throws AppException {
        return null;
    }

    /**
     * @Description: 导入Excel报表数据
     * @Params: [importExcelVo]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-05-14 12:16
     */
    @Override
    public ApiResponseResultEntity importExcel(ImportExcelVo importExcelVo) throws AppException {
        return null;
    }
}
