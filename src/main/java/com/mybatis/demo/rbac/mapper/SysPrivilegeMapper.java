package com.mybatis.demo.rbac.mapper;

import com.mybatis.demo.rbac.model.SysPrivilege;

import java.util.List;

public interface SysPrivilegeMapper {
    /**
     * 根据角色id查询权限
     *
     * @param roleId
     * @return
     */
    List<SysPrivilege> selectPriByRoleId(Long roleId);
}
