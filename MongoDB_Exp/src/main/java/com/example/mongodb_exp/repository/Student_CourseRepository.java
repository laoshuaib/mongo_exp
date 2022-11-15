package com.example.mongodb_exp.repository;
import com.example.mongodb_exp.entity.Student_Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Student_CourseRepository extends MongoRepository<Student_Course,String>{
    List<Student_Course> findBySid(Long sid);
}
