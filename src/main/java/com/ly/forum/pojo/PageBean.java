package com.ly.forum.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

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
public class PageBean<T> {
    //当前页码 pageCode
    private int pageCode;
    //总页数 totalPage
    private long totalPage;
    //每页记录数 pageSize
    private int pageSize;
    //总记录数 totalRecord
    private int totalRecord;
    //每页的对象列表 beanList
    private List<T> beanList;
}
