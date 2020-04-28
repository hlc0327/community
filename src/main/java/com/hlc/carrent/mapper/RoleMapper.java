package com.hlc.carrent.mapper;

import com.hlc.carrent.domain.Role;
import com.hlc.carrent.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    /**
     * 查询所有角色
     */
    List<Role> selectAllRole(RoleVo roleVo);

    /**
     * 查询菜单的条数
     */
    int selectAllRoleCount();

    /**
     * 添加角色
     */
    int addRole(Role role);

    /**
     * 修改角色
     */
    int updateRole(Role role);

    /**
     * 根据id删角色
     *
     * @param roleid
     */
    void deleteRole(@Param("roleid") Integer roleid);

    /**
     * 根据角色id 删除sys_role_menu里面的数据
     *
     * @param roleid
     */
    void deleteRoleMenuByRid(@Param("roleid") Integer roleid);

    /**
     * 根据角色id 删除sys_role_user里面的数据
     *
     * @param roleid
     */
    void deleteRoleUserByRid(@Param("roleid") Integer roleid);

    /**
     * 保存角色和菜单的关系
     */
    void saveRoleMenu(@Param("rid") Integer rid,@Param("mid") Integer mid);

    /**
     * 根据用户id删除sys_role_user里面的数据
     */
    void deleteRoleUserByUid(@Param("userid") Integer userid);
    /**
     * 根据用户ID查询角色
     */
    List<Role> selectRoleByUid(@Param("available") Integer available,@Param("uid") Integer userid);
}
