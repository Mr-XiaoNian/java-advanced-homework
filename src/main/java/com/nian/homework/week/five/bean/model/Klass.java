package com.nian.homework.week.five.bean.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Data
@Resource(name = "classOne")
public class Klass {

    List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
