package com.itws.web;


import com.github.pagehelper.PageInfo;
import com.itws.pojo.Blog;
import com.itws.pojo.BlogParam;
import com.itws.pojo.Type;
import com.itws.service.BlogService;
import com.itws.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    /**
     * 分类查询
     * @param page 页码数
     * @param size
     * @param id typeId
     * @param model 返回activeTypeId 用于前台业务
     * @return
     */
    @RequestMapping("/types/{id}")
    public String types(@RequestParam(required = true,name = "page",defaultValue = "1")int page,@RequestParam(required = true,name = "size",defaultValue = "8")int size, @PathVariable Long id, Model model){
        List<Type> types = typeService.queryTypeTop(1000);
        BlogParam blogParam=new BlogParam();
        if(id==-1) {
            //如果id=-1；就说明我们从首页第一次茶轩分类，我们就把id设置为我们查询出来的list集合第一个元素；
            id=types.get(0).getId();
            blogParam.setTypeId(id);
        }else{
            blogParam.setTypeId(id);
        }
        List<Blog> blogs = blogService.queryBlogByTypeId(blogParam, page, size);
        PageInfo pageInfo=new PageInfo(blogs);
        model.addAttribute("types",types);
        model.addAttribute("page",pageInfo);
        model.addAttribute("activeTypeId",id);

        return "types";
    }
}
