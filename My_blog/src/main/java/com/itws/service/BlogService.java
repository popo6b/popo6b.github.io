package com.itws.service;

import com.itws.pojo.Blog;
import com.itws.pojo.BlogParam;
import com.itws.pojo.Tag;

import java.util.List;
import java.util.Map;

public interface BlogService {

    List<Blog> queryBlog( BlogParam param,int page,int size);

    List<Blog> queryBlog( int page,int size);

    List<Blog> queryBlogByTypeId(BlogParam blogParam,int page,int size);

    void save(Blog blog);

    Blog queryById(Long id);

    void deleteById(Long id);

    void update(Blog blog);

    void addBlogToFlags(Long blogId, List<Tag> tag);

    List<Blog> listRecommendBlogTop(Integer size);

    List<Blog> listBySearch(String search,int page,int size);

    List<Blog> queryBlogByTagId(Long id);


    /**
     * 查询各个时间段的博客分别放在一个map中，string是时间段-键，博客是值
     * @return
     */
    Map<String,List<Blog>> queryBlogsByTime();


    /**
     * 茶轩博客的数目
     * @return
     */
    Integer queryBlogCount();




}
