package com.example.mongodb_exp.controller;

import com.example.mongodb_exp.common.Result;
import com.example.mongodb_exp.entity.Teacher;
import com.example.mongodb_exp.service.TeacherService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    TeacherService teacherService;

    @GetMapping("/view")
    public Result<?> findAll() {
        List<Teacher> teacherList = teacherService.findAll();
        return Result.success(teacherList);
    }

    @PostMapping("/save")
    public Result<?> save(@RequestBody @NotNull Teacher teacher){
        teacherService.saveOne(teacher);
        return Result.success();
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody @NotNull Teacher teacher){
        teacherService.update(teacher);
        return Result.success();
    }
}
