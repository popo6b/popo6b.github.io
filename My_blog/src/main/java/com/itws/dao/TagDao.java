package com.itws.dao;

import com.itws.pojo.Tag;
import com.itws.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagDao {
    /**
     * 通过id查询Tag
     * @param id
     * @return
     */
    Tag queryById(Long id);

    /**
     * 通过id删除标签
     * @param id
     */
    void deleteById(Long id);

    /**
     * 保存Tag
     * @param tag
     */
    void save(Tag tag);

    /**
     * 查询所有标签
     */
    List<Tag> queryAll();

    /**
     * 通过id更新Tag
     * @param id
     * @param tag
     */
    void updateTag(@Param("id") Long id,@Param("tag") Tag tag);

    /**
     * 通过name查询Tag
     * @param name
     * @return
     */
    Tag queryByName(String name);

    List<Tag> queryByIds(@Param("list") List<Long> list);

    /**
     * 通过一个集合条件（name）,把数据库中有含有name的tag全部查询出来
     * @param list
     * @return
     */
    List<Tag> queryByNames(@Param("list") List<String> list);

    /**
     * 查询出使用了博客的标签
     */
    void queryuseTag();

    /**
     * 查询出博客使用劳动id；并返回一个list
     */
    List<String> queryUseTagName();

    /**
     * 通过传入的size进行分页查询将查询的结果；查询有排序的将排序写在里面的，进行相关连的博客数降序查询
     * @param
     * @return
     */
    List<Tag> queryTagTop();

    /**
     * 通过传入的博客id查询这个博客下的所有标签
     * @param id
     * @return
     */
    List<Tag> queryTagByBlogId(Long id);
}
