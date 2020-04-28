package com.hlc.carrent.mapper;

import com.hlc.carrent.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {

    /**
     * 查询所有左菜单列表
     */
    List<Menu> selectMenuLeftTree(Menu menu);

    /**
     * 查询所有菜单管理右边树结构
     */
    List<Menu> selectMenuRightTree(Menu menu);

    /**
     * 查询所有菜单
     */
    List<Menu> selectAllMenu(@Param("title") String title, @Param("id") Integer id,@Param("available") Integer available);

    /**
     * 查询菜单的条数
     */
    int selectAllMenuCount();

    /**
     * 添加菜单
     */
    int addMenu(Menu menu);

    /**
     * 修改菜单
     */
    int updateMenu(Menu menu);

    /**
     * 根据pid菜单数量
     *
     * @return count
     */
    Integer selectMenuByPid(@Param("pid") Integer pid);

    /**
     * 根据id删菜单
     *
     * @param id
     */
    void deleteMenu(@Param("id") Integer id);

    /**
     * 根据菜单id 删除sys_role_menu里面的数据
     * @param mid
     */
    void deleteRoleMenuByMid(@Param("mid") Integer mid);


    /**
     * 查询所有菜单
     */
    List<Menu> queryAllMenu(Menu menu);
    /**
     * 根据角色ID查询菜单
     */
    List<Menu>  queryMenuByRoleId(@Param("available")Integer available, @Param("rid")Integer rid);

    /**
     * 根据用户查询ID菜单
     */
    List<Menu> selectMenuByUid(@Param("available")Integer available, @Param("uid")Integer userid);

}
