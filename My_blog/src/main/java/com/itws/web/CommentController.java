package com.itws.web;


import com.itws.pojo.Comment;
import com.itws.pojo.User;
import com.itws.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        //我们这里做了处理，我们先查询blogid对应的comment，但是内置还设置了一个条件就是parentcommentId=null
        //我们相当于就先把顶级comment给查询出来
        List<Comment> comments =commentService.queryCommentsById(blogId);
        //为了前台业务进行显示我们把所有除顶级评论以外的所有评论归咎于一个集合中用于前台展示
        secondComment(comments);
        model.addAttribute("comments",comments);
        return "blog :: commentList";
    }


    @PostMapping("/comments/save.do")
    public String save(Comment comment, HttpSession session){
       Long BlogId=comment.getBlog().getId();
       comment.setBlogId(BlogId);
       User user = (User) session.getAttribute("user");
        if (user == null) {
            comment.setAdminComment(false);
            comment.setAvatar(avatar);
        } else {
            comment.setAdminComment(true);
            comment.setAvatar(user.getAvatar());
            comment.setNickName(user.getNickName());
        }
        commentService.save(comment);
        return  "redirect:/comments/"+comment.getBlog().getId();
    }


    List<Comment> returnComment=new ArrayList<>();

    /**
     * 遍历集合comments中的每一个comment查询出来的所有子comment，可能出现子还有子，我们就进行一个递归调用
     * @param comments 传入的顶级集合comments里面都是顶级的comment
     * @return
     */
    public void secondComment(List<Comment> comments){
        for (Comment comment : comments) {
            List<Comment> comments1 = commentService.queryCommentByParentId(comment.getId());
            if(comments1==null||comments1.size()==0){
                continue;
            }
            returnComment.addAll(comments1);
            secondComment(comments1);
            if(comment.getParentcommentId()==null) {
                List<Comment> comments2=new ArrayList<>();//集合调用的是指向；我们要把原来的这个集合复制一份，然后再清空，否则我们的数据也会被清空
                comments2.addAll(returnComment);//
                comment.setReplyComments(comments2);
                returnComment.clear();
            }
        }
    }

}
