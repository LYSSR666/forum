package com.ly.forum.mapper;

import com.ly.forum.pojo.Category;
import com.ly.forum.pojo.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import javafx.geometry.Pos;
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
public interface PostMapper extends BaseMapper<Post> {
    /*
    * 分页按时间发布顺序查询所有的帖子
    * */
    public List<Post> findPostByPublishTime(@Param("pageCode") int pageCode, @Param("pageSize") int pageSize);

    /*
     * 分页查询所有的帖子
     * */
    public List<Post> findAllPost(@Param("pageCode") int pageCode, @Param("pageSize") int pageSize);

    /*
    * 查询所有帖子的总记录数
    * */
    public int findPostTotalRecord();

    /*
    * 查询最近7天帖子的总记录数
    * */
    public int findRecentPostRecord();

    /*
    * 分页查询当前登录用户所关注的用户发布的帖子
    * */
    public List<Post> findCurUserFocusedPost(@Param("userid") int userid, @Param("pageCode") int pageCode, @Param("pageSize") int pageSize);

    /*
    * 查询当前登录用户所关注的用户发布的帖子的总记录数
    * */
    public int findFocusedPostTotalRecord(@Param("userid") int userid);

    /*
    * 根据分类类别来分页查询帖子信息
    * */
    public List<Post> findPostByCategory(@Param("category") Category category, @Param("pageCode") int pageCode, @Param("pageSize") int pageSize);


    /*
    * 分页查询所有高校发布的帖子
    * */
    public List<Post> findPostByCollegeUser(@Param("pageCode") int pageCode, @Param("pageSize")int pageSize);

    /*
    * 查询高校发布的帖子总数
    * */
    public int findCollegePostTotalRecords();

    /*
    * 分页查询话题列表
    * */
    public List<Post> findTopicList(@Param("pageCode") int pageCode, @Param("pageSize") int pageSize);

    /*
    * 查询热搜榜列表
    * */
    public List<Post> hotSearchList(@Param("pageCode") int pageCode, @Param("pageSize") int pageSize);

    /*
    * 查询当前登录用户的所有帖子信息
    * */
    public List<Post> findAllPersonalPost(@Param("userid") int userid, @Param("pageCode") int pageCode, @Param("pageSize") int pageSize);

    /*
    * 查询当前用户收藏的帖子
    * */
    public List<Post> findUserCollectedPost(@Param("userid") int userid, @Param("pageCode") int pageCode, @Param("pageSize") int pageSize);

    /*
    * 收藏帖子
    * */
    public int collectPost(@Param("currentUserId") int currentUserId, @Param("postId") int postId);

    /*
    * 取消收藏帖子
    * */
    public int deleteCollectPost(@Param("currentUserId") int currentUserId, @Param("postId") int postId);

    /*
    * 查询当前用户收藏的帖子的总数
    * */
    public int findUserCollectPostRecord(@Param("userid") int userid);

    /*
    * 查询包含该关键字的帖子总数
    * */
    public int findPostByKeywordRecord(@Param("keyword") String keyword);

    /*
    * 根据关键字进行模糊查询
    * */
    public List<Post> findPostByKeyword(@Param("keyword") String keyword, @Param("pageCode") int pageCode, @Param("pageSize") int pageSize);

    /*
    * 保存新发布帖子信息
    * */
    public int savePost(@Param("post") Post post);

    /*
    * 修改帖子评论数
    * */
    public int updateCommentNum(@Param("postId") int postId, @Param("postCommentNum") int postCommentNum);

    /*
    * 查询帖子的评论数
    * */
    public int findCommentNumByPostId(@Param("postId") int postId);

    /*
    * 增加帖子的点赞数量
    * */
    public int addPostSupportNum(@Param("postId") int postId, @Param("postSupportNum") int postSupportNum);

    /*
    * 取消帖子点赞
    * */
    public int subPostSupportNum(@Param("postId") int postId, @Param("postSupportNum") int postSupportNum);

    /*
    * 插入帖子点赞记录
    * */
    public int recordSupportPost(@Param("userId") int userId, @Param("postId") int postId);

    /*
    * 删除帖子点赞记录
    * */
    public int delSupportPost(@Param("userId") int userId, @Param("postId") int postId);

    /*
    * 查询帖子的点赞数量
    * */
    public int findPostSupportNum(@Param("postId") int postId);

    /*
    * 增加帖子转发数量
    * */
    public int addPostForwardNum(@Param("postId") int postId, @Param("forwardNum") int forwardNum);

    /*
    * 插入转发记录
    * */
    public int addForwardRecord(@Param("userId") int userId, @Param("postId") int postId);

    /*
    * 查询阅读量最多的10篇帖子
    * */
    public List<Post> findPostByReadNum();

    /*
     * 保存转发的帖子
     * */
    public int saveForwardPost(@Param("post") Post post);

    /*
    * 根据postId来查找转发数量
    * */
    public int findForwardNumByPostId(@Param("postId") int postId);
}
