package com.mybatis.demo.rbac.mapper1;

import com.mybatis.demo.rbac.entity.File;

public interface FileMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file
     *
     * @mbg.generated
     */
    int insert(File record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file
     *
     * @mbg.generated
     */
    int insertSelective(File record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file
     *
     * @mbg.generated
     */
    File selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(File record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(File record);
}