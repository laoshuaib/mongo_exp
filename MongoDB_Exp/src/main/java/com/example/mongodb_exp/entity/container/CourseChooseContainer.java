package com.example.mongodb_exp.entity.container;

import lombok.Data;

@Data
public class CourseChooseContainer {
    private Long sid;
    private Integer cid;
    private Integer fcid;
    private String cname;
    private Integer tid;
    private String tname;
    private Double credit;
//    private Double score;//刚选的课默认为-1分，且只有分数为-1时可以更新该课程
}
