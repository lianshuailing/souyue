package com.souyue.base.controller;

import com.souyue.base.entity.Label;
import com.souyue.base.service.LabelService;
import com.souyue.common.pojo.PageResult;
import com.souyue.common.pojo.Result;
import com.souyue.common.pojo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author shkstart
 * @create 2019-09-21 10:47
 */
@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;
    @Autowired
    private HttpServletRequest request;

    // 用于测试 @RefreshScope
    @Value("${sy.id}")
    private String ip;

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){

        labelService.save(label);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
//        int i = 1/0;

//        用于测试 @RefreshScope
//        System.out.println("ip为："+ip);


        // 获取头信息-----测试！！！！
        String header = request.getHeader("Authorization");
        System.out.println(header);

        List<Label> list = labelService.findAll();
        return new Result(true, StatusCode.OK,  "查询成功", list);
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String id){
//        System.out.println("222222222222222");

        Label label = labelService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", label);
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result update(@PathVariable String labelId, @RequestBody Label label){

        label.setId(labelId);
        labelService.update(label);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String labelId){

        labelService.delete(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件查询(Label单表)
     * @param label
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label){

        List<Label> list = labelService.findSearch(label);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }


    /**
     * 备注：int Integer都行，公司里不要求的话，推荐int默认0不会出现空指针；当然Integer也行,在代码里判断 没传的话，给默认值。
     * @param page
     * @param size
     * @param label
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result pageQuery(@PathVariable int page, @PathVariable int size, @RequestBody Label label){

        Page<Label> pageData = labelService.pageQuery(label, page, size);   // 总数                                          List<Label>
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(pageData.getTotalElements(), pageData.getContent()));
    }
}
