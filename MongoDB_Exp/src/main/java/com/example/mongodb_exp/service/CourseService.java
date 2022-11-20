package com.example.mongodb_exp.service;

import com.example.mongodb_exp.entity.Course;
import com.example.mongodb_exp.entity.container.CountContainer;
import com.example.mongodb_exp.repository.CourseRepository;
import com.example.mongodb_exp.repository.Student_CourseRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class CourseService {
    @Resource
    private CourseRepository courseRepository;

    @Resource
    private Student_CourseRepository student_courseRepository;

    public void saveOne(@NotNull Course course) {
        course.setCid(courseRepository.findMaxOne().get(0).getCid() + 1);
        courseRepository.save(course);
    }

    public Course findByCid(Integer id) {
        return courseRepository.findByCid(id);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public List<Course> findByFcid(Integer fcid) {
        return courseRepository.findCoursesByFcid(fcid);
    }

    public void update(@NotNull Course course) {
        //多套一层，保证安全
        if (courseRepository.findByCid(course.getCid()) != null) {
            courseRepository.save(course);
        }
    }

    public List<Course> findChosen() {
        List<Integer> cidList = student_courseRepository.getCidList();
        List<Course> res = new LinkedList<>();
        for (Integer integer : cidList) {
            res.add(courseRepository.findByCid(integer));
        }
        return res;
    }


    public List<CountContainer> findCourseCount(){
        List<CountContainer> res = new LinkedList<>();
        List<CountContainer> out = student_courseRepository.getCidCount();
        for (CountContainer temp : out) {
            temp.setName(courseRepository.findByCid(Math.toIntExact(temp.get_id())).getName());
            res.add(temp);
        }
        return res;
    }


}
