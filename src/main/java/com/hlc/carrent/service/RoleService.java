package com.hlc.carrent.service;

import com.hlc.carrent.domain.Menu;
import com.hlc.carrent.domain.Role;
import com.hlc.carrent.utils.DataGridView;
import com.hlc.carrent.vo.DtreeVo;
import com.hlc.carrent.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单管理服务接口
 */
public interface RoleService {

    /**
     * 查询所有菜单
     */
    List<Role> selectAllRole(RoleVo roleVo);

    /**
     * 查询角色的条数
     */
    int selectAllRoleCount();

    /**
     * 添加菜单
     */
    int addRole(Role role);

    /**
     * 修改菜单
     */
    int updateRole(Role role);

    /**
     * 根据id删菜单
     *
     * @param roleid
     */
    void deleteRole(Integer roleid);

    /**
     * 批量删除角色
     */
    void deleteBatchRole(Integer[] ids);

    /***
     * 加载角色管理分配菜单的json
     * @param roleid
     * @return
     */
    DataGridView initRoleMenuTreeJson(Integer roleid);


    /**
     * 保存角色和菜单的关系
     */
    void saveRoleMenu(RoleVo roleVo);

}

