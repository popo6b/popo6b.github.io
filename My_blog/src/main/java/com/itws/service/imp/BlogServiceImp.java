package com.itws.service.imp;

import com.github.pagehelper.PageHelper;
import com.itws.dao.BlogDao;
import com.itws.pojo.Blog;
import com.itws.pojo.BlogParam;
import com.itws.pojo.Tag;
import com.itws.service.BlogService;
import com.itws.utils.DateToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class BlogServiceImp implements BlogService {

    @Autowired
    private BlogDao blogDao;
    @Override
    public List<Blog> queryBlog(BlogParam param,int page,int size) {
        String orderBy="views"+" desc";
        PageHelper.startPage(page,size,orderBy);
        List<Blog> blogs = blogDao.queryBlog(param);
        for (Blog blog:blogs){
            blog.setUpdateTimeStr(DateToString.DateConvertString(blog.getUpdateTime()));
        }
        return blogs;
    }




    @Override
    public List<Blog> queryBlog(int page, int size) {
        String orderBy="views"+" desc";
        PageHelper.startPage(page,size,orderBy);
        List<Blog> blogs = blogDao.queryBlogToIndex();
        return blogs;
    }

    @Override
    public List<Blog> queryBlogByTypeId(BlogParam blogParam,int page,int size) {
        String orderBy="views"+" desc";
        PageHelper.startPage(page,size,orderBy);
        List<Blog> blogs = blogDao.queryBlogByTypeId(blogParam);
        for (Blog blog:blogs){
            blog.setUpdateTimeStr(DateToString.DateConvertString(blog.getUpdateTime()));
        }
        return blogs;
    }

    @Override
    public void save(Blog blog) {
        //在保存之前我们对一些数据进行操作
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //添加完数据后返回插入数据库的主键
        blogDao.save(blog);
        addBlogToFlags(blog.getId(),blog.getTags());

    }

    @Transactional
    @Override
    public Blog queryById(Long id) {
        Blog blog = blogDao.queryById(id);
        Integer views=blog.getViews()+1;
        blogDao.updateBlogViews(views,id);
        return blog;
    }

    @Override
    public void deleteById(Long id) {
        blogDao.deleteBlogToTags(id);
        blogDao.deleteBlogById(id);

    }

    @Transactional
    @Override
    public void update( Blog blog) {
        blog.setUpdateTime(new Date());
        blogDao.updateBlogById(blog);
        blogDao.deleteBlogToTags(blog.getId());
        addBlogToFlags(blog.getId(), blog.getTags());
    }

    @Override
    public void addBlogToFlags(Long blogId, List<Tag> tags) {
        for (Tag tag : tags) {
            blogDao.saveBlogToTags(blogId, tag.getId());
        }
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size){
        String orderBy="updateTime desc";
        PageHelper.startPage(1,size,orderBy);
        return blogDao.listRecommendBlogTop(size);
    }

    @Override
    public List<Blog> listBySearch(String search, int page, int size) {
        String orderBy="updateTime desc";
        PageHelper.startPage(page,size,orderBy);
        return blogDao.listBlogBySearch("%"+search+"%");
    }

    @Override
    public List<Blog> queryBlogByTagId(Long id) {
        return blogDao.queryBlogByTagId(id);
    }

    @Override
    public Map<String, List<Blog>> queryBlogsByTime() {
        HashMap<String,List<Blog>> blogMap=new HashMap<>();
        List<String> dates = blogDao.queryBlogTime();
        for (String date : dates) {
            List<Blog> blogs = blogDao.queryBlogByTime(date);
            blogMap.put(date,blogs);
        }
        return blogMap;
    }

    @Override
    public Integer queryBlogCount() {
        return blogDao.queryBlogCount();
    }


}
