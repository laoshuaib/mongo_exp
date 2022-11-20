package com.example.mongodb_exp.repository;
import com.example.mongodb_exp.entity.Student_Course;
import com.example.mongodb_exp.entity.container.CountContainer;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Student_CourseRepository extends MongoRepository<Student_Course,String>{
    List<Student_Course> findBySid(Long sid);
    List<Student_Course> findBySidAndCid(Long sid,Integer cid);

    @Aggregation(pipeline = {
            "{'$project':{'cid':true,'sid':true}}",
            "{'$group':{'_id':'$cid'}}",
    })
    List<Integer> getCidList();


    @Aggregation(pipeline = {
            "{'$group':{'_id':'$cid',count: {'$sum': 1}}}",
            "{'$sort':{'count':-1}}",
            "{'$limit':10}"
    })
    List<CountContainer>getCidCount();

    @Aggregation(pipeline = {
            "{'$group':{'_id':'$sid',count: {'$sum': 1}}}",
            "{'$sort':{'count':-1}}",
    })
    List<CountContainer>getSidCount();
}
