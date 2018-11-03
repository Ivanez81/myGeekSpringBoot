package ru.blinov.mygeekspringboot.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class StudentCourseKey implements Serializable {
    static final long serialVersionUID = 1L;
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "course_id")
    private Long courseId;
}