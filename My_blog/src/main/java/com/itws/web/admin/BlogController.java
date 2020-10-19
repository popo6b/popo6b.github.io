package com.itws.web.admin;


import com.github.pagehelper.PageInfo;
import com.itws.pojo.Blog;
import com.itws.pojo.BlogParam;
import com.itws.pojo.Tag;
import com.itws.pojo.User;
import com.itws.service.BlogService;
import com.itws.service.TagService;
import com.itws.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    public static final String BLOGS="admin/blogs";
    public static final String BLOGS_SEARCH="admin/blogs :: blogList";
    public static final String BLOGS_ADD="admin/blogs_input";
    public static final String BLOGS_ADD_SUCCESS="redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@RequestParam(name = "page",required = true,defaultValue = "1") int page,@RequestParam(name = "pageSize",required = true,defaultValue = "5") int size ,BlogParam param, Model model){
        List<Blog> blogs = blogService.queryBlog(param,page,size);
        PageInfo pageInfo=new PageInfo(blogs);
        model.addAttribute("types",typeService.queryAll());
        model.addAttribute("page",pageInfo);
        return BLOGS;
    }

    /**
     * 使用这个方法来做前端页面局部刷新使用thymeleaf的fragment来实现局部刷新，类似于ajax一样的效果
     * @return
     */
    @PostMapping("/blogs/search")
    public String search(@RequestParam(name = "page",required = true,defaultValue = "1") int page,@RequestParam(name = "pageSize",required = true,defaultValue = "5")int size,  BlogParam param, Model model){
        List<Blog> blogs = blogService.queryBlog(param,page,size);
        PageInfo pageInfo=new PageInfo(blogs);
        model.addAttribute("page",pageInfo);
        return BLOGS_SEARCH;
    }

    /**
     * 进入页面跳转，跳转到新增页面
     * @param model
     * @return
     */
    @GetMapping("/blogs/add")
    public String blogs_add(Model model){
        //为了我们做修改的时候在修改页面拿到一些值
        add_update(model);
        model.addAttribute("blog",new Blog());
        return BLOGS_ADD;
    }

    @RequestMapping("/blogs/{id}/delete.do")
    public String blogs_delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteById(id);
        attributes.addFlashAttribute("message","操作成功");
        return BLOGS_ADD_SUCCESS;

    }

    public void add_update(Model model){
        //为了我们做修改的时候在修改页面拿到一些值
        model.addAttribute("types", typeService.queryAll());
        model.addAttribute("tags",tagService.queryAll());
    }

    @RequestMapping("/blogs/{id}/update")
    public String blogs_update(@PathVariable Long id, Model model){
        //为了我们做修改的时候在修改页面拿到一些值
        add_update(model);
        Blog blog = blogService.queryById(id);
        blog.init();
        model.addAttribute("blog",blog);
        return BLOGS_ADD;
    }

    @PostMapping("/blogs/add.do")
    public String blogs_add_submit( Blog blog, HttpSession httpSession, RedirectAttributes attributes){
        User user = (User) httpSession.getAttribute("user");
        // 前端给我们传过来的是一个typeId，然后我们使用typeService去查询出type然后保存到我们的blog里面
        blog.setType(typeService.queryType(blog.getType().getId()));
        /**
      String tagIds = blog.getTagIds();
        //自写一个工具类的方法将传过来的String转成list，为了我们后端mybatis查询数据库的时候使用
//        List<Long> list = StringToList.stringConvertList(tagIds);
//        System.out.println(list);
//        List<Tag> tags = tagService.queryByIds(list);
         **/

        //将前端的所有tagName获取到；
        List<String> tagName = blog.getTagNames();


        //因为前端传过来的标签可能是我们数据库没有的，我们就要新加，
        //1.现在查询出来的都是数据库已有的
        if(tagName.size()>0 && tagName!=null) {
            List<Tag> tags = tagService.queryByNames(tagName);
            List<String> existName = new ArrayList<>();
            if (tags.size() > 0) {
                //用来存已有的name
                for (Tag tag : tags) {
                    existName.add(tag.getName());
                }
                //2.从原先前台查询出来的移除掉已有的，为了后面进行保存
                tagName.removeAll(existName);
            }
            // 3.如果移除了之后tagname的size>0;就说明要进行新增；新增完后再加入到tags里面
            if (tagName.size() > 0) {
                for (String s : tagName) {
                    Tag tag = new Tag();
                    tag.setName(s);
                    tagService.save(tag);
                    tags.add(tagService.queryByName(s));
                }
            }
            blog.setTags(tags);
        }
        try {
            if (blog.getId()!=null) {
                blogService.update(blog);
            } else {
                blog.setUser(user);
                blogService.save(blog);
            }
            attributes.addFlashAttribute("message", "操作成功");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "操作失败");
            e.printStackTrace();
        }
        return BLOGS_ADD_SUCCESS;

    }


}
