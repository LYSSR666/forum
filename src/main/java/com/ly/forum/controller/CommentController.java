package com.ly.forum.controller;

import com.ly.forum.pojo.Ccomment;
import com.ly.forum.pojo.Pcomment;
import com.ly.forum.pojo.User;
import com.ly.forum.service.CommentService;
import com.ly.forum.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /*
    * 根据帖子id来查询该条帖子所对应的评论信息
    * */
    @RequestMapping("/findCommentsByPostId")
    public List<Pcomment> findCommentsByPostId(int postid){
        List<Pcomment> pcommentList = commentService.findCommentsByPostId(postid);
        return pcommentList;
    }

    /*
    * 插入父级评论
    * */
    @RequestMapping("/saveParentComment")
    public int saveParentComment(Integer userid, Integer postid, String content, Integer postCommentNum){
        /* 如果内容为空则返回-1，表示插入失败 */
        if(userid==null || content==null || content.equals("")){
            return -1;
        }
        int resultNum = commentService.saveParentComment(userid, postid, content, postCommentNum);
        return resultNum;
    }

    /*
    * 插入子级评论
    * */
    @RequestMapping("/saveChildComment")
    public int saveChildComment(Integer userid, Integer postid, String content, Integer parentCommentNum, Integer postCommentNum, Integer pcid){
        /* 如果内容为空则返回-1，表示插入失败 */
        if(userid==null || content==null || content.equals("")){
            return -1;
        }
        int resultNum = commentService.saveChildComment(userid, postid, content, parentCommentNum, postCommentNum, pcid);
        return resultNum;
    }

    /*
    * 根据pcid来查询父级评论的子级评论信息
    * */
    @RequestMapping("/findChildCommentByPcid")
    public List<Ccomment> findChildCommentByPcid(int pcid){
        List<Ccomment> childCommentList = commentService.findChildCommentByPcid(pcid);
        return childCommentList;
    }

    /*
    * 查询该父级评论的子级评论数量
    * */
    @RequestMapping("/findCommentNumByPcid")
    public int findCommentNumByPcid(int pcid){
        int commentNum = commentService.findCommentNumByPcid(pcid);
        return commentNum;
    }

    /*
    * 增加父级评论的点赞数量
    * */
    @RequestMapping("/addParentCommentSupportNum")
    public int addParentCommentSupportNum(int userId, int postId, int commentId, int parentCommentSupportNum){
        int resultNum = commentService.addParentCommentSupportNum(userId, postId, commentId, parentCommentSupportNum);
        return resultNum;
    }

    /*
     * 减少父级评论的的点赞数量
     * */
    @RequestMapping("/subParentCommentSupportNum")
    public int subParentCommentSupportNum(int userId, int postId, int commentId, int parentCommentSupportNum){
        int resultNum = commentService.subParentCommentSupportNum(userId, postId, commentId, parentCommentSupportNum);
        return resultNum;
    }

    /*
    * 增加子级评论的点赞数量
    * */
    @RequestMapping("/addChildCommentSupportNum")
    public int addChildCommentSupportNum(int ccid, int childCommentSupportNum){
        int resultNum = commentService.addChildCommentSupportNum(ccid, childCommentSupportNum);
        return resultNum;
    }

    /*
     * 查询当前用户是否点赞了某一条父级评论
     * */
    @RequestMapping("/isSupportParentComment")
    public int isSupportParentComment(int userId, int postId, int commentId){
        int resultNum = commentService.isSupportParentComment(userId, postId, commentId);
        return resultNum;
    }

}
