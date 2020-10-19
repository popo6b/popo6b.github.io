package com.itws.dao;

import com.itws.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {


    /**
     * 保存评论
     * @param comment
     */
    void save(Comment comment);

    /**
     * 通过传入的博客id查询这个博客下相关联的所有评论，但我们配置文件做了处理，因为满足前台业务我们就查询顶级的comment
     * 我们mapper。xml加了一个条件就是parentCommentid=null的查询出来
     * @param id 博客id
     * @return
     */
     List<Comment> queryCommentById(Long id);

    /**
     * 通过传入的parentid；茶轩这个parentid下的所有子comment
     * @param id
     * @return
     */
     List<Comment> queryCommentByParentId(Long id);
}
