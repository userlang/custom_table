package com.customtable.service.impl;

import com.customtable.mapper.BaseTableColumnMapper;
import com.customtable.service.BaseTableColumnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseTableColumnServiceImpl implements BaseTableColumnService {

    @Resource
    private BaseTableColumnMapper baseTableColumnMapper;

    @Override
    public int closeTableColumn(String listCode, String columnCode, int i) {
        return baseTableColumnMapper.modifyTableColumnDisplay(listCode,columnCode,i);
    }

    @Override
    public int openTableColumn(String listCode, String columnCode, int i) {
        return baseTableColumnMapper.modifyTableColumnDisplay(listCode,columnCode,i);
    }
}
