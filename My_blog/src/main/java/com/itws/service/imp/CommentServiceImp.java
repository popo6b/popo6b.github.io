package com.itws.service.imp;

import com.itws.dao.CommentDao;
import com.itws.pojo.Comment;
import com.itws.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Override
    public List<Comment> queryCommentsById(Long id) {
        return commentDao.queryCommentById(id);
    }

    @Transactional
    @Override
    public void save(Comment comment) {
        Long parentCommentId=comment.getParentComment().getId();
        if(parentCommentId!=-1){
            comment.setParentcommentId(comment.getParentComment().getId());
        }else {
            comment.setParentcommentId(null);
        }
        comment.setCreateTime(new Date());
        commentDao.save(comment);
    }


    @Override
    public List<Comment> queryCommentByParentId(Long id){
        return commentDao.queryCommentByParentId(id);
    }
}
