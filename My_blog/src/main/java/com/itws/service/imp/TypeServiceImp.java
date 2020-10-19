package com.itws.service.imp;

import com.github.pagehelper.PageHelper;
import com.itws.NotFoundException;
import com.itws.dao.TypeDao;
import com.itws.pojo.Type;
import com.itws.service.TypeService;
import com.itws.utils.ListToPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImp implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public void saveType(Type type) {
      typeDao.save(type);
    }

    @Override
    public Type queryType(Long id) {
        return typeDao.queryById(id);
    }

    @Override
    public Page<Type> listType(Pageable pageable) {

        List<Type> types = typeDao.queryAll();
        Page<Type> page = ListToPage.ListConvertPage(types, pageable);
        return page;

    }

    @Override
    public void updateType(Long id, Type type) {
        Type t=typeDao.queryById(id);
        if (t==null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        typeDao.save(t);
    }

    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }

    @Override
    public Type queryTypeByName(String name) {
        return typeDao.queryTypeByName(name);
    }

    @Override
    public List<Type> queryAll() {
        return typeDao.queryAll();
    }

    @Override
    public List<Type> queryTypeTop(Integer size) {
        PageHelper.startPage(1,size);
        return typeDao.queryTypeTop(size);
    }
}
