package com.ly.forum.service;

import com.ly.forum.pojo.PageBean;
import com.ly.forum.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李阳
 * @since 2021-12-03
 */
public interface UserService extends IService<User> {

    /*
     * 用户注册
     * */
    int register(User user);

    /*
    * 查询我关注的所有的用户信息
    * */
    PageBean<User> findAllFocusedUser(int userid, int pageCode, int pageSize);


    /*
     * 查询当前用户的所有粉丝
     * */
    PageBean<User> findAllFans(int userid, int pageCode, int pageSize);

    /*
    * 判断当前登录用户是否关注了某一个特定的用户
    * */
    int isFocused(int currentUserId, int focusedUserId);

    /*
    * 判断当前登录用户是否点赞了某一篇帖子
    * */
    public int isSupported(int currentUserId, int postId);

    /*
     * 关注博主
     * */
    int focusUser(int currentUserId, int focusedUserId);

    /*
     * 取消关注
     * */
    int cancelFocus(int currentUserId, int focusedUserId);

    /*
     * 查询当前用户尚未关注的用户的信息
     * */
    PageBean<User> recommendUser(int currentUserId, int pageCode, int pageSize);

    /*
     * 删除粉丝
     * */
    int deleteFans(int currentUserId, int fansUserId);

    /*
     * 判断当前用户是否收藏了某一个帖子
     * */
    int isCollected(int currentUserId, int postId);

    /*
     * 查询所有的用户信息
     * */
    PageBean<User> findAllUsers(int pageCode, int pageSize);

}
