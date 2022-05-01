package com.ly.forum.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
* 父级评论实体类
* */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pcomment {
    @TableId(value = "userid", type = IdType.AUTO)
    private int pcid;

    private String parentContent;

    private String publishTime;

    private int commentNum;

    private int supportNum;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Post post;

    @TableField(exist = false)
    private List<Ccomment> childrenCommentList;
}
