package com.itws.web;

import com.github.pagehelper.PageInfo;
import com.itws.pojo.Blog;
import com.itws.pojo.Tag;
import com.itws.pojo.Type;
import com.itws.service.BlogService;
import com.itws.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {
    
    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/tags/{id}")
    public String tags(@RequestParam(required = true,name = "page",defaultValue = "1")int page,
                       @RequestParam(required = true,name = "size",defaultValue = "8")int size, @PathVariable Long id, Model model){
        List<Tag> tags = tagService.queryTagTop(1000);
        if(id==-1){
            id=tags.get(0).getId();
        }
        List<Blog> blogs = blogService.queryBlogByTagId(id);
        for (Blog blog : blogs) {
            List<Tag> tagList = tagService.queryTagByBlogId(blog.getId());
            blog.setTags(tagList);
        }
        PageInfo pageInfo=new PageInfo(blogs);
        model.addAttribute("page",pageInfo);
        model.addAttribute("tags",tags);
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
