package com.example.mongodb_exp.service;

import com.example.mongodb_exp.entity.Student;
import com.example.mongodb_exp.entity.container.CountContainer;
import com.example.mongodb_exp.repository.StudentRepository;
import com.example.mongodb_exp.repository.Student_CourseRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentService {
    @Resource
    StudentRepository studentRepository;

    @Resource
    private Student_CourseRepository student_courseRepository;

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public void saveOne(@NotNull Student student){
        student.setSid(studentRepository.findMaxOne().get(0).getSid()+1);
        studentRepository.save(student);
    }

    public void update(@NotNull Student student){
        if (studentRepository.findBySid(student.getSid())!=null){
            studentRepository.save(student);
        }
    }

    public Student findBySid(Long sid){
        return studentRepository.findBySid(sid);
    }

    public List<CountContainer> findStudentCount(){
        int count=0;
        int lastNum=0;
        List<CountContainer> res = new LinkedList<>();
        List<CountContainer> out = student_courseRepository.getSidCount();
        for (CountContainer temp : out) {
            if (count>=10&&lastNum!= temp.getCount()){
                break;
            }
            temp.setName(studentRepository.findBySid(temp.get_id()).getName());
            count++;
            lastNum= temp.getCount();
            res.add(temp);
        }
        return res;
    }
}
