package com.customtable.service.impl;

import com.customtable.bo.BaseList;
import com.customtable.mapper.BaseTableMapper;
import com.customtable.service.BaseTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BaseTableServiceImpl implements BaseTableService {

    @Resource
    private BaseTableMapper baseTableMapper;

    @Override
    public int addListTable(BaseList base) {
        return baseTableMapper.addListTable(base);
    }

    @Override
    public List<BaseList> queryListNameAll() {
        return baseTableMapper.queryList();
    }

    @Override
    public int modifyTableState(String listCode,int i) {
        return baseTableMapper.modifyTableState(listCode,i);
    }
}
