package com.itws.web.admin;


import com.google.code.kaptcha.Constants;
import com.itws.pojo.LoginParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @GetMapping({"/","/login"})
    public String loginPage(){
        return "admin/login";
    }
    @RequestMapping("/index")
    public String index(){
        return "admin/index";
    }


    @PostMapping("/login.do")
    public String login(LoginParam loginParam, Model model, HttpServletRequest request){

        HttpSession session=request.getSession();
        String kaptchaSession = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (loginParam.getCode()!= null) {
           String code=loginParam.getCode();
           if(!kaptchaSession.equals(code)){
               model.addAttribute("msg","验证码错误");
               return "admin/login";
           }

        }

        //获取当前用户
        Subject subject= SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token=new UsernamePasswordToken(loginParam.getUsername(),loginParam.getPassword());
        try {
            subject.login(token);//执行登录，没有异常就说明登录成功
            return "redirect:/admin/index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
            return "admin/login";
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg","密码错误");
            return "admin/login";
        }


    }
}
