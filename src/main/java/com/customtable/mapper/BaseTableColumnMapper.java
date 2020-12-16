package com.customtable.mapper;

import com.customtable.bo.BaseListColumn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BaseTableColumnMapper {

    List<BaseListColumn> queryListColumnByListCode(@Param("listCode") String listCode);
    int addListColumn(BaseListColumn info);
    //删除列
    int delListColumn(BaseListColumn info);

    int modifyTableColumnDisplay(String listCode, String columnCode, int i);


    Integer queryUserTableColumn(Integer userId, String listCode, String columnCode);
}
