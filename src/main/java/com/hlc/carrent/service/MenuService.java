package com.hlc.carrent.service;

import com.hlc.carrent.domain.Menu;
import com.hlc.carrent.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单管理服务接口
 */
public interface MenuService {

    /**
     * 查询所有左菜单列表
     */
    List<Menu> selectMenuLeftTree(MenuVo menuVo);

    /**
     * 根据用户id查询用户的可用菜单
     */
    List<Menu> selectMenuByUserIdForList(MenuVo menuVo,Integer id);
    /**
     * 查询所有菜单管理右边树结构
     */
    List<Menu> selectMenuRightTree(Menu menu);
    /**
     * 查询所有菜单
     */
    List<Menu> selectAllMenu(Integer pageNum,Integer pageSize,String title,Integer id,Integer vailable);
    /**
     * 查询菜单的条数
     */
    int selectAllMenuCount();
    /**
     *  添加菜单
     */
    int addMenu(Menu menu);
    /**
     * 修改菜单
     */
    int updateMenu(Menu menu);

    /**
     * 根据pid菜单数量
     * @return count
     */
    Integer selectMenuByPid(Integer pid);

    /**
     * 根据id删菜单
     * @param menuVo
     */
    void deleteMenu(MenuVo menuVo);
    /**
     * 根据用户查询ID菜单
     */
    List<Menu> selectMenuByUid(Integer available,Integer userid);

}
