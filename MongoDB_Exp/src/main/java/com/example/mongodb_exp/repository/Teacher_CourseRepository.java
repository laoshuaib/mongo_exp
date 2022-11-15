package com.example.mongodb_exp.repository;

import com.example.mongodb_exp.entity.Teacher_Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Teacher_CourseRepository extends MongoRepository<Teacher_Course,String> {
    List<Teacher_Course> findFirstByCid(Integer cid);
}
