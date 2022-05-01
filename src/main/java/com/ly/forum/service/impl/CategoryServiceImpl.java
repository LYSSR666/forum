package com.ly.forum.service.impl;

import com.ly.forum.pojo.Category;
import com.ly.forum.mapper.CategoryMapper;
import com.ly.forum.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    /*
    * 查询所有的分类名称
    * 因为是单表查询，可直接调用官方提供的api
    * */
    public List<Category> findAllCategory(){
        List<Category> categoryList = categoryMapper.selectList(null);
        return categoryList;
    }
}
