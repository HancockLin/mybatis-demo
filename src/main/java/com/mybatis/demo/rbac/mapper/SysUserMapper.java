package com.mybatis.demo.rbac.mapper;

import com.mybatis.demo.rbac.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    /**
     * 假设一个用户只有一个角色（使用resultMap配置一对一映射）
     *
     * @param id
     * @return
     */
    public SysUser selectUserAndRoleById2(Long id);

    /**
     * 假设一个用户只有一个角色（使用resultMap的association标签配置一对一映射）
     *
     * @param id
     * @return
     */
    public SysUser selectUserAndRoleById3(Long id);

    /**
     * 假设一个用户只有一个角色（使用resultMap的association标签进行嵌套查询）
     *
     * @param id
     * @return
     */
    public SysUser selectUserAndRoleByIdSel(Long id);

    /**
     * 使用resultMap的<collection>标签进行一对多查询
     *
     * @param id
     * @return
     */
    List<SysUser> selectRolesById(@Param("id") Long id);

    /**
     * 使用resultMap的<collection>标签进行一对多查询，根据编号查用户信息
     *
     * @param id
     * @return
     */
    SysUser selectUserById(Long id);
}
