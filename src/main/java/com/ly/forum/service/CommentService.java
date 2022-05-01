package com.ly.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.forum.pojo.Ccomment;
import com.ly.forum.pojo.Pcomment;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CommentService extends IService<Pcomment> {
    /*
    * 根据postId查询属于该条帖子的所有评论信息
    * */
    public List<Pcomment> findCommentsByPostId(int postId);

    /*
     * 插入父级评论
     * */
    public int saveParentComment(int userid, int postid, String content, int postCommentNum);

    /*
    * 插入子级评论
    * */
    public int saveChildComment(int userid, int postid, String content, int parentCommentNum, int postCommentNum, int pcid);

    /*
    * 根据pcid来查询父级评论的子级评论信息
    * */
    public List<Ccomment> findChildCommentByPcid(int pcid);

    /*
     * 查询该父级评论的子级评论数量
     * */
    public int findCommentNumByPcid(int pcid);

    /*
     * 增加父级评论的点赞数量
     * */
    public int addParentCommentSupportNum(int userId, int postId, int commentId, int parentCommentSupportNum);

    /*
    * 减少父级评论的点赞数量
    * */
    public int subParentCommentSupportNum(int userId, int postId, int commentId, int parentCommentSupportNum);

    /*
     * 增加子级评论的点赞数量
     * */
    public int addChildCommentSupportNum(int ccid, int childCommentSupportNum);

    /*
     * 查询当前用户是否点赞了某一条父级评论
     * */
    public int isSupportParentComment(int userId, int postId, int commentId);

}
