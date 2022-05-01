package com.ly.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.forum.pojo.PageBean;
import com.ly.forum.pojo.Post;
import com.ly.forum.pojo.User;
import com.ly.forum.mapper.UserMapper;
import com.ly.forum.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李阳
 * @since 2021-12-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /*
    * 实现用户登录
    * 即通过用户名密码查询用户
    * */
    public User login(String username, String password){
        QueryWrapper<User> wrapper =  new QueryWrapper<User>();
        wrapper.eq("username", username).eq("password", password);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    /*
    * 用户注册
    * */
    @Override
    public int register(User user){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String createTime = dateFormat.format(date);
        user.setCreateTime(createTime);
        int resultNum = userMapper.register(user);
        if(resultNum == 1){
            return 0;
        }
        return -1;
    }

    /*
     * 查询所有的学校官方用户
     * */
    public List<User> findCollegeUsers(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("iscollege", 1);
        List<User> userList = userMapper.selectList(wrapper);
        return userList;
    }

    /*
     * 查询当前用户所关注的所有的用户信息
     * */
    @Override
    public PageBean<User> findAllFocusedUser(int userid, int pageCode, int pageSize) {
        PageBean<User> pageBean = new PageBean<User>();
        int totalRecord = userMapper.findAllFocusedUserRecords(userid);
        int totalPage = totalRecord/pageSize;
        totalPage = totalRecord%pageSize == 0 ? totalPage : totalPage+1;
        List<User> userList = userMapper.findAllFocusedUser(userid, (pageCode-1)*pageSize, pageSize);
        pageBean.setPageCode(pageCode);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        pageBean.setTotalRecord(totalRecord);
        pageBean.setBeanList(userList);
        return pageBean;
    }

    /*
    * 查询关注当前用户的所有用户的信息（即粉丝信息）
    * */
    @Override
    public PageBean<User> findAllFans(int userid, int pageCode, int pageSize) {
        PageBean<User> pageBean = new PageBean<User>();
        int totalRecord = userMapper.findAllFansRecord(userid);
        int totalPage = totalRecord/pageSize;
        totalPage = totalRecord%pageSize == 0 ? totalPage : totalPage+1;
        List<User> userList = userMapper.findAllFans(userid, (pageCode-1)*pageSize, pageSize);
        pageBean.setPageCode(pageCode);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        pageBean.setTotalRecord(totalRecord);
        pageBean.setBeanList(userList);
        return pageBean;
    }

    /*
    * 判断当前登录用户是否关注了某一个特定的用户
    * */
    @Override
    public int isFocused(int currentUserId, int focusedUserId) {
        int resultNum = userMapper.isFocused(currentUserId, focusedUserId);
        return resultNum;
    }

    /*
     * 判断当前登录用户是否点赞了某一篇帖子
     * */
    public int isSupported(int currentUserId, int postId){
        int resultNum = userMapper.isSupported(currentUserId, postId);
        return resultNum;
    }

    /*
    * 关注博主
    * */
    @Override
    public int focusUser(int currentUserId, int focusedUserId) {
        int resultNum = userMapper.focusUser(currentUserId, focusedUserId);
        return resultNum;
    }

    /*
    * 取消关注
    * */
    @Override
    public int cancelFocus(int currentUserId, int focusedUserId) {
        int resultNum = userMapper.cancelFocus(currentUserId, focusedUserId);
        return resultNum;
    }

    /*
     * 查询当前用户尚未关注的用户的信息
     * */
    @Override
    public PageBean<User> recommendUser(int currentUserId, int pageCode, int pageSize) {
        PageBean<User> pageBean = new PageBean<User>();
        int totalRecord = userMapper.unfocuseUserNum(currentUserId);
        int totalPage = totalRecord/pageSize;
        totalPage = totalRecord%pageSize==0 ? totalPage : totalPage+1;
        List <User> userList = userMapper.recommendUser(currentUserId, (pageCode-1)*pageSize, pageSize);
        pageBean.setBeanList(userList);
        pageBean.setPageCode(pageCode);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    /*
     * 删除粉丝
     * */
    @Override
    public int deleteFans(int currentUserId, int fansUserId){
        // 删除我对粉丝的关注
        int focusNum = userMapper.isFocused(currentUserId, fansUserId);
        if (focusNum == 1){
            int deleteFocusResult = userMapper.cancelFocus(currentUserId, fansUserId);
        }
        // 删除粉丝对我的关注
        int deleteFansResult = userMapper.deleteFans(currentUserId, fansUserId);
        return deleteFansResult;
    }

    /*
     * 判断当前用户是否收藏了某一个帖子
     * */
    @Override
    public int isCollected(int currentUserId, int postId){
        int resultNum = userMapper.isCollected(currentUserId, postId);
        return resultNum;
    }

    /*
     * 查询所有的用户信息
     * */
    @Override
    public PageBean<User> findAllUsers(int pageCode, int pageSize){
        PageBean<User> pageBean = new PageBean<User>();
        int totalRecord = userMapper.findAllUserRecord();
        int totalPage = totalRecord/pageSize;
        totalPage = totalRecord%pageSize==0 ? totalPage : totalPage+1;
        List <User> userList = userMapper.findAllUsers(pageCode, pageSize);
        pageBean.setBeanList(userList);
        pageBean.setPageCode(pageCode);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

}
