package com.example.mongodb_exp.service;

import com.example.mongodb_exp.entity.Student;
import com.example.mongodb_exp.repository.StudentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {
    @Resource
    StudentRepository studentRepository;

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public void saveOne(@NotNull Student student){
        student.setSid(studentRepository.findMaxOne().get(0).getSid()+1);
        studentRepository.save(student);
    }
}
