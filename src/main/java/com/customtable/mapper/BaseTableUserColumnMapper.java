package com.customtable.mapper;

import com.customtable.bo.BaseUserListColumn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BaseTableUserColumnMapper {
    List<BaseUserListColumn> queryListColumnByCondition(@Param("listCode") String listCode,@Param("userId") Integer userId);

    int insertBaseUserListColumn(BaseUserListColumn info);

    int delUserColumn(BaseUserListColumn info);

    Integer queryUserTableColumn(String listCode, Integer userId, String columnCode);
}
