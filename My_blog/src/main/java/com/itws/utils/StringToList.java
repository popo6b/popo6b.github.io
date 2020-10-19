package com.itws.utils;

import java.util.ArrayList;
import java.util.List;

public class StringToList {

    public static List<Long> stringConvertList(String ids){
        List<Long> list=new ArrayList<>();
        if(!"".equals(ids)||ids!=null){
            String [] idarray=ids.split(",");
            for (int i=0;i< idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
