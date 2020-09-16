package ${dao_package};

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
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
void insert${table_name_upper}(Map
<String, Object> params);

/**
* @Description 删除${table_comment}
* @Date ${currentTime}
* @Param [id]
* @return void
**/
void delete${table_name_upper}(String id);


/**
* @Description 更新${table_comment}
* @Date ${currentTime}
* @Param [params]
* @return void
**/
void update${table_name_upper}(Map
<String, Object> params);

/**
* @Description 根据id查询${table_comment}: 单条
* @Date ${currentTime}
* @Param [id]
* @return java.util.Map
<java.lang.String,java.lang.Object>
**/
Map
<String, Object> select${table_name_upper}ById(String id);

/**
* @Description 根据条件查询${table_comment}：多条
* @Date ${currentTime}
* @Param [params]
* @return java.util.List
<java.util.Map
<java.lang.String,java.lang.Object>>
**/
List
<Map
<String, Object>> get${table_name_upper}s(Map
<String, Object> params);

/***
* @Description 查询数量
* @Date ${currentTime}
* @Param [params]
* @return java.lang.Integer
**/
Integer get${table_name_upper}sCount(Map
<String, Object> params);
}