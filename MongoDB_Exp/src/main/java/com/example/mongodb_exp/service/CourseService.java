package com.example.mongodb_exp.service;

import com.example.mongodb_exp.entity.Course;
import com.example.mongodb_exp.repository.CourseRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseService {
    @Resource
    private CourseRepository courseRepository;

    public void saveOne(@NotNull Course course){
        course.setCid(courseRepository.findMaxOne().get(0).getCid()+1);
        courseRepository.save(course);
    }

    public Course findByCid(Integer id){
        return courseRepository.findByCid(id);
    }

    public List<Course> findAll(){
        return courseRepository.findAll();
    }
    public List<Course> findByFcid(Integer fcid){
        return courseRepository.findCoursesByFcid(fcid);
    }

    public void update(@NotNull Course course){
        if (courseRepository.findByCid(course.getCid())!=null){
            courseRepository.save(course);
        }
    }

}
