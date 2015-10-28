<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${objectName}Mapper">

	<sql id="columns">
	<#list fieldList as var>
		${var[0]},
	</#list>
		${objectNameUpper}_ID
	</sql>

	<sql id="columnsT">
	<#list fieldList as var>
		T.${var[0]},
	</#list>
		T.${objectNameUpper}_ID
	</sql>

	<!-- 新增-->
	<insert id="save" parameterType="pd">
		INSERT INTO ${tabletop}${objectNameUpper}(
	<#list fieldList as var>
			${var[0]},
	</#list>
			${objectNameUpper}_ID
		) VALUES (
	<#list fieldList as var>
			${r"#{"}${var[0]}${r"}"},
	</#list>
			${r"#{"}${objectNameUpper}_ID${r"}"}
		)
	</insert>

	<!-- 删除-->
	<delete id="delete" parameterType="pd">
		DELETE FROM ${tabletop}${objectNameUpper}
		WHERE
			${objectNameUpper}_ID = ${r"#{"}${objectNameUpper}_ID${r"}"}
	</delete>

	<!-- 修改 -->
	<update id="edit" parameterType="pd">
		UPDATE ${tabletop}${objectNameUpper}
			SET
	<#list fieldList as var>
		<#if var[3] == "是">
			<if test="${var[0]} != null and ${var[0]} != ''">
				${var[0]} = ${r"#{"}${var[0]}${r"}"},
			</if>
		</#if>
	</#list>
			WHERE
				${objectNameUpper}_ID = ${r"#{"}${objectNameUpper}_ID${r"}"}
	</update>

	<!-- 通过ID获取数据 -->
	<select id="findById" parameterType="pd" resultType="pd">
		SELECT
			<include refid="columns"></include>
		FROM 
			${tabletop}${objectNameUpper}
		WHERE 
			${objectNameUpper}_ID = ${r"#{"}${objectNameUpper}_ID${r"}"}
	</select>

	<!-- 列表 -->
	<select id="datalistPage" parameterType="page" resultType="pd">
		SELECT
			<include refid="columnsT"></include>
		FROM 
			${tabletop}${objectNameUpper} T
	</select>

	<!-- 列表(全部) -->
	<select id="listAll" parameterType="pd" resultType="pd">
		SELECT
			<include refid="columnsT"></include>
		FROM 
			${tabletop}${objectNameUpper} T
	</select>

	<!-- 批量删除 -->
	<delete id="deleteAll" parameterType="String">
		DELETE FROM ${tabletop}${objectNameUpper}
		WHERE 
			${objectNameUpper}_ID IN
		<foreach item="item" index="index" collection="array" open="(" separator="," close=")">
			${r"#{item}"}
		</foreach>
	</delete>
	
</mapper>