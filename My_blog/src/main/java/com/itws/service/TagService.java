package com.itws.service;

import com.itws.pojo.Tag;
import com.itws.pojo.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    Tag queryById(Long id);

    /**
     * 保存Tag
     * @param tag
     */
    void save(Tag tag);

    Page<Tag> queryList(Pageable pageable);

    void deleteById(Long id);

    void update(Long id,Tag tag);

    Tag  queryByName(String name);

    List<Tag> queryAll();

    List<Tag> queryByIds(List<Long> ids);
    List<Tag> queryByNames(List<String> names);

    void queryUseTag();

    List<String> queryUserTagName();

    List<Tag> queryTagTop(Integer size);

    List<Tag> queryTagByBlogId(Long id);

}
