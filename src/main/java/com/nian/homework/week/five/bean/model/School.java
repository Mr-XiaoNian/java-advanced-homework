package com.nian.homework.week.five.bean.model;

import lombok.Data;

import javax.annotation.Resource;

//在SpringConfigInitial里项目启动后调用了自动配置后School的ding()方法
@Data
public class School implements ISchool {

    @Resource(name = "classOne")
    public Klass classOne;
    
    @Resource(name = "studentFour")
    public Student studentFour;

    @Override
    public void ding(){
        System.out.println("ClassOne have " + this.classOne.getStudents().size() + " students and anther one is " + this.studentFour);
    }


    public Klass getClassOne() {
        return classOne;
    }

    public void setClassOne(Klass classOne) {
        this.classOne = classOne;
    }

    public Student getStudentFour() {
        return studentFour;
    }

    public void setStudentFour(Student studentFour) {
        this.studentFour = studentFour;
    }
}
