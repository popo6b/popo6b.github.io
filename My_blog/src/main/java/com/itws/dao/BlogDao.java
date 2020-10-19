package com.itws.dao;

import com.itws.pojo.Blog;
import com.itws.pojo.BlogParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author popo
 */
@Mapper
@Repository
public interface BlogDao {
    /**
     * 通过传入的参数查询博客
     * @param blogParam
     * @return 返回多条记录
     */
    List<Blog> queryBlog(BlogParam blogParam);

    /**
     * 通过传入的参数来查询博客
     * @param blogParam
     * @return
     */
    List<Blog> queryBlogByTypeId(BlogParam blogParam);

    /**
     * 查询全部博客
     * @return 通过list集合返回查询到的所有博客
     */
    List<Blog> queryBlogToIndex();

    /**
     * 保存博客信息
     * @param blog
     */
    void save(Blog blog);

    /**
     * 添加博客和标签表想关联的中间表
     * @param blogId
     * @param tagId
     */
    void saveBlogToTags(@Param("blogId") Long blogId, @Param("tagId") Long tagId);

    /**
     * 查询博客通过博客的id查询
     * @param id
     * @return 返回一条博客信息
     */
    Blog queryById(Long id);

    /**
     * 删除某条博客通过id属性
     * @param id
     */
    void deleteBlogById(Long id);

    /**
     * 删除博客表和标签表关联的中间表信息
     * @param blogId
     */
    void deleteBlogToTags(Long blogId);

    /**
     * 通过传入的参数修改某条博客的信息
     * @param blog
     */
    void updateBlogById(Blog blog);

    /**
     * 查询出推荐的博客，通过更新的时间
     * @param size
     * @return
     */
    List<Blog> listRecommendBlogTop(Integer size);

    /**
     * 通过搜索条件来查询博客
     * @param search
     * @return
     */
    List<Blog> listBlogBySearch(String search);

    /**
     * 通过传入的博客id修改博客的浏览次数
     * @param views 浏览次数
     * @param id 博客id
     */
    void updateBlogViews(@Param("views") Integer views,Long id);

    /**
     * 通过传入的tagid查询这个这个标签下的所有blog
     * @param id
     * @return
     */
    List<Blog> queryBlogByTagId(Long id);

    /**
     * 查询各个时间段对应的博客信息
     * @return
     */
    List<Blog> queryBlogByTime(String date);

    /**
     * 查询博客总的所有时间
     * @return
     */
    List<String> queryBlogTime();

    /**
     * 查询博客的数目
     * @return
     */
    Integer queryBlogCount();



}
