package com.ly.forum.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.forum.pojo.Category;
import com.ly.forum.pojo.PageBean;
import com.ly.forum.pojo.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.forum.pojo.PostVo;
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
public interface PostService extends IService<Post> {
    /*
     * 分页查询所有的帖子
     * */
    public PageBean<Post> findAllPost(int pageCode, int pageSize);

    /*
     * 分页按时间发布顺序查询所有的帖子
     * */
    public PageBean<Post> findPostByPublishTime(int pageCode, int pageSize);

    /*
     * 分页查询当前登录用户所关注的用户发布的帖子
     * */
    public PageBean<Post> findCurUserFocusedPost(int userid, int pageCode, int pageSize);

    /*
    * 根据分类类别来分页查询帖子信息
    * */
    public PageBean<Post> findPostByCategory(Category category, int pageCode, int pageSize);

    /*
    * 分页查询所有高校发布的帖子
    * */
    public PageBean<Post> findPostByCollegeUser(int pageCode, int pageSize);

    /*
    * 分页查询话题列表
    * */
    public PageBean<Post> findTopicList(int pageCode, int pageSize);

    /*
    * 查询热搜榜列表
    * */
    public PageBean<Post> hotSearchList(int pageCode, int pageSize);

    /*
    * 查询当前登录用户的所有帖子信息
    * */
    public PageBean<Post> findAllPersonalPost(int userid, int pageCode, int pageSize);

    /*
    * 查询当前用户收藏的帖子
    * */
    public PageBean<Post> findUserCollectedPost(int userid, int pageCode, int pageSize);

    /*
    * 收藏帖子
    * */
    public int collectPost(int currentUserId, int postId);

    /*
    * 取消收藏帖子
    * */
    public int deleteCollectPost(int currentUserId, int postId);

    /*
     * 根据关键字进行模糊查询
     * */
    public PageBean<Post> findPostByKeyword(String keyword, int pageCode, int pageSize);

    /*
     * 保存新发布帖子信息
     * */
    public int savePost(Post post);

    /*
    * 根据postId查询帖子的评论数
    * */
    public int findCommentNumByPostId(int postId);

    /*
    * 增加帖子的点赞数量
    * */
    public int addPostSupportNum(int postId, int postSupportNum, int userId);

    /*
    * 取消帖子点赞
    * */
    public int subPostSupportNum(int postId, int postSupportNum, int userId);

    /*
    * 查询帖子的点赞数量
    * */
    public int findPostSupportNum(int postId);

    /*
    * 查询阅读量最多的10篇帖子
    * */
    public List<Post> findPostByReadNum();

    /*
    * 保存转发的帖子
    * */
    public int saveForwardPost(Post post);

    /*
     * 根据postId来查找转发数量
     * */
    public int findForwardNumByPostId(int postId);
}
