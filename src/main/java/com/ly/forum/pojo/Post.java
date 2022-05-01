package com.ly.forum.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 李阳
 * @since 2021-12-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Post implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "postid", type = IdType.AUTO)
    private Integer postid;

    private String postname;

    private String copywriting;

    private String forwardContent;

    private String imagelinks;

    private Integer forwardNum;

    private Integer supportNum;

    private Integer commentNum;

    private Integer readNum;

    private String publishTime;

    private Integer isForward;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Category category;

    @TableField(exist = false)
    private List<Pcomment> commentList;
    
}
