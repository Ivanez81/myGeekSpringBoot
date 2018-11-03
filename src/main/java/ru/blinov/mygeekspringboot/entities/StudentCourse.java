package ru.blinov.mygeekspringboot.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "student_course")
@Data
public class StudentCourse {

    @EmbeddedId
    private StudentCourseKey studentCourseKey;

    @Column(name = "grade")
    private int grade;

    @Column(name = "lessons")
    private int lessons;

}
