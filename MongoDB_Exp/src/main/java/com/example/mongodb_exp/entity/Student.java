package com.example.mongodb_exp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long sid;
    private String name;
    private String sex;
    private Integer age;
    private String birthday;
    private String dname;
    @Field("class")
    private Integer grade;
}
