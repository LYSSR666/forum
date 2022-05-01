package com.ly.forum.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.forum.pojo.PageBean;
import com.ly.forum.pojo.User;
import com.ly.forum.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 李阳
 * @since 2021-12-03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /*
    * 用户登录
    * */
    @RequestMapping("/login")
    public User login(String username, String password){
        System.out.println(username + ", " + password);
        User user = userService.login(username, password);
        return user;
    }

    /*
    * 用户注册
    * */
    @PostMapping("/register")
    public int register(@RequestBody String form){
        System.out.println(form);
        JSONObject jsonObject = JSON.parseObject(form);
        User user = new User();
        user.setUserAvatar(jsonObject.getString("userAvatar"));
        user.setUsername(jsonObject.getString("username"));
        user.setPassword(jsonObject.getString("password"));
        user.setMotto(jsonObject.getString("motto"));
        user.setSchool(jsonObject.getString("school"));
        user.setSpecialty(jsonObject.getString("specialty"));
        user.setArea(jsonObject.getString("area"));
        user.setIscollege(Integer.parseInt(jsonObject.getString("iscollege")));
        int resultNum = userService.register(user);
        return resultNum;
    }

    /*
     * 查询所有的学校官方用户
     * */
    @RequestMapping("/findAllColleges")
    public List<User> findCollegeUsers(){
        List<User> userList = userService.findCollegeUsers();
        return userList;
    }

    /*
    * 查询我关注的所有的用户信息
    * */
    @RequestMapping("/findAllFocusedUser")
    public PageBean<User> findAllFocusedUser(int userid, int pageCode, int pageSize){
        PageBean<User> pageBean = userService.findAllFocusedUser(userid, pageCode, pageSize);
        return pageBean;
    }

    /*
    * 查询当前用户的所有粉丝
    * */
    @RequestMapping("/findAllFans")
    public PageBean<User> findAllFans(int userid, int pageCode, int pageSize){
        PageBean<User> pageBean = userService.findAllFans(userid, pageCode, pageSize);
        return pageBean;
    }

    /*
     * 判断当前登录用户是否关注了某一个特定的用户
     * */
    @RequestMapping("/isFocused")
    public int isFocused(int currentUserId, int focusedUserId){
        int resultNum = userService.isFocused(currentUserId, focusedUserId);
        if (resultNum == 1){
            return 0;
        }
        return -1;
    }

    /*
     * 判断当前登录用户是否点赞了某一篇帖子
     * */
    @RequestMapping("/isSupported")
    public int isSupported(int currentUserId, int postId){
        int resultNum = userService.isSupported(currentUserId, postId);
        if (resultNum == 1){
            return 0;
        }
        return -1;
    }

    /*
     * 关注博主
     * */
    @RequestMapping("/focusUser")
    public int focusUser(int currentUserId, int focusedUserId) {
        int resultNum = userService.focusUser(currentUserId, focusedUserId);
        if (resultNum == 1){
            return 0;
        }
        return -1;
    }

    /*
     * 取消关注
     * */
    @RequestMapping("/cancelFocus")
    public int cancelFocus(int currentUserId, int focusedUserId) {
        int resultNum = userService.cancelFocus(currentUserId, focusedUserId);
        if (resultNum == 1){
            return 0;
        }
        return -1;
    }

    /*
    * 查询当前用户尚未关注的用户的信息
    * */
    @RequestMapping("/recommendUser")
    PageBean<User> recommendUser(int currentUserId, int pageCode, int pageSize){
        PageBean<User> pageBean = userService.recommendUser(currentUserId, pageCode, pageSize);
        return pageBean;
    }

    /*
     * 删除粉丝
     * */
    @RequestMapping("/deleteFans")
    public int deleteFans(int currentUserId, int fansUserId){
        int result = userService.deleteFans(currentUserId, fansUserId);
        if (result==1){
            return 0;
        }
        return -1;
    }

    /*
     * 判断当前用户是否收藏了某一个帖子
     * */
    @RequestMapping("/isCollected")
    public int isCollected(int currentUserId, int postId){
        int resultNum = userService.isCollected(currentUserId, postId);
        if (resultNum == 1){
            return 0;
        }
        return -1;
    }

    /*
    * 查询所有的用户信息
    * */
    @RequestMapping("/findAllUsers")
    public PageBean<User> findAllUsers(int pageCode, int pageSize){
        PageBean<User> pageBean = userService.findAllUsers(pageCode, pageSize);
        return pageBean;
    }

}

