package com.itws.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ListToPage {

    /**
     * 将list转成page对象；供前端使用
     * @param list
     * @param pageable
     * @param <T>
     * @return
     */
    public static <T> Page<T> ListConvertPage(List<T> list, Pageable pageable){
        //当前页的第一条数据在list的位置
        int start= (int) pageable.getOffset();
        //当前页最后的一条数据在list中的位置
        int end=(start+pageable.getPageSize())>list.size()?list.size():(start+pageable.getPageSize());
        return new PageImpl<>(list.subList(start,end),pageable,list.size());
    }
}
