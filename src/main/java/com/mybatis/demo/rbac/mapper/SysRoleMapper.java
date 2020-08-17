package com.mybatis.demo.rbac.mapper;

import com.mybatis.demo.rbac.model.SysRole;

import java.util.List;

public interface SysRoleMapper {
    /**
     * 使用resultMap的<collection>标签进行一对多查询，根据用户编号查角色
     *
     * @param userId
     * @return
     */
    public List<SysRole> selectRolesById(Long userId);

    public List<SysRole> selectRolesByIdChoose(Long userId);

    /**
     * 根据ID查询角色，枚举用法
     * @param id
     * @return
     */
    SysRole selectById(Long id);

    /**
     * 根据ID更新角色，枚举用法
     * @param role
     * @return
     */
    int updateById(SysRole role);
}
