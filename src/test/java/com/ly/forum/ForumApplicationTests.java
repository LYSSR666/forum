package com.ly.forum;

import com.ly.forum.mapper.UserMapper;
import com.ly.forum.pojo.PageBean;
import com.ly.forum.pojo.Pcomment;
import com.ly.forum.pojo.User;
import com.ly.forum.service.impl.CommentServiceImpl;
import com.ly.forum.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ForumApplicationTests {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        /*List<Pcomment> pcommentList = commentService.findParentCommentByPostId(1);
        for (Pcomment pcomment: pcommentList){
            System.out.println(pcomment);
        }*/
//        List<User> users = userMapper.findAllFocusedUser(3, 0, 10);
//        List<User> users = userMapper.findAllFocusedUser_Copy(3);
//        System.out.println(users);
    }

}
