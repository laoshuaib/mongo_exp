package com.example.mongodb_exp.repository;

import com.example.mongodb_exp.entity.Student;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {
    @Aggregation(pipeline = {
            "{'$sort':{'sid':-1}}",
            "{'$limit':1}"
    })
    List<Student> findMaxOne();
    Student findBySid(Long sid);
}
