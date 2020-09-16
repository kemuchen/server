package cn.framework.system.dao.customize.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author ：柯雷
 * @ClassName:：CommonMapper
 * @Description： 公共的处理DAO
 * @date ：2018年9月25日 下午5:28:51
 */
@Repository
public interface CustomizeSysCommonMapper {

    /**
     * @param ：@param  sql
     * @param ：@return
     * @return ：String
     * @throws
     * @Title：getDynamicDicts
     * @Description：执行动态语句
     */
    List<Map<String, Object>> execDynamicSql(@Param("dynamicSql") String dynamicSql);
}