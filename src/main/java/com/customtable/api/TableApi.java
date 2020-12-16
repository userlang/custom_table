package com.customtable.api;

import com.customtable.bo.BaseList;
import com.customtable.service.BaseTableService;
import com.customtable.service.BaseTableUserColumnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags="TableApi")
public class TableApi {
    @Resource
    private BaseTableService baseTableService;

    /**
     * 查询当前系统的全部的列表
     */
    @ApiOperation("查询所有列表")
    @RequestMapping(value = "queryTableAll", method = RequestMethod.POST)
    public Map<String,Object> queryTableAll(){
        Map<String,Object> result=new HashMap<>();
        List<BaseList> r=baseTableService.queryListNameAll();
        result.put("data",r);
        return  result;
    }
    /**
     * 添加系统列表
     */

    @ApiOperation("添加列表信息")
    @RequestMapping(value = "addTable", method = RequestMethod.POST)
    public Map<String,Object> addTable(String listCode,String listName,String listDesc){
        Map<String,Object> result=new HashMap<>();
        BaseList base=new BaseList();
        base.setListCode(listCode);
        base.setListDesc(listDesc);
        base.setListName(listName);
        int i=baseTableService.addListTable(base);
        if(i>0){
            return queryTableAll();
        }
        result.put("message","添加失敗");
        result.put("code","-1");
        return result;

    }

    /**
     * 添加系统列表
     */

    @ApiOperation("禁用列表")
    @RequestMapping(value = "closeListCode", method = RequestMethod.POST)
    public Map<String,Object> closeListCode(String listCode){
        Map<String,Object> result=new HashMap<>();
        int i=baseTableService.modifyTableState(listCode,1);
        if(i>0){
            return queryTableAll();
        }
        result.put("message","禁用");
        result.put("code","-1");
        return result;

    }
    @ApiOperation("启用列表")
    @RequestMapping(value = "openListCode", method = RequestMethod.POST)
    public Map<String,Object> openListCode(String listCode){
        Map<String,Object> result=new HashMap<>();
        int i=baseTableService.modifyTableState(listCode,0);
        if(i>0){
            return queryTableAll();
        }
        result.put("message","启动失敗");
        result.put("code","-1");
        return result;

    }
}
