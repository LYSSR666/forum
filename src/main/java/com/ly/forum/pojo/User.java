package com.ly.forum.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    // 用户ID
    @TableId(value = "userid", type = IdType.AUTO)
    private Integer userid;

    private String userAvatar;

    private String username;

    private String password;

    private String motto;

    private String school;

    private String specialty;

    private String createTime;

    private String area;

    private int fansNum;

    private int focusNum;

    private int postNum;

    private int totalReadNum;

    private int interactionNum;

    private Integer iscollege;

}
