package com.ly.forum.controller;


import com.ly.forum.pojo.Category;
import com.ly.forum.service.CategoryService;
import com.ly.forum.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 李阳
 * @since 2021-12-03
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @RequestMapping("/findAllCategory")
    public List<Category> findAllCategory(){
        List<Category> categoryList = categoryServiceImpl.findAllCategory();
        return categoryList;
    }

}

