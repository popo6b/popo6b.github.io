package com.itws.web;


import com.github.pagehelper.PageInfo;
import com.itws.pojo.Blog;

import com.itws.service.BlogService;
import com.itws.service.TagService;
import com.itws.service.TypeService;
import com.itws.utils.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping({"/","/index"})
    public String index(@RequestParam(name = "page",defaultValue = "1",required = true)int page, Model model){
        List<Blog> blogs = blogService.queryBlog(page, 5);
        PageInfo pageInfo=new PageInfo(blogs);
        model.addAttribute("page",pageInfo);
        model.addAttribute("types",typeService.queryTypeTop(6));
        model.addAttribute("tags",tagService.queryTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String serach(@RequestParam(name = "page",defaultValue = "1",required = true)int page,@RequestParam(name = "size",defaultValue ="5",required = true )int size,String query,Model model){
        List<Blog> blogs = blogService.listBySearch(query, page, size);
        PageInfo pageInfo=new PageInfo(blogs);
        model.addAttribute("page",pageInfo);
        model.addAttribute("query",query);
        return "search";
    }

    @RequestMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        Blog blog = blogService.queryById(id);
        //调用写的utils的方法然后将markdown格式转成html
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        model.addAttribute("blog",blog);
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }




}
