package com.ly.forum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.forum.mapper.CommentMapper;
import com.ly.forum.mapper.PostMapper;
import com.ly.forum.pojo.Ccomment;
import com.ly.forum.pojo.Pcomment;
import com.ly.forum.pojo.Post;
import com.ly.forum.pojo.User;
import com.ly.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Pcomment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostMapper postMapper;

    /*
     * 根据postId查询属于该条帖子的所有评论信息
     * */
    @Override
    public List<Pcomment> findCommentsByPostId(int postId) {
        List<Pcomment> parentCommentList = commentMapper.findParentCommentByPostId(postId);
        for(Pcomment pcomment: parentCommentList){
            List<Ccomment> ccommentList = commentMapper.findChildComment(postId, pcomment.getPcid());
            pcomment.setCommentNum(ccommentList.size());
            pcomment.setChildrenCommentList(ccommentList);
        }
        return parentCommentList;
    }

    /*
    * 插入父级评论
    * */
    @Override
    public int saveParentComment(int userId, int postId, String content, int postCommentNum) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        Pcomment pcomment = new Pcomment();
        // 封装数据
        pcomment.setParentContent(content);
        pcomment.setPublishTime(date);
        User user = new User();
        Post post = new Post();
        user.setUserid(userId);
        post.setPostid(postId);
        pcomment.setUser(user);
        pcomment.setPost(post);
        //进行插入操作
        int saveResultNum = commentMapper.saveParentComment(pcomment);
        System.out.println("插入的条数：" + saveResultNum);
        //修改帖子的评论数量
        int updateResultNum = postMapper.updateCommentNum(postId, postCommentNum+1);
        System.out.println("修改的条数：" + updateResultNum);
        if(saveResultNum==1 && updateResultNum==1){
            return 0;
        }
        return -1;
    }

    /*
    * 插入子级评论
    * */
    @Override
    public int saveChildComment(int userid, int postid, String content, int parentCommentNum, int postCommentNum, int pcid) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        Ccomment childComment = new Ccomment();
        Pcomment parentComment = new Pcomment();
        User user = new User();
        Post post = new Post();
        parentComment.setPcid(pcid);
        user.setUserid(userid);
        post.setPostid(postid);
        childComment.setChildContent(content);
        childComment.setPublishTime(date);
        childComment.setParentComment(parentComment);
        childComment.setUser(user);
        childComment.setPost(post);
        //进行插入操作
        int saveResultNum = commentMapper.saveChildComment(childComment);
        //修改父级评论的评论数量
        int updateParentCommentNum = commentMapper.updateParentCommentNum(pcid, parentCommentNum+1);
        //修改帖子评论数量
        int updatePostCommentNum = postMapper.updateCommentNum(postid, postCommentNum+1);
        if(saveResultNum==1 && updateParentCommentNum==1 && updatePostCommentNum==1){
            return 0;
        }
        return -1;
    }

    /*
    * 根据pcid来查询父级评论的子级评论信息
    * */
    @Override
    public List<Ccomment> findChildCommentByPcid(int pcid) {
        List<Ccomment> childCommentList = commentMapper.findChildCommentByPcid(pcid);
        return childCommentList;
    }

    /*
     * 查询该父级评论的子级评论数量
     * */
    @Override
    public int findCommentNumByPcid(int pcid) {
        int commentNum = commentMapper.findCommentNumByPcid(pcid);
        return commentNum;
    }

    /*
     * 增加父级评论的点赞数量
     * */
    @Override
    public int addParentCommentSupportNum(int userId, int postId, int commentId, int parentCommentSupportNum) {
        int saveRecordNum = commentMapper.saveSupportCommentRecord(userId, postId, commentId);
        int updateResultNum = commentMapper.addParentCommentSupportNum(commentId, parentCommentSupportNum+1);
        if (saveRecordNum == 1 && updateResultNum == 1){
            int supportNum = commentMapper.findParentCommentSupportNum(commentId);
            return supportNum;
        }
        return -1;
    }

    /*
    * 减少父级评论的的点赞数量
    * */
    @Override
    public int subParentCommentSupportNum(int userId, int postId, int commentId, int parentCommentSupportNum){
        int delRecordNum = commentMapper.delSupportCommentRecord(userId, postId, commentId);
        int updateResultNum = commentMapper.subParentCommentSupportNum(commentId, parentCommentSupportNum-1);
        if (delRecordNum == 1 && updateResultNum == 1){
            int supportNum = commentMapper.findParentCommentSupportNum(commentId);
            return supportNum;
        }
        return -1;
    }

    /*
     * 增加子级评论的点赞数量
     * */
    @Override
    public int addChildCommentSupportNum(int ccid, int childCommentSupportNum) {
        int updateResultNum = commentMapper.addChildCommentSupportNum(ccid, childCommentSupportNum+1);
        if (updateResultNum == 1){
            int supportNum = commentMapper.findChildCommentSupportNum(ccid);
            return supportNum;
        }
        return -1;
    }

    /*
     * 查询当前用户是否点赞了某一条父级评论
     * */
    @Override
    public int isSupportParentComment(int userId, int postId, int commentId){
        int resultNum = commentMapper.isSupportParentComment(userId, postId, commentId);
        if (resultNum == 1){
            return 0;
        }
        return -1;
    }

}
