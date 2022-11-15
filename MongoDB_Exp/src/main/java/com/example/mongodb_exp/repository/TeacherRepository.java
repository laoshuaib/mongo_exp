package com.example.mongodb_exp.repository;

import com.example.mongodb_exp.entity.Teacher;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeacherRepository extends MongoRepository<Teacher,String> {
    @Aggregation(pipeline = {
            "{'$sort':{'tid':-1}}",
            "{'$limit':1}"
    })
    List<Teacher> findMaxOne();
    Teacher findByTid(Integer tid);
}
