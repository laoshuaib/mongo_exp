package com.example.mongodb_exp.service;

import com.example.mongodb_exp.repository.Teacher_CourseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Teacher_CourseService {
    @Resource
    Teacher_CourseRepository teacher_courseRepository;

    public Integer getTid(Integer cid){
        if (teacher_courseRepository.findFirstByCid(cid).size()==0){
            return 0;
        }
        return teacher_courseRepository.findFirstByCid(cid).get(0).getTid();
    }
}
