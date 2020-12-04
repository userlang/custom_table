package com.customtable.service.impl;

import com.customtable.bo.BaseList;
import com.customtable.bo.BaseListColumn;
import com.customtable.bo.BaseUserListColumn;
import com.customtable.mapper.BaseTableColumnMapper;
import com.customtable.mapper.BaseTableMapper;
import com.customtable.mapper.BaseTableUserColumnMapper;
import com.customtable.service.BaseTableUserColumnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BaseTableUserColumnServiceImpl implements BaseTableUserColumnService {



    @Resource
    private BaseTableColumnMapper baseListColumnMapper;
    @Resource
    private BaseTableUserColumnMapper baseUserListColumnMapper;


    @Override
    public List<BaseListColumn> queryListColumn(String listCode) {
        return baseListColumnMapper.queryListColumnByListCode(listCode);
    }

    @Override
    public List<BaseUserListColumn> queryListColumnByCondition(String listCode, Integer userId) {
        return baseUserListColumnMapper.queryListColumnByCondition(listCode,userId);
    }

    @Override
    public int insertBaseUserListColumn(BaseUserListColumn info) {
        return baseUserListColumnMapper.insertBaseUserListColumn(info);
    }

    @Override
    public int delUserColumn(BaseUserListColumn info) {
        return baseUserListColumnMapper.delUserColumn(info);
    }

    @Override
    public int addListColumn(BaseListColumn info) {
        return baseListColumnMapper.addListColumn(info);
    }

    @Override
    public int delListColumn(BaseListColumn info) {
        return baseListColumnMapper.delListColumn(info);
    }
}
