package com.itws.dao;


import com.itws.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TypeDao {
    /**
     * 根据
     * @param id id号
     * @return 返回一个类型
     */
    Type queryById(Long id);

    /**
     * 查询多个type
     * @param
     * @return
     */
    List<Type> queryAll();

    /**
     * 保存type
     * @param t
     */
    void save(Type t);

    /**
     * 通过id找到指定修改的type
     * @param id
     * @param type
     * @return
     */
    void updateType(Long id,Type type);

    /**
     * 删除某个type
     * @param id
     */
    void deleteType(Long id);

    /**
     * 通过name查询某个type
     * @param name
     * @return
     */
    Type queryTypeByName(String name);

    /**
     * 根据传入的size来分页查询出type的前几个type;我们让博客表去关联type；然后我们通过typeid查询使用到这个typeid的博客有多少
     * @param size
     * @return
     */
    List<Type> queryTypeTop(Integer size);

}
