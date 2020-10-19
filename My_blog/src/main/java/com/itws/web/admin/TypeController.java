package com.itws.web.admin;

import com.itws.pojo.Type;
import com.itws.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;


    @RequestMapping("/types")
    public String types(@PageableDefault(size=6,sort={"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        Page<Type> page = typeService.listType(pageable);
        model.addAttribute("page",page);
        return "admin/types";
    }

    @RequestMapping("/types/input")
    public String typesInput(Model model){
        model.addAttribute("type",new Type());
        return "admin/types_input";
    }

    @RequestMapping("/types/{id}/input")
    public String updateInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.queryType(id));
        return "admin/types_input";
    }

    @PostMapping("/types.do")
    public String save(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type type1 = typeService.queryTypeByName(type.getName());
        if(type1!=null){
            result.rejectValue("name","nameError","不能重复叠加分类");
        }
        if(result.hasErrors()){
            return "admin/types_input";
        }
        try {
            typeService.saveType(type);
            attributes.addFlashAttribute("message","操作成功");
        } catch (Exception e) {
            attributes.addFlashAttribute("message","操作失败");
            e.printStackTrace();
        }
        return "redirect:/admin/types";
    }

    /**
     *
     * @param type 前端请求传入的参数封装成一个type对象，并且我们使用JSR303进行后端数据校验
     * @param result 用于接收bean的校验信息
     * @param id 使用restful风格占位符，获取请求参数
     * @param attributes 重定向域，用户重定向后给前端一些消息提示
     * @return
     */
    @RequestMapping("/types/update.do/{id}")
    public String update(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes){
        Type type1 = typeService.queryTypeByName(type.getName());
        if(type1!=null){
            result.rejectValue("name","nameError","不能重复叠加分类");
        }
        if(result.hasErrors()){
            return "admin/types_input";
        }
        try {
            typeService.updateType(id,type);
            attributes.addFlashAttribute("message","操作成功");
        } catch (Exception e) {
            attributes.addFlashAttribute("message","操作失败");
            e.printStackTrace();
        }
        return "redirect:/admin/types";
    }

    @RequestMapping("/types/{id}/delete.do")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:/admin/types";

    }


}
