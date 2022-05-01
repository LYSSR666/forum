package com.ly.forum.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PostVo implements Serializable {
    /*
    * 帖子信息
    * */
    private Post post;

    /*
    * 当前用户是否关注了发帖用户
    * */
    private boolean isFocusedUser;

    /*
    * 当前登录用户是否点赞了该帖子
    * */
    private boolean isSupportedPost;

    /*
    * 当前登录用户是否收藏了该帖子
    * */
    private boolean isCollectedPost;
}
