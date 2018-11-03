package ru.blinov.mygeekspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.blinov.mygeekspringboot.repositories.StudentCourseRepository;

import java.util.List;

@Repository
public class StudentCourseService {

    private StudentCourseRepository studentCourseRepository;

    @Autowired
    public void setStudentCourseRepository(StudentCourseRepository studentCourseRepository) {
        this.studentCourseRepository = studentCourseRepository;
    }

    public StudentCourseService() {
    }

    public List<Long> getAllStudents() {
        return studentCourseRepository.findAllStudents();
//        return (List)studentsRepository.findAll();
//        return studentsRepository.getStudentsIdsByCoursesCountDesc();
    }
}
