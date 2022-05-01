package com.ly.forum.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.forum.pojo.*;
import com.ly.forum.service.PostService;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    /*
     * 分页查询所有的帖子
     * */
    @RequestMapping("/findAllPost")
    public PageBean<Post> findAllPost(int pageCode, int pageSize){
        PageBean<Post> pageBean = postService.findAllPost(pageCode, pageSize);
        return pageBean;
    }

    /*
     * 分页按时间发布顺序查询所有的帖子
     * */
    @RequestMapping("/findPostByPublishTime")
    public PageBean<Post> findPostByPublishTime(int pageCode, int pageSize){
        PageBean<Post> pageBean = postService.findPostByPublishTime(pageCode, pageSize);
        return pageBean;
    }

    /*
     * 分页查询当前登录用户所关注的用户发布的帖子
     * */
    @RequestMapping("/findCurUserFocusedPost")
    public PageBean<Post> findCurUserFocusedPost(int userid, int pageCode, int pageSize){
        PageBean<Post> pageBean = postService.findCurUserFocusedPost(userid, pageCode, pageSize);
        return pageBean;
    }

    /*
    * 根据分类类别来分页查询帖子信息
    * */
    @RequestMapping("/findPostByCategory")
    public PageBean<Post> findPostByCategory(Category category, int pageCode, int pageSize){
        PageBean<Post> pageBean = postService.findPostByCategory(category, pageCode, pageSize);
        return pageBean;
    }

    /*
     * 分页查询所有高校发布的帖子
     * */
    @RequestMapping("/findPostByCollegeUser")
    public PageBean<Post> findPostByCollegeUser(int pageCode, int pageSize){
        PageBean<Post> pageBean = postService.findPostByCollegeUser(pageCode, pageSize);
        return pageBean;
    }

    /* 分页查询话题列表 */
    @RequestMapping("/findTopicList")
    public PageBean<Post> findTopicList(int pageCode, int pageSize){
        PageBean<Post> pageBean = postService.findTopicList(pageCode, pageSize);
        return pageBean;
    }

    /* 查询热搜榜列表 */
    @RequestMapping("/hotSearchList")
    public PageBean<Post> hotSearchList(int pageCode, int pageSize){
        PageBean<Post> pageBean = postService.hotSearchList(pageCode, pageSize);
        return pageBean;
    }

    /*
     * 查询当前登录用户的所有帖子信息
     * */
    @RequestMapping("/findAllPersonalPost")
    public PageBean<Post> findAllPersonalPost(int userid, int pageCode, int pageSize) {
        PageBean<Post> pageBean = postService.findAllPersonalPost(userid, pageCode, pageSize);
        return pageBean;
    }

    /*
    * 查询当前用户收藏的帖子
    * */
    @RequestMapping("/findUserCollectedPost")
    public PageBean<Post> findUserCollectedPost(int userid, int pageCode, int pageSize){
        PageBean<Post> pageBean = postService.findUserCollectedPost(userid, pageCode, pageSize);
        return pageBean;
    }

    /*
     * 收藏帖子
     * */
    @RequestMapping("/collectPost")
    public int collectPost(int currentUserId, int postId){
        int resultNum = postService.collectPost(currentUserId, postId);
        return resultNum;
    }

    /*
    * 取消收藏帖子
    * */
    @RequestMapping("/deleteCollectPost")
    public int deleteCollectPost(int currentUserId, int postId){
        int resultNum = postService.deleteCollectPost(currentUserId, postId);
        return resultNum;
    }

    /*
     * 根据关键字进行模糊查询
     * */
    @RequestMapping("/findPostByKeyword")
    public PageBean<Post> findPostByKeyword(String keyword, int pageCode, int pageSize) {
        PageBean<Post> pageBean = postService.findPostByKeyword(keyword, pageCode, pageSize);
        return pageBean;
    }

    /*
    * 保存新发布的帖子信息
    * */
    @PostMapping("/savePost")
    public int savePost(@RequestBody String form){
        JSONObject jsonObject = JSON.parseObject(form);
        //System.out.println(jsonObject);
        Post post = new Post();
        post.setPostname(jsonObject.getString("postname"));
        post.setCopywriting(jsonObject.getString("copywriting"));
        post.setImagelinks(jsonObject.getString("imagelinks"));
        post.setCategory(new Category().setCategoryid(Integer.parseInt(jsonObject.getString("categoryid"))));
        post.setUser(new User().setUserid(Integer.parseInt(jsonObject.getString("userid"))));
        System.out.println(post);
        int result = postService.savePost(post);
        if(result == 1){
            return 0;
        }
        return -1;
    }

    /*
    * 根据postId查询帖子的评论数
    * */
    @RequestMapping("/findCommentNumByPostId")
    public int findCommentNumByPostId(int postid){
        int commentNum = postService.findCommentNumByPostId(postid);
        return commentNum;
    }

    /*
    * 增加帖子的点赞数量
    * */
    @RequestMapping("/addPostSupportNum")
    public int addPostSupportNum(int postId, int postSupportNum, int userId) {
        int resultNum = postService.addPostSupportNum(postId, postSupportNum, userId);
        if (resultNum == 1){
            return 0;
        }
        return -1;
    }

    /*
    * 取消帖子的点赞
    * */
    @RequestMapping("/subPostSupportNum")
    public int subPostSupportNum(int postId, int postSupportNum, int userId){
        int resultNum = postService.subPostSupportNum(postId, postSupportNum, userId);
        if (resultNum == 1){
            return 0;
        }
        return -1;
    }

    /*
    * 查询该帖子的点赞数量
    * */
    @RequestMapping("/findPostSupportNum")
    public int findPostSupportNum(int postid){
        int supportNum = postService.findPostSupportNum(postid);
        return supportNum;
    }

    /*
     * 查询阅读量最多的10篇帖子
     * */
    @RequestMapping("/findPostByReadNum")
    public List<Post> findPostByReadNum(){
        List<Post> postList = postService.findPostByReadNum();
        return postList;
    }

    /*
    * 保存转发的帖子
    * */
    @RequestMapping("/saveForwardPost")
    public int saveForwardPost (@RequestBody String form){
        //System.out.println(form);
        JSONObject jsonObject = JSON.parseObject(form);
        Post post = new Post();
        post.setPostid(Integer.parseInt(jsonObject.getString("postid")));
        post.setPostname(jsonObject.getString("postname"));
        post.setCopywriting(jsonObject.getString("copywriting"));
        post.setForwardContent(jsonObject.getString("forward_content"));
        post.setImagelinks(jsonObject.getString("imagelinks"));
        post.setForwardNum(Integer.parseInt(jsonObject.getString("forwardNum")));
        post.setCategory(new Category().setCategoryid(Integer.parseInt(jsonObject.getString("categoryid"))));
        post.setUser(new User().setUserid(Integer.parseInt(jsonObject.getString("userid"))));
        post.setIsForward(Integer.parseInt(jsonObject.getString("is_forward")));
        int result = postService.saveForwardPost(post);
        System.out.println("执行了Controller...");
        if(result == 1){
            return 0;
        }
        return -1;
    }

    /*
     * 根据postId来查找转发数量
     * */
    @RequestMapping("/findForwardNumByPostId")
    public int findForwardNumByPostId(int postId){
        int forwardNum = postService.findForwardNumByPostId(postId);
        return forwardNum;
    }

}

