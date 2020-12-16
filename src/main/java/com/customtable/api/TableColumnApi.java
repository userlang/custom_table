package com.customtable.api;

import com.customtable.bo.BaseListColumn;
import com.customtable.service.BaseTableColumnService;
import com.customtable.service.BaseTableUserColumnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags="TableColumnApi")
public class TableColumnApi {

    @Resource
    private BaseTableUserColumnService userListColumnService;

    @Resource
    private BaseTableColumnService baseTableColumnService;
    /**
     * 列表添加列
     */
    @ApiOperation("添加列表列")
    @RequestMapping(value = "addTableColumn",method = RequestMethod.POST)
    public Map<String,Object> addTableColumn(
            @ApiParam(value = "列表编码",name = "listCode") @RequestParam("listCode") String listCode,
            @ApiParam(value = "列编码",name = "columnCode") @RequestParam("columnCode") String columnCode,
            @ApiParam(value = "列显示名称",name = "columnName") @RequestParam("columnName") String columnName,
            @ApiParam(value = "是否可见0可见,1不可见",name = "display") @RequestParam("display") String display){
        Map<String,Object> result=new HashMap<>();
        BaseListColumn info=new BaseListColumn();
        info.setListCode(listCode);
        info.setColumnCode(columnCode);
        info.setColumnName(columnName);
       // info.setDisplay(Integer.parseInt(display));
        int i =userListColumnService.addListColumn(info);
        if(i>0){
            result.put("message","添加成功");
            result.put("code","0");
            return result;
        }
        result.put("message","无法添加");
        result.put("code","-1");
        return result;
    }
    /**
     * 列表删除列
     */
    @ApiOperation("删除列表列")
    @RequestMapping(value = "delTableColumn",method = RequestMethod.POST)
    public  Map<String,Object> delTableColumn(
            @ApiParam(value = "列表编码",name = "listCode") @RequestParam("listCode") String listCode,
            @ApiParam(value = "列编码",name = "columnCode") @RequestParam("columnCode") String columnCode){
        Map<String,Object> result=new HashMap<>();
        BaseListColumn info=new BaseListColumn();
        info.setListCode(listCode);
        info.setColumnCode(columnCode);

        int i =userListColumnService.delListColumn(info);
        if(i>0){
            result.put("message","删除成功");
            result.put("code","0");
            return result;
        }
        result.put("message","无法删除");
        result.put("code","-1");
        return result;
    }
    /**
     * 查询列表有多少列
     */
    @ApiOperation("查询列表列")
    @RequestMapping(value = "queryTableColumn",method = RequestMethod.POST)
    public  Map<String,Object> queryTableColumn(
            @ApiParam(value = "列表编码",name = "listCode") @RequestParam("listCode") String listCode){

        Map<String,Object> result=new HashMap<>();

        List<BaseListColumn> list =userListColumnService.queryListColumn(listCode);

        if(null!=list&&list.size()>0){
            result.put("message","查询成功");
            result.put("code","0");
            result.put("data",list);
            return result;
        }
        result.put("data",null);
        result.put("message","查询失败");
        result.put("code","-1");
        return result;
    }

    @ApiOperation("开启列表列")
    @RequestMapping(value = "openTableColumn",method = RequestMethod.POST)
    public  Map<String,Object> openTableColumn(
            @ApiParam(value = "列表编码",name = "listCode") @RequestParam("listCode") String listCode,
            @ApiParam(value = "列编码",name = "columnCode") @RequestParam("columnCode") String columnCode
    ){

        Map<String,Object> result=new HashMap<>();

        int i=baseTableColumnService.openTableColumn(listCode,columnCode,0);

        if(i>0){
            List<BaseListColumn> list= userListColumnService.queryListColumn(listCode);
            result.put("message","开启列表列成功");
            result.put("code","0");
            result.put("data",list);
            return result;
        }
        result.put("data",null);
        result.put("message","开启列表列失败");
        result.put("code","-1");
        return result;
    }

    @ApiOperation("禁用列表列")
    @RequestMapping(value = "closeTableColumn",method = RequestMethod.POST)
    public  Map<String,Object> closeTableColumn(
            @ApiParam(value = "列表编码",name = "listCode") @RequestParam("listCode") String listCode,
            @ApiParam(value = "列编码",name = "columnCode") @RequestParam("columnCode") String columnCode
    ){

        Map<String,Object> result=new HashMap<>();

        int i=baseTableColumnService.closeTableColumn(listCode,columnCode,1);

        if(i>0){
            List<BaseListColumn> list= userListColumnService.queryListColumn(listCode);
            result.put("message","禁用列表列成功");
            result.put("code","0");
            result.put("data",list);
            return result;
        }
        result.put("data",null);
        result.put("message","禁用列表列失败");
        result.put("code","-1");
        return result;
    }


}
