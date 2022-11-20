package com.example.mongodb_exp;

import com.example.mongodb_exp.entity.Course;
import com.example.mongodb_exp.repository.CourseRepository;
import com.example.mongodb_exp.repository.Student_CourseRepository;
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

    @Autowired
    private Student_CourseRepository student_courseRepository;
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
//        courseRepository.cid();
        System.out.println(student_courseRepository.getCidCount());
    }
}
