package com.ly.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.forum.pojo.Ccomment;
import com.ly.forum.pojo.Pcomment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Pcomment> {

    /*
     * 根据postId查询属于该条帖子的所有评论信息
     * */
    public List<Pcomment> findParentCommentByPostId(@Param("postId") int postId);

    /*
     * 根据就父级评论查询属于该父级评论的所有子级评论
     * */
    public List<Ccomment> findChildComment(@Param("postId") int postId, @Param("pcid") int pcid);

    /*
     * 插入父级评论信息
     * */
    public int saveParentComment(@Param("parentComment") Pcomment parentComment);

    /*
     * 插入子级评论信息
     * */
    public int saveChildComment(@Param("childComment") Ccomment childComment);

    /*
     * 修改父级评论的评论数量
     * */
    public int updateParentCommentNum(@Param("pcid") int pcid, @Param("parentCommentNum") int parentCommentNum);

    /*
     * 根据pcid来查询父级评论的子级评论信息
     * */
    public List<Ccomment> findChildCommentByPcid(@Param("pcid") int pcid);

    /*
    * 查询该父级评论的子级评论数量
    * */
    public int findCommentNumByPcid(@Param("pcid") int pcid);

    /*
    * 增加父级评论的点赞数量
    * */
    public int addParentCommentSupportNum(@Param("commentId") int commentId, @Param("parentCommentSupportNum") int parentCommentSupportNum);

    /*
    * 减少父级评论的点赞数量
    * */
    public int subParentCommentSupportNum(@Param("commentId") int commentId, @Param("parentCommentSupportNum") int parentCommentSupportNum);

    /*
    * 根据pcid查询父级评论的点赞数量
    * */
    public int findParentCommentSupportNum(@Param("commentId") int commentId);

    /*
    * 增加子级评论的点赞数量
    * */
    public int addChildCommentSupportNum(@Param("ccid") int ccid, @Param("childCommentSupportNum") int childCommentSupportNum);

    /*
    * 根据ccid查询子级评论的点赞数量
    * */
    public int findChildCommentSupportNum(@Param("ccid") int ccid);

    /*
    * 查询当前用户是否点赞了某一条父级评论
    * */
    public int isSupportParentComment(@Param("userId") int userId, @Param("postId") int postId, @Param("commentId") int commentId);

    /*
    * 插入点赞父级评论记录
    * */
    public int saveSupportCommentRecord(@Param("userId") int userId, @Param("postId") int postId, @Param("commentId") int commentId);

    /*
    * 删除点赞父级评论记录
    * */
    public int delSupportCommentRecord(@Param("userId") int userId, @Param("postId") int postId, @Param("commentId") int commentId);

}


