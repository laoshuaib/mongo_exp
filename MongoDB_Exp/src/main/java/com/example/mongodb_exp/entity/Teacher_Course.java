package com.example.mongodb_exp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("teacher_course")
@NoArgsConstructor
@AllArgsConstructor
public class Teacher_Course {
    @Id
    private String _id;
    private Integer tid;
    private Integer cid;
}
