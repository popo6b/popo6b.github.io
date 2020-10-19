package com.itws.service.imp;

import com.github.pagehelper.PageHelper;
import com.itws.dao.TagDao;
import com.itws.pojo.Tag;
import com.itws.pojo.Type;
import com.itws.service.TagService;
import com.itws.utils.ListToPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagServiceImp implements TagService {
    @Autowired
    private TagDao tagDao;
    @Override
    public Tag queryById(Long id) {
        return tagDao.queryById(id);
    }

    @Override
    public void save(Tag tag) {
        tagDao.save(tag);
    }

    @Override
    public Page<Tag> queryList(Pageable pageable) {
        List<Tag> tags = tagDao.queryAll();
        Page<Tag> page = ListToPage.ListConvertPage(tags, pageable);
        return page ;
    }

    @Override
    public void deleteById(Long id) {
        tagDao.deleteById(id);
    }

    @Override
    public void update(Long id, Tag tag) {
        tagDao.updateTag(id,tag);
    }

    @Override
    public Tag queryByName(String name) {
        return tagDao.queryByName(name);
    }

    @Override
    public List<Tag> queryAll() {
        return tagDao.queryAll();
    }

    @Override
    public List<Tag> queryByIds(List<Long> ids) {
        return tagDao.queryByIds(ids);
    }

    @Override
    public List<Tag> queryByNames(List<String> names) {
        return tagDao.queryByNames(names);
    }

    @Override
    public void queryUseTag() {

    }

    @Override
    public List<String> queryUserTagName() {
        return tagDao.queryUseTagName();
    }

    @Override
    public List<Tag> queryTagTop(Integer size) {
        PageHelper.startPage(1,size);
        return tagDao.queryTagTop();
    }

    @Override
    public List<Tag> queryTagByBlogId(Long id) {
        return tagDao.queryTagByBlogId(id);
    }


}
