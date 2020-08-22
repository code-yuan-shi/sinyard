package com.sinyard.demo.controller;

import com.sinyard.demo.document.EsStudent;
import com.sinyard.demo.service.EsStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sinyard
 * @date 2020-08-22 14:18
 * @desc
 */
@Controller
@Api(tags = "EsStudentController", description = "搜索学生管理")
@RequestMapping("/esStudent")
public class EsStudentController {
    @Autowired
    private EsStudentService esStudentService;

    @ApiOperation(value = "导入所有数据库中学生到ES")
    @RequestMapping(value = "/importAll",method = RequestMethod.POST)
    @ResponseBody
    public Integer importAllList() {
        int count = esStudentService.importAll();
        return count;
    }

    @ApiOperation(value = "根据学号删除学生")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Integer id) {
        return  esStudentService.delete(id);
    }
    @ApiOperation(value = "根据学号批量删除商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("ids") List<Integer> ids) {
        esStudentService.delete(ids);
        return "批量删除成功";
    }


    @ApiOperation(value = "根据id创建商品")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String create(@PathVariable Integer id) {
        EsStudent esStudent = esStudentService.create(id);
        if (esStudent != null) {
            return "创建成功";
        } else {
            return "创建失败";
        }
    }

    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    @ResponseBody
    public Page<EsStudent> search(@RequestParam(required = false) String keyword,
                                  @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsStudent> esStudentPage = esStudentService.search(keyword, pageNum, pageSize);
        return esStudentPage;
    }

}
