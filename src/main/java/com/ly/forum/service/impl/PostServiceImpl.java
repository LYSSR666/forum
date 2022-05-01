package com.ly.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.forum.mapper.CommentMapper;
import com.ly.forum.mapper.UserMapper;
import com.ly.forum.pojo.*;
import com.ly.forum.mapper.PostMapper;
import com.ly.forum.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李阳
 * @since 2021-12-03
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    /*
     * 分页查询所有的帖子
     * */
    public PageBean<Post> findAllPost(int pageCode, int pageSize) {
        PageBean pageBean = new PageBean<Post>();
        // 查询总页数
        IPage <Post> page = new Page<>(pageCode, pageSize);
        long totalPage = postMapper.selectPage(page, null).getPages();
        // 查询每页要显示的帖子内容
        List<Post> postList = postMapper.findAllPost((pageCode-1)*pageSize, pageSize);
        pageBean.setPageCode(pageCode);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        pageBean.setBeanList(postList);
        return pageBean;
    }

    /*
     * 分页按时间发布顺序查询所有的帖子
     * */
    public PageBean<Post> findPostByPublishTime(int pageCode, int pageSize){
        PageBean pageBean = new PageBean<Post>();
        // 查询总页数
        int totalRecord = postMapper.findRecentPostRecord();
        int totalPage = totalRecord/pageSize;
        totalPage = totalRecord%pageSize==0 ? totalPage : totalPage+1;
        // 查询每页要显示的内容
        List<Post> postList = postMapper.findPostByPublishTime(pageCode, pageSize);
        pageBean.setPageCode(pageCode);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        pageBean.setBeanList(postList);
        return pageBean;
    }

    /*
     * 分页查询当前登录用户所关注的用户发布的帖子
     * */
    @Override
    public PageBean<Post> findCurUserFocusedPost(int userid, int pageCode, int pageSize) {
        PageBean pageBean = new PageBean<Post>();
        // 查询总页数
        int totalRecord = postMapper.findFocusedPostTotalRecord(userid);
        int totalPage = totalRecord/pageSize;
        totalPage = totalRecord%pageSize==0 ? totalPage : totalPage+1;
        // 查询每页要显示的内容
        List<Post> postList = postMapper.findCurUserFocusedPost(userid, (pageCode-1)*pageSize, pageSize);
        pageBean.setPageCode(pageCode);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        pageBean.setBeanList(postList);
        return pageBean;
    }

    /*
     * 根据分类类别来分页查询帖子信息
     * */
    @Override
    public PageBean<Post> findPostByCategory(Category category, int pageCode, int pageSize) {
        PageBean pageBean = new PageBean<Post>();
        // 查询总页数
        IPage <Post> page = new Page<>(pageCode, pageSize);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("categoryid", category.getCategoryid());
        long totalPage = postMapper.selectPage(page, wrapper).getPages();
        // 查询每页要显示的内容
        List<Post> postList = postMapper.findPostByCategory(category, (pageCode-1)*pageSize, pageSize);
        pageBean.setPageCode(pageCode);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        pageBean.setBeanList(postList);
        return pageBean;
    }

    /*
     * 分页查询所有高校发布的帖子
     * */
    @Override
    public PageBean<Post> findPostByCollegeUser(int pageCode, int pageSize){
        PageBean pageBean = new PageBean<Post>();
        // 查询总页数
        int totalRecord = postMapper.findCollegePostTotalRecords();
        int totalPage = totalRecord/pageSize;
        totalPage = totalRecord%pageSize==0 ? totalPage : totalPage+1;
        // 查询每页要显示的内容
        List<Post> postList = postMapper.findPostByCollegeUser((pageCode-1)*pageSize, pageSize);
        pageBean.setPageCode(pageCode);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        pageBean.setBeanList(postList);
        return pageBean;
    }

    /*
     * 分页查询话题列表
     * */
    public PageBean<Post> findTopicList(int pageCode, int pageSize){
        PageBean<Post> pageBean = new PageBean<Post>();
        // 查询总页数
        IPage <Post> page = new Page<>(pageCode, pageSize);
        long totalPage = postMapper.selectPage(page, null).getPages();
        List<Post> postList = postMapper.findTopicList((pageCode-1)*pageSize, pageSize);
        pageBean.setPageCode(pageCode);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        pageBean.setBeanList(postList);
        return pageBean;
    }

    /*
     * 查询热搜榜列表
     * */
    public PageBean<Post> hotSearchList(int pageCode, int pageSize){
        PageBean<Post> pageBean = new PageBean<Post>();
        // 查询总页数
        IPage <Post> page = new Page<>(pageCode, pageSize);
        long totalPage = postMapper.selectPage(page, null).getPages();
        List<Post> postList = postMapper.hotSearchList((pageCode-1)*pageSize, pageSize);
        pageBean.setPageCode(pageCode);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        pageBean.setBeanList(postList);
        return pageBean;
    }

    /*
     * 查询当前登录用户的所有帖子信息
     * */
    @Override
    public PageBean<Post> findAllPersonalPost(int userid, int pageCode, int pageSize) {
        PageBean pageBean = new PageBean<Post>();
        // 查询总页数
        IPage <Post> page = new Page<>(pageCode, pageSize);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userid", userid);
        int totalRecord = postMapper.selectCount(wrapper);
        long totalPage = postMapper.selectPage(page, wrapper).getPages();
        // 查询每页要显示的内容
        List<Post> postList = postMapper.findAllPersonalPost(userid, (pageCode-1)*pageSize, pageSize);
        pageBean.setPageCode(pageCode);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        pageBean.setTotalRecord(totalRecord);
        pageBean.setBeanList(postList);
        return pageBean;
    }

    /*
    * 查询当前用户收藏的帖子
    * */
    @Override
    public PageBean<Post> findUserCollectedPost(int userid, int pageCode, int pageSize) {
        PageBean<Post> pageBean = new PageBean<>();
        int totalRecord = postMapper.findUserCollectPostRecord(userid);
        int totalPage = totalRecord/pageSize;
        totalPage = totalRecord%pageSize==0 ? totalPage : totalPage+1;
        List<Post> postList = postMapper.findUserCollectedPost(userid, (pageCode-1)*pageSize, pageSize);
        pageBean.setPageCode(pageCode);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        pageBean.setTotalRecord(totalRecord);
        pageBean.setBeanList(postList);
        return pageBean;
    }

    /*
     * 收藏帖子
     * */
    @Override
    public int collectPost(int currentUserId, int postId){
        int resultNum = postMapper.collectPost(currentUserId, postId);
        if (resultNum == 1){
            return 0;
        }
        return -1;
    }

    /*
     * 取消收藏帖子
     * */
    public int deleteCollectPost(int currentUserId, int postId){
        int resultNum = postMapper.deleteCollectPost(currentUserId, postId);
        if (resultNum == 1){
            return 0;
        }
        return -1;
    }

    /*
    * 根据关键字进行模糊查询
    * */
    @Override
    public PageBean<Post> findPostByKeyword(String keyword, int pageCode, int pageSize) {
        //System.out.println(keyword);
        PageBean<Post> pageBean = new PageBean<>();
        int totalRecord = postMapper.findPostByKeywordRecord("%"+keyword+"%");
        int totalPage = totalRecord/pageSize;
        totalPage = totalRecord%pageSize==0 ? totalPage : totalPage+1;
        if (totalRecord == 0){
            totalPage = 1;
        }
        List<Post> postList = postMapper.findPostByKeyword("%"+keyword+"%", (pageCode-1)*pageSize, pageSize);
        pageBean.setPageCode(pageCode);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        pageBean.setBeanList(postList);
        return pageBean;
    }

    /*
    * 保存新发布帖子信息
    * */
    public int savePost(Post post){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        post.setPublishTime(date);
        int resultNum = postMapper.savePost(post);
        return resultNum;
    }

    /*
     * 根据postId查询帖子的评论数
     * */
    @Override
    public int findCommentNumByPostId(int postId) {
        int commentNum = postMapper.findCommentNumByPostId(postId);
        return commentNum;
    }

    /*
    * 增加帖子的点赞数量
    * */
    @Override
    public int addPostSupportNum(int postId, int postSupportNum, int userId) {
        int resultNum = postMapper.addPostSupportNum(postId, postSupportNum+1);
        int effectRecordNum = postMapper.recordSupportPost(userId, postId);
        if(resultNum==1 && effectRecordNum==1){
            return 1;
        }
        return -1;
    }

    /*
    * 取消帖子点赞数量
    * */
    @Override
    public int subPostSupportNum(int postId, int postSupportNum, int userId){
        int resultNum = postMapper.subPostSupportNum(postId, postSupportNum-1);
        int effectRecordNum = postMapper.delSupportPost(userId, postId);
        if(resultNum==1 && effectRecordNum==1){
            return 1;
        }
        return -1;
    }

    /*
    * 查询帖子的点赞数量
    * */
    @Override
    public int findPostSupportNum(int postId){
        int supportNum = postMapper.findPostSupportNum(postId);
        return supportNum;
    }

    /*
     * 查询阅读量最多的10篇帖子
     * */
    @Override
    public List<Post> findPostByReadNum() {
        List<Post> postList = postMapper.findPostByReadNum();
        return postList;
    }

    /*
     * 保存转发的帖子
     * */
    @Override
    public int saveForwardPost(Post post) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        post.setPublishTime(date);
        int resultNum = postMapper.saveForwardPost(post);
        if (resultNum == 1){
            // 转发后转发数量加1
            postMapper.addPostForwardNum(post.getPostid(), post.getForwardNum()+1);
            // 转发后插入转发记录
            postMapper.addForwardRecord(post.getUser().getUserid(), post.getPostid());
        }
        System.out.println("执行了Service....");
        System.out.println(post);
        return resultNum;
    }

    /*
     * 根据postId来查找转发数量
     * */
    @Override
    public int findForwardNumByPostId(int postId) {
        int forwardNum = postMapper.findForwardNumByPostId(postId);
        return forwardNum;
    }

}
