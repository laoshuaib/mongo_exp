package com.example.mongodb_exp;

import com.example.mongodb_exp.entity.Course;
import com.example.mongodb_exp.repository.CourseRepository;
import com.example.mongodb_exp.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MongoDbExpApplicationTests {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;
    @Test
    void contextLoads() {

        //插入
//        Course course = new Course();
//        course.setCid(300007);
//        course.setCredit(4.0);
//        course.setFcid(300001);
//        course.setName("OS");
//        courseService.update(course);
//        System.out.println(res);

//        //查询
        System.out.println(courseRepository.findMaxOne());
        Course course=courseRepository.findMaxOne().get(0);
        course.setCredit(0.0);
        courseService.update(course);
        System.out.println(courseRepository.findMaxOne());
//        courseRepository.cid();
    }
}
