package com.customtable.mapper;

import com.customtable.bo.BaseList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseTableMapper {

      List<BaseList> queryList();

      int addListTable(BaseList base);
}
