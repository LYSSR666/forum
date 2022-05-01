package com.ly.forum.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* 子级评论实体类
* */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ccomment {
    private int ccid;

    private String childContent;

    private String publishTime;

    private int supportNum;

    @TableField(exist = false)
    private Pcomment parentComment;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Post post;
}
