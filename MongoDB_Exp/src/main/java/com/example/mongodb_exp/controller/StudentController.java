package com.example.mongodb_exp.controller;

import cn.hutool.json.JSONUtil;
import com.example.mongodb_exp.common.Result;
import com.example.mongodb_exp.entity.Student;
import com.example.mongodb_exp.service.StudentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    StudentService studentService;

    @GetMapping("/view")
    public Result<?> findAll() {
        List<Student> studentList = studentService.findAll();
        return Result.success(studentList);
    }

    @PostMapping("/save")
    public Result<?> save(@RequestBody @NotNull Student student){
        studentService.saveOne(student);
        return Result.success();
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody @NotNull Student student){
        studentService.update(student);
        return Result.success();
    }
    @SuppressWarnings("SuspiciousToArrayCall")
    @PostMapping("/updateAll")
    public Result<?> updateAll(@RequestBody @NotNull Map<String, Object> input){
        //JSON解析
        Student[] students = JSONUtil.parseArray(
                JSONUtil.toJsonStr(input.get("studentList"))).toArray(new Student[0]);
        for (Student student:students){
            if (studentService.findBySid(student.getSid())!=null){
                studentService.update(student);
            }else
                return Result.error(-1,"集合中存在不在数据库中的sid");
        }
        return Result.success();
    }
}
