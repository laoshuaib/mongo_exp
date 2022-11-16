package com.example.mongodb_exp.service;

import com.example.mongodb_exp.entity.Student_Course;
import com.example.mongodb_exp.repository.Student_CourseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Student_CourseService {
    @Resource
    Student_CourseRepository student_courseRepository;

    public List<Student_Course> findBySid(Long sid){
     return student_courseRepository.findBySid(sid);
    }

    public void saveOne(Student_Course student_course){
        student_courseRepository.save(student_course);
    }

    public void deleteOne(Student_Course student_course){
        Student_Course del = student_courseRepository.findBySidAndCid(student_course.getSid(),student_course.getCid()).get(0);
        student_courseRepository.delete(del);
    }
}
