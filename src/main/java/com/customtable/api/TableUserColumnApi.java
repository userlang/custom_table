package com.customtable.api;

import com.customtable.bo.BaseListColumn;
import com.customtable.bo.BaseUserListColumn;
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
@Api(tags="ListColumnApi")
public class TableUserColumnApi {

    @Resource
    private BaseTableUserColumnService userListColumnService;




    /**
     *  根据列表编号和用户id维度来查询当前用户拥有该列表的多少个列，和该列表的全量列
     * @param userId 用户维度
     * @param listCode 列表编码
     * @return userColumn=用户拥有的列  allColumn=该列表全量的列
     */
    @ApiOperation("查询列表所有列,当前用户持有多少列")
    @RequestMapping(value = "queryListColumnAndUserColumn",method = RequestMethod.POST)
    public Map<String,Object> queryListColumnAndUserColumn(
            @ApiParam(value = "用户id",name = "userId",defaultValue = "-1") @RequestParam("userId") Integer userId,
            @ApiParam(value = "列表编码",name = "listCode") @RequestParam("listCode") String listCode){
        //查询当前用户有该列表的多少个列
        List<BaseUserListColumn>  userColumn=userListColumnService.queryListColumnByCondition(listCode,userId);
        //查询该列表的全量列
        List<BaseListColumn> allColumn= userListColumnService.queryListColumn(listCode);
        Map<String,Object> result=new HashMap<>();
        result.put("userColumn",userColumn);
        result.put("allColumn",allColumn);
       return result;
    }


    /**
     * 用户自定义列表添加列
     */
    @ApiOperation("用户添加列表列，并且返回该列表所有列,当前用户持有多少列")
    @RequestMapping(value = "addUserListColumn",method = RequestMethod.POST)
    public  Map<String,Object> addUserListColumn(
            @ApiParam(value = "用户id",name = "userId") @RequestParam("userId") Integer userId,
            @ApiParam(value = "列表编码",name = "listCode") @RequestParam("listCode") String listCode,
            @ApiParam(value = "列编码",name = "columnCode") @RequestParam("columnCode") String columnCode){
        Map<String,Object> result=new HashMap<>();
        BaseUserListColumn info=new BaseUserListColumn();
        info.setColumnCode(columnCode);
        info.setListCode(listCode);
        info.setUserId(userId);
        int ll=userListColumnService.insertBaseUserListColumn(info);
        if(ll>0){
            return queryListColumnAndUserColumn(userId,listCode);
        }
        result.put("message","无法添加");
        result.put("code","-1");
        return result;
    }
    /**
     * 列表添加列
     */
    @ApiOperation("添加列表列，并且返回该列表所有列,当前用户持有多少列")
    @RequestMapping(value = "addListColumn",method = RequestMethod.POST)
    public  Map<String,Object> addListColumn(
            @ApiParam(value = "列表编码",name = "listCode") @RequestParam("listCode") String listCode,
            @ApiParam(value = "列编码",name = "columnCode") @RequestParam("columnCode") String columnCode,
            @ApiParam(value = "列显示名称",name = "columnName") @RequestParam("columnName") String columnName,
            @ApiParam(value = "是否可见0可见,1不可见",name = "display") @RequestParam("display") String display){
        Map<String,Object> result=new HashMap<>();
        BaseListColumn info=new BaseListColumn();
        info.setListCode(listCode);
        info.setColumnCode(columnCode);
        info.setColumnName(columnName);
        info.setDisplay(Integer.parseInt(display));
        int i =userListColumnService.addListColumn(info);
        if(i>0){
            return queryListColumnAndUserColumn(-1,listCode);
        }
        result.put("message","无法添加");
        result.put("code","-1");
        return result;
    }
    /**
     * 列表删除列
     */
    @ApiOperation("删除列表列，并且返回该列表所有列,当前用户持有多少列")
    @RequestMapping(value = "delListColumn",method = RequestMethod.POST)
    public  Map<String,Object> delListColumn(
            @ApiParam(value = "列表编码",name = "listCode") @RequestParam("listCode") String listCode,
            @ApiParam(value = "列编码",name = "columnCode") @RequestParam("columnCode") String columnCode){
        Map<String,Object> result=new HashMap<>();
        BaseListColumn info=new BaseListColumn();
        info.setListCode(listCode);
        info.setColumnCode(columnCode);

        int i =userListColumnService.delListColumn(info);
        if(i>0){
            return queryListColumnAndUserColumn(-1,listCode);
        }
        result.put("message","无法删除");
        result.put("code","-1");
        return result;
    }



    /**
     * 删除用户列表列
     */

    @ApiOperation("删除用户列表列")
    @RequestMapping(value = "delUserColumn",method = RequestMethod.POST)
    public  Map<String,Object> delUserColumn(
            @ApiParam(value = "用户id",name = "userId") @RequestParam("userId") Integer userId,
            @ApiParam(value = "列表编码",name = "listCode") @RequestParam("listCode") String listCode,
            @ApiParam(value = "列编码",name = "columnCode") @RequestParam("columnCode") String columnCode

    ){

       Map<String,Object> result=new HashMap<>();
        BaseUserListColumn info=new BaseUserListColumn();
        info.setColumnCode(columnCode);
        info.setListCode(listCode);
        info.setUserId(userId);

        int ll=userListColumnService.delUserColumn(info);
        if(ll>0){
            result.put("message","删除成功");
            result.put("code","0");
            return result;
        }
        result.put("message","无法删除");
        result.put("code","-1");
        return result;
    }





}
