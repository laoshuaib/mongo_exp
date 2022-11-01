package com.example.mongodb_exp.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.mongodb_exp.common.Result;
import com.example.mongodb_exp.entity.Course;
import com.example.mongodb_exp.service.CourseService;
import com.example.mongodb_exp.untils.CheckUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    CourseService courseService;

    @GetMapping("/view")
    public Result<?> findAll() {
        List<Course> courseList = courseService.findAll();
        return Result.success(courseList);
    }
    @GetMapping("/find")
    public Result<?> findByFcid(@RequestParam String search) {
        List<Course> courseList = courseService.findAll();//默认值
        if (StrUtil.isNotBlank(search)&&CheckUtil.isNum(search)) {
                courseList = courseService.findByFcid(Integer.parseInt(search));
        }
        return Result.success(courseList);
    }
    @PostMapping("/save")
    public Result<?> save(@RequestBody @NotNull Course course){
        //验证先行课序号是否存在
        if (course.getFcid()!=0&&courseService.findByCid(course.getFcid())==null){
            return Result.error(-1,"先行课程不存在");
        }
        //保存（service层负责插入cid）
        courseService.saveOne(course);
        return Result.success();
    }
    @PostMapping("/excelUpload")
    public Result<?> upload(@NotNull MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        //表头校验
        List<List<Object>> readTest = reader.read(0, 0);
        List<Object> legalTitle = new ArrayList<>();
        legalTitle.add("课程名称");
        legalTitle.add("先行课编号");
        legalTitle.add("学分");
        if (!readTest.contains(legalTitle))
            return Result.error(-1, "请检查excel表头设置是否正确");
        //准备批量注入
        List<List<Object>> readAll = reader.read(1);
        for (List<Object> course : readAll) {
            int fcid = Integer.parseInt(course.get(1).toString());
            if (fcid!=0&&courseService.findByCid(fcid)==null){
                return Result.error(-1,"存在非法先行课编号");
            }
        }
        for (List<Object> course : readAll) {
            Course input = new Course();
            input.setName((String) course.get(0));
            input.setFcid(Integer.parseInt(course.get(1).toString()));
            input.setCredit(Double.parseDouble(course.get(2).toString()));
            courseService.saveOne(input);
        }
        return Result.success();
    }
}
