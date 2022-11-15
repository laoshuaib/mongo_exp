package com.example.mongodb_exp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("student_course")
@NoArgsConstructor
@AllArgsConstructor
public class Student_Course {
    @Id
    private String _id;
    private Long sid;
    private Integer cid;
    private Integer tid;
    private Double score;//刚选的课默认为-1分，且只有分数为-1时可以更新该课程
}
