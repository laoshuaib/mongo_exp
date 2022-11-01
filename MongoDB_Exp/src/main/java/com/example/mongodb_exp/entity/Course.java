package com.example.mongodb_exp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("course")
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Integer cid;
    private String name;
    private Integer fcid;
    private Double credit;
}
