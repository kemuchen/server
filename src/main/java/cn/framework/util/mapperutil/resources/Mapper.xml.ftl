<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//ibatis.apache.org//DTD Mapper 3.0//EN"
        "http://ibatis.apache.org/dtd/ibatis-3-mapper.dtd">

<mapper namespace="${dao_package}.${table_name_upper}Mapper">
    <!-- 字段列表 -->
    <sql id="${table_name}_col">
        <#list columns as column>${column.column_name}<#if column_has_next>,</#if> </#list>
    </sql>

    <!-- 分页查询前缀 -->
    <sql id="pagePrefix">
        select * from (
    </sql>

    <!-- 分页查询后缀 -->
    <sql id="pageSuffix">
        ) vv
        <if test="startRow != null and pageSize != null">
            limit ${startRow}, ${pageSize}
        </if>
    </sql>

    <!-- 新增${table_comment} -->
    <insert id="insert${table_name_upper}" parameterType="map">
        insert into ${table_name}
        (<#list columns as column><#if column.column_name != 'id'>${column.column_name}<#if column_has_next>,</#if></#if> </#list>
        )
        values(<#list columns as column><#if column.column_name != 'id'><#if column.column_type?contains('datetime')>sysdate()<#else><#if column.column_type?contains('int') || column.column_type?contains('double') || column.column_type?contains('decimal')>$<#else>#</#if>{${column.column_name}}</#if><#if column_has_next>,</#if></#if> </#list>
        )
    </insert>

    <!-- 删除${table_comment} -->
    <delete id="delete${table_name_upper}" parameterType="integer">
        delete from ${table_name} where id = ${id}
    </delete>

    <!-- 更新${table_comment} -->
    <update id="update${table_name_upper}" parameterType="map">
        update ${table_name}
        <set>
            <#list columns as column>
                <#if column.column_name != 'id'>
                    <if test="${column.column_name} != null and ${column.column_name} != ''">${column.column_name}
                    = <#if column.column_type?contains('int') || column.column_type?contains('double') || column.column_type?contains('decimal')>$<#else>#</#if>
                    {${column.column_name}},</if></#if> <!-- ${column.column_comment} -->
            </#list>
        </set>
        where id = ${id}
    </update>

    <!-- 根据id查询${table_comment} -->
    <select id="select${table_name_upper}ById" parameterType="integer" resultType="map">
        select
        <include refid="${table_name}_col"/>
        from ${table_name} where id = ${id}
    </select>

    <!-- 根据条件查询${table_comment}列表 -->
    <select id="get${table_name_upper}s" parameterType="map" resultType="map">
        <include refid="pagePrefix"/>
        select
        <include refid="${table_name}_col"/>
        from ${table_name}
        <where>
            <#list columns as column>
                <if test="${column.column_name} != null and ${column.column_name} != ''">and ${column.column_name}
                    = <#if column.column_type?contains('int') || column.column_type?contains('double') || column.column_type?contains('decimal')>$<#else>#</#if>
                    {${column.column_name}}
                </if> <!-- ${column.column_comment} -->
            </#list>
        </where>
        <include refid="pageSuffix"/>
    </select>

    <!-- 根据条件查询${table_comment}数量 -->
    <select id="get${table_name_upper}sCount" parameterType="map" resultType="Integer">
        select count(1) from ${table_name}
        <where>
            <#list columns as column>
                <if test="${column.column_name} != null and ${column.column_name} != ''">and ${column.column_name}
                    = <#if column.column_type?contains('int')>$<#else>#</#if>{${column.column_name}}
                </if> <!-- ${column.column_comment} -->
            </#list>
        </where>
    </select>
</mapper>