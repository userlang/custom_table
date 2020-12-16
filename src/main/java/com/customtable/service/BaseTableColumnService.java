package com.customtable.service;

public interface BaseTableColumnService {

    int closeTableColumn(String listCode, String columnCode, int i);

    int openTableColumn(String listCode, String columnCode, int i);
}
