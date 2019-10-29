package com.souyue.recruit.controller;

import com.souyue.common.pojo.PageResult;
import com.souyue.common.pojo.Result;
import com.souyue.common.pojo.StatusCode;
import com.souyue.recruit.entity.Recruit;
import com.souyue.recruit.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2019-09-23 19:15
 */
@RestController
@RequestMapping("/recruit")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;


    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Recruit> list = recruitService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Recruit recruit) {
        recruitService.save(recruit);
        return new Result(true, StatusCode.OK, "新增成功");
    }

    @GetMapping("{recruitId}")
    public Result findById(@PathVariable String recruitId) {
        return new Result(true, StatusCode.OK, "查询成功", recruitService.findById(recruitId));
    }


    @PutMapping("{recruitId}")
    public Result updateById(@PathVariable String recruitId, @RequestBody Recruit recruit) {
        recruit.setId(recruitId);
        return new Result(true, StatusCode.OK, "修改成功", recruitService.update(recruit));
    }

    @DeleteMapping("{recruitId}")
    public Result deleteById(@PathVariable String recruitId) {
        recruitService.delete(recruitId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 单表条件查询---Specification
     * @param searchMap
     * @return
     */
    @PostMapping("search")
    public Result search(@RequestBody Map searchMap) {
        List<Recruit> list = recruitService.search(searchMap);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 单表条件查询 带分页  ps:@PathVariable不能设默认值，因为path里不能传null，无意义。@RequestParam可以设默认值
     * @param searchMap
     * @return
     */
    @PostMapping("search/{page}/{size}")
    public Result search(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        PageResult<Recruit> recruits = recruitService.searchByPage(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", recruits);
    }

    /**
     *  单表条件查询---根据命名规范查询
     * @return
     */
    @GetMapping("search/recommend")
    public Result recommend() {
        List<Recruit> list = recruitService.findTop6ByStateOrderByCreatetime();
        return new Result(true, StatusCode.OK, "查询成功", list);
    }
}
