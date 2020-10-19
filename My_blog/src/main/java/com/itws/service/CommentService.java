package com.itws.service;

import com.itws.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> queryCommentsById(Long id);

    void save(Comment comment);

    List<Comment> queryCommentByParentId(Long id);
}
