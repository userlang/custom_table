package com.customtable.service;

import com.customtable.bo.BaseList;

import java.util.List;

public interface BaseTableService {
    //添加列表
    int addListTable(BaseList base);


    List<BaseList> queryListNameAll();
}
