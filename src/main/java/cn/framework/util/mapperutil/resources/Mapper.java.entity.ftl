package ${dao_package};

import org.springframework.stereotype.Repository;

import java.util.List;
import ${entity_package}.${table_name_upper}Entity;

/**
* @author ：${author}
* @ClassName:：${table_name_upper}Mapper
* @Description：${table_comment}Dao
* @date ：${currentTime}
*/
@Repository
public interface ${table_name_upper}Mapper {

    /***
    * @Description 新增${table_comment}
    * @Date ${currentTime}
    * @Param [params]
    * @return void
    **/
    void insert${table_name_upper}(${table_name_upper}Entity ${table_name});

    /**
    * @Description 删除${table_comment}
    * @Date ${currentTime}
    * @Param [id]
    * @return void
    **/
    void delete${table_name_upper}(Integer id);

    /**
    * @Description 更新${table_comment}
    * @Date ${currentTime}
    * @Param [params]
    * @return void
    **/
    void update${table_name_upper}(${table_name_upper}Entity ${table_name});

    /**
    * @Description 根据id查询${table_comment}: 单条
    * @Date ${currentTime}
    * @Param [id]
    * @return java.util.Map
    * <java.lang.Integer>
    **/
    ${table_name_upper}Entity select${table_name_upper}ById(Integer id);

    /**
    * @Description 根据条件查询${table_comment}：多条
    * @Date ${currentTime}
    * @Param [params]
    * @return java.util.List
    **/
    List <${table_name_upper}Entity> get${table_name_upper}s(${table_name_upper}Entity ${table_name});

    /***
    * @Description 查询数量
    * @Date ${currentTime}
    * @Param [params]
    * @return java.lang.Integer
    **/
    Integer get${table_name_upper}sCount(${table_name_upper}Entity ${table_name});
}