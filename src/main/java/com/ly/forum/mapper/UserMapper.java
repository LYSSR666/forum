package com.ly.forum.mapper;

import com.ly.forum.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李阳
 * @since 2021-12-03
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /*
    * 用户注册
    * */
    int register(@Param("user") User user);

    /*
    * 查询我所关注的用户总记录数
    * */
    int findAllFocusedUserRecords(@Param("userid") int userid);

    /*
    * 查询我关注的所有用户信息
    * */
    List<User> findAllFocusedUser(@Param("userid") int userid, @Param("pageCode") int pageCode, @Param("pageSize") int pageSize);

    /*
    * 查询我的粉丝总记录数
    * */
    int findAllFansRecord(@Param("userid") int userid);

    /*
    * 查询当前用户的所有粉丝
    * */
    List<User> findAllFans(@Param("userid") int userid, @Param("pageCode") int pageCode, @Param("pageSize") int pageSize);

    /*
    * 判断当前登录用户是否关注了某一个特定的用户
    * */
    int isFocused(@Param("currentUserId") int currentUserId, @Param("focusedUserId") int focusedUserId);

    /*
    * 判断当前登录用户是否点赞了某一篇帖子
    * */
    int isSupported(@Param("currentUserId") int currentUserId, @Param("postId") int postId);

    /*
    * 关注博主
    * */
    int focusUser(@Param("currentUserId") int currentUserId, @Param("focusedUserId") int focusedUserId);

    /*
    * 取消关注
    * */
    int cancelFocus(@Param("currentUserId") int currentUserId, @Param("focusedUserId") int focusedUserId);

    /*
    * 查询当前用户尚未关注的用户的信息
    * */
    List<User> recommendUser(@Param("currentUserId") int currentUserId, @Param("pageCode") int pageCode, @Param("pageSize") int pageSize);

    /*
    * 查询当前用户尚未关注的用户总数
    * */
    int unfocuseUserNum(@Param("currentUserId") int currentUserId);

    /*
    * 删除粉丝
    * */
    int deleteFans(@Param("currentUserId") int currentUserId, @Param("fansUserId") int fansUserId);

    /*
    * 判断当前用户是否收藏了某一个帖子
    * */
    int isCollected(@Param("currentUserId") int currentUserId, @Param("postId") int postId);

    /*
    * 查询所有的用户信息
    * */
    List<User> findAllUsers(@Param("pageCode") int pageCode, @Param("pageSize") int pageSize);

    /*
    * 查询所有用户总数量
    * */
    int findAllUserRecord();
}
