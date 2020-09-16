package cn.framework.system.service.impl.right;

import cn.framework.Constants;
import cn.framework.common.entity.ApiResponseResultEntity;
import cn.framework.security.exception.AppException;
import cn.framework.security.exception.codes.SysErrorCode;
import cn.framework.system.dao.auto.entity.SysRightEntity;
import cn.framework.system.dao.customize.vo.SysRightVo;
import cn.framework.system.service.inter.right.BusinessRightServiceInterface;
import cn.framework.system.service.inter.right.SysRightServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BusinessRightServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-28 11:32
 * @Version 1.0
 */
@Service
public class BusinessRightServiceImplements implements BusinessRightServiceInterface {

    /**
     * 日志输出对象
     */
    Logger logger = LoggerFactory.getLogger(BusinessRightServiceImplements.class);

    /**
     * 菜单基础操作service
     */
    @Autowired
    SysRightServiceInterface sysRightService;

    /**
     * @Description: 根据用户id查询菜单信息
     * @Params: [userId]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 11:48
     */
    @Override
    public ApiResponseResultEntity getRightsByUserId(Integer userId) throws AppException {
        try {
            return sysRightService.getRightsByUserId(userId);
        } catch (AppException e) {
            logger.error("【BusinessRightServiceImplements.getRightsByUserId】根据用户id查询菜单信息出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessRightServiceImplements.getRightsByUserId】根据用户id查询菜单信息出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询格式化菜单信息
     * @Params: [userId]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 11:46
     */
    @Override
    public ApiResponseResultEntity getRightsByFormat() throws AppException {
        try {
            ApiResponseResultEntity apiResponseResultEntity = new ApiResponseResultEntity(SysErrorCode.SUCCESS);
            List<SysRightVo> rights = new ArrayList<>();

            // 查询一级菜单
            SysRightEntity sysRightEntity = new SysRightEntity();
            sysRightEntity.setParent_id(0);
            sysRightEntity.setValid(Constants.YES);

            List<SysRightEntity> firstRights = this.selectRights(sysRightEntity);

            // 二级菜单
            List<SysRightEntity> secondRights;
            // 遍历一级菜单获取二级菜单
            for (SysRightEntity rightEntity : firstRights) {
                sysRightEntity.setParent_id(rightEntity.getId());
                secondRights = this.selectRights(sysRightEntity);
                SysRightVo sysRightPojo = new SysRightVo();
                sysRightPojo.setRight(rightEntity);
                sysRightPojo.setSub_sys_right(secondRights);
                rights.add(sysRightPojo);
            }

            apiResponseResultEntity.setData(rights);
            return apiResponseResultEntity;
        } catch (AppException e) {
            logger.error("【BusinessRightServiceImplements.getRightsByFormat】查询格式化菜单信息出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessRightServiceImplements.getRightsByFormat】查询格式化菜单信息出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询菜单
     * @Params: [sysRightEntity]
     * @return: cn.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-04-28 11:35
     */
    @Override
    public ApiResponseResultEntity getRights(SysRightEntity sysRightEntity) throws AppException {
        try {
            ApiResponseResultEntity apiResponseResultEntity = new ApiResponseResultEntity(SysErrorCode.SUCCESS);
            apiResponseResultEntity.setData(this.selectRights(sysRightEntity));
            return apiResponseResultEntity;
        } catch (Exception e) {
            logger.error("【BusinessRightServiceImplements.getRights】查询菜单出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询菜单
     * @Params: [sysRightEntity]
     * @return: java.util.List<cn.framework.system.dao.auto.entity.SysRightEntity>
     * @Author: 柯雷
     * @Date: 2020-04-28 11:37
     */
    private List<SysRightEntity> selectRights(SysRightEntity sysRightEntity) throws AppException {
        try {
            return (List<SysRightEntity>) sysRightService.getRights(sysRightEntity).getData();
        } catch (Exception e) {
            logger.error("【BusinessRightServiceImplements.selectRights】查询菜单出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

}
