package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    //查询所有
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    //添加
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    @ResultMap("brandResultMap")
    void add(Brand brand);

    @Delete("delete from tb_brand where id=#{id}")
    void delete(int id);

    @Update("update tb_brand set brand_name = #{brandName},company_name = #{companyName},ordered = #{ordered},description = #{description},status = #{status} where id = #{id}")
    void update(Brand brand);

    //批量删除
    void deleteByIds(@Param("ids") int[] ids);

    /*
    * 分页查询
    *
    * */
    @Select("select * from tb_brand limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);

    /*
     * 查询总记录数
     *
     * */
    @Select("select count(*) from tb_brand")
    int selectTotalCount();


    /*
     * 分页条件查询
     *
     * */

    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("brand") Brand brand);
    /*
     * 根据查询总记录数
     *
     * */
    int selectTotalCountByCondition(Brand brand);


}
