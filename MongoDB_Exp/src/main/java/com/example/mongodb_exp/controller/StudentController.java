package com.example.mongodb_exp.controller;

import com.example.mongodb_exp.common.Result;
import com.example.mongodb_exp.entity.Student;
import com.example.mongodb_exp.service.StudentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
}
