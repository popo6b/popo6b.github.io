package com.itws.web.admin;

import com.itws.pojo.Tag;
import com.itws.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequestMapping("/admin")
@Controller
public class TagController {

    @Autowired
    private TagService tagService;


    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        Page<Tag> page = tagService.queryList(pageable);
        model.addAttribute("page",page);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String tagsInput(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags_input";
    }

    /**
     * 保存前端传过来的Tag数据到数据库
     * @param tag 前端传过来的数据封装到Tag实体类中，并且使用JSR303进行后端数据校验
     * @param result 用于接收bean实体类中的校验信息
     * @param attributes 用于给前端一些信息提示
     * @return
     */
    @PostMapping("/tags.do")
    public String save(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tag1 = tagService.queryByName(tag.getName());
        if(tag1!=null){
            result.rejectValue("name","nameError","不能重复叠加标签");
        }
        if(result.hasErrors()){
            return "redirect:/admin/tags_input";
        }
        try {
            tagService.save(tag);
            attributes.addFlashAttribute("message","操作成功");
        } catch (Exception e) {
            attributes.addFlashAttribute("message","操作失败");
            e.printStackTrace();
        }
        return "redirect:/admin/tags";
    }

    @RequestMapping("/tags/update.do/{id}")
    public String update(@Valid Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes attributes){
        Tag tag1 = tagService.queryByName(tag.getName());
        if(tag1!=null){
            result.rejectValue("name","nameError","不能重复叠加标签");
        }
        if(result.hasErrors()){
            return "redirect:/admin/tags_input";
        }
        try {
            tagService.update(id,tag);
            attributes.addFlashAttribute("message","操作成功");
        } catch (Exception e) {
            attributes.addFlashAttribute("message","操作失败");
            e.printStackTrace();
        }
        return "redirect:/admin/tags";
    }


    @RequestMapping("/tags/{id}/delete.do")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteById(id);
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:/admin/tags";

    }
    @RequestMapping("/tags/{id}/input")
    public String updateInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.queryById(id));
        return "admin/tags_input";
    }

}
