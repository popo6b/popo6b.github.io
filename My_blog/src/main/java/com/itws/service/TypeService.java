package com.itws.service;

import com.itws.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    void saveType(Type type);

    Type queryType(Long id);

    Page<Type> listType(Pageable pageable);//分页查询

    void updateType(Long id,Type tYpe);

    void deleteType(Long id);

    Type queryTypeByName(String name);

    List<Type> queryAll();

    List<Type> queryTypeTop(Integer size);


}
