package com.hlc.carrent.service;

import com.hlc.carrent.domain.User;
import com.hlc.carrent.utils.DataGridView;
import com.hlc.carrent.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    /**
     * 查询用户
     */
    List<User> selectAllUser(UserVo userVo);
    /**
     * 查询菜单的条数
     */
    int selectAllUserCount();
    /**
     * 添加角色
     */
    int addUser(User user);

    /**
     * 修改角色
     */
    int updateUser(User user);

    /**
     * 根据id删角色
     *
     * @param userid
     */
    void deleteUser(@Param("userid") Integer userid);

    /**
     * 批量删除用户
     * @param ids
     */
    void deleteBatchUser(Integer [] ids);
    /**
     * 重置密码
     * @param userid
     */
    void resetUserPwd(Integer userid);

    /**
     * 加载用户管理的分配角色数据
     * @param userid
     * @return
     */
    DataGridView selectUserRole(Integer userid);
    /**
     * 登录
     * @param userVo
     * @return
     */
    User login(UserVo userVo);

    /**
     * 保存用户和角色的关系
     * @param userVo
     */
    void saveUserROle(UserVo userVo);
}
