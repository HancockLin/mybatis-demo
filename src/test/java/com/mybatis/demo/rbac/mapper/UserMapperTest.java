package com.mybatis.demo.rbac.mapper;

import com.mybatis.demo.rbac.model.SysPrivilege;
import com.mybatis.demo.rbac.model.SysRole;
import com.mybatis.demo.rbac.model.SysUser;
import com.mybatis.demo.rbac.type.Enabled;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserMapperTest {
    public static void main(String[] args) {

    }

    @Test
    public void testSelectUserANdRoleById2() throws IOException {

        SqlSession sqlSession = openSession();

        try {

            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);

//            // 调用selectUserAndRoleById2方法
//            SysUser user = userMapper.selectUserAndRoleById2(1001L);
//
//            // 调用selectUserAndRoleById3方法
//            SysUser user = userMapper.selectUserAndRoleById3(1001L);
//
            // 调用selectUserAndRoleByIdSel方法
//            SysUser user = userMapper.selectUserAndRoleByIdSel(1001L);
//
//            // user不为空
//            Assert.assertNotNull(user);
//
//            System.out.println("调用user.getRole()");
//
//            System.out.println(user.getRole());
//
//            // role不为空
//            Assert.assertNotNull(user.getRole());
//
//            System.out.println();


            // 调用selectRolesById方法
            List<SysUser> users = userMapper.selectRolesById(null);

            System.out.println("用户数：" + users.size());
            for (SysUser user : users) {
                System.out.println("用户：" + user);
                for (SysRole role : user.getRoles()) {
                    System.out.println("--角色：" + role);
                }
                System.out.println();
            }

            System.out.println();

        } finally {
            sqlSession.close();
        }
    }

    /**
     * 根据角色id获取权限
     *
     * @throws IOException
     */
    @Test
    public void testSelectPriByRoleId() throws IOException {

        SqlSession sqlSession = openSession();

        try {

            // 获取PrivilegeMapper接口
            SysPrivilegeMapper sysPrivilegeMapper = sqlSession.getMapper(SysPrivilegeMapper.class);

            // 调用selectPriByRoleId方法
            List<SysPrivilege> sysPrivilegeList = sysPrivilegeMapper.selectPriByRoleId(1L);

            Assert.assertNotNull(sysPrivilegeList);

            System.out.println();

            for (SysPrivilege sysPrivilege : sysPrivilegeList) {
                System.out.println(sysPrivilege);
            }

        } finally {
            sqlSession.close();
        }

    }

    /**
     * 根据用户id获取角色及权限
     *
     * @throws IOException
     */
    @Test
    public void testSelectRolesById() throws IOException {
        // 获取SqlSession
        SqlSession sqlSession = openSession();
        try {
            // 获取SysRoleMapper接口
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);

            // 调用selectRolesById方法
            List<SysRole> roles = roleMapper.selectRolesById(1L);

            System.out.println("角色数：" + roles.size());
            for (SysRole role : roles) {
                System.out.println("调用role.getSysPrivileges()获取权限");
                System.out.println("\n角色:" + role);
                for (SysPrivilege privilege : role.getSysPrivileges()) {
                    System.out.println("--权限：" + privilege);
                }
                System.out.println();
            }

            System.out.println();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 根据id获取用户及角色及权限
     *
     * @throws IOException
     */
    @Test
    public void testSelectUserById() throws IOException {
        // 获取SqlSession
        SqlSession sqlSession = openSession();
        try {
            // 获取SysUserMapper接口
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);

            // 调用selectUserById方法
            SysUser user = userMapper.selectUserById(1L);

            // user不为空
            Assert.assertNotNull(user);

            // 查询用户下有多少角色
            System.out.println("\n调用user.getRoles()获取角色");
            List<SysRole> roles = user.getRoles();
            System.out.println("角色数：" + roles.size());

            for (SysRole role : roles) {
                System.out.println("\n调用role.getSysPrivileges()获取权限");
                for (SysPrivilege privilege : role.getSysPrivileges()) {
                    System.out.println("--权限：" + privilege);
                }
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 鉴别器
     */
    @Test
    public void testSelectRolesByIdChoose() throws IOException {
        // 获取SqlSession
        SqlSession sqlSession = openSession();
        try {
            // 获取SysRoleMapper接口
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);

            // 调用selectRolesByIdChoose方法
            List<SysRole> roles = roleMapper.selectRolesByIdChoose(1L);

            System.out.println("角色数：" + roles.size());
            for (SysRole role : roles) {
                System.out.println("角色[" + role.getRoleName() + "]调用role.getSysPrivileges()");
                List<SysPrivilege> privileges = role.getSysPrivileges();
                if (privileges != null) {
                    for (SysPrivilege privilege : privileges) {
                        System.out.println("--权限：" + privilege);
                    }
                }
                System.out.println();
            }

            System.out.println();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 枚举
     * @throws IOException
     */
    @Test
    public void testUpdateById() throws IOException {
        // 获取SqlSession
        SqlSession sqlSession = openSession();
        try {
            // 获取SysRoleMapper接口
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);

            // 调用selectById方法
            SysRole role = roleMapper.selectById(1L);

            System.out.println(role);
            System.out.println(role.getEnabled() + "：" + Enabled.enabled);

            role.setEnabled(Enabled.disabled);

            // 调用updateById方法
            int row = roleMapper.updateById(role);

            System.out.println(row);

        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    public SqlSession openSession() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sqlSessionFactory.openSession();
    }
}
