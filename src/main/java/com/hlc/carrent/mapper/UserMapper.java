package com.hlc.carrent.mapper;

import com.hlc.carrent.domain.User;
import com.hlc.carrent.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {

    /**
     * 查询用户
     */
    List<User> selectAllUser(User user);
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
     * 重置密码
     */
    void resetUserPwd(@Param("pwd")String pwd,@Param("userid")Integer userid);
    /**
     * 登录
     */
    User login(User user);

    /**
     * 保存用户和角色的关系
     * @param userid
     * @param rid
     */
    void insertUserRole(@Param("uid")Integer userid,@Param("rid")Integer rid);
}
