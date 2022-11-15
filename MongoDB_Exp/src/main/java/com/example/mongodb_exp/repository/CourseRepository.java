package com.example.mongodb_exp.repository;

import com.example.mongodb_exp.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends MongoRepository<Course,String> {
    Course findByCid(Integer cid);
    List<Course> findCoursesByFcid(Integer fcid);
    @Aggregation(pipeline = {
            "{'$sort':{'cid':-1}}",
            "{'$limit':1}"
    })
    List<Course> findMaxOne();

}
