package com.example.mongodb_exp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("teacher")
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    private String _id;
    private Integer tid;
    private String name;
    private String sex;
    private Integer age;
    private String dname;
}
