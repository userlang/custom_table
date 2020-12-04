package com.customtable.service;

import com.customtable.bo.BaseList;
import com.customtable.bo.BaseListColumn;
import com.customtable.bo.BaseUserListColumn;

import java.util.List;

public interface BaseTableUserColumnService {



    List<BaseListColumn> queryListColumn(String listCode);

    List<BaseUserListColumn> queryListColumnByCondition(String listCode, Integer userId);

    //用户添加某个列表的列
    int  insertBaseUserListColumn(BaseUserListColumn info);

    int delUserColumn(BaseUserListColumn info);

    //给列表 添加列
    int addListColumn(BaseListColumn info);

    //删除列表 列
    int delListColumn(BaseListColumn info);


}
