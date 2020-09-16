package cn.framework.system.dao.customize.mapper;

import cn.framework.system.dao.auto.entity.SysRightEntity;
import cn.framework.system.dao.customize.vo.SysRightSelectVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单权限dao
 *
 * @author YCJ
 */
@Repository
public interface CustomizeSysRightMapper {
    /**
     * @param ：@param loginid
     * @return ：List<Map<String,Object>>
     * @throws
     * @Title：getRightsByLoginid
     * @Description：根据登录id查询用户权限
     */
    List<SysRightEntity> getRightsByUserId(SysRightSelectVo sysRightSelectPojo);
}