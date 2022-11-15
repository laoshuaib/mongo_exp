package com.example.mongodb_exp.service;

import com.example.mongodb_exp.entity.Teacher;
import com.example.mongodb_exp.repository.TeacherRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherService {
    @Resource
    TeacherRepository teacherRepository;

    public List<Teacher> findAll(){
        return  teacherRepository.findAll();
    }

    public void saveOne(@NotNull Teacher teacher){
        teacher.setTid(teacherRepository.findMaxOne().get(0).getTid()+1);
        teacherRepository.save(teacher);
    }
    public void update(@NotNull Teacher teacher){
        if(teacherRepository.findByTid(teacher.getTid())!=null){
            teacherRepository.save(teacher);
        }
    }

    public Teacher findByTid(Integer tid){
        return teacherRepository.findByTid(tid);
    }
}
