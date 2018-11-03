package ru.blinov.mygeekspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.blinov.mygeekspringboot.entities.Course;
import ru.blinov.mygeekspringboot.repositories.CoursesRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CoursesService {

    private CoursesRepository coursesRepository;

    @Autowired
    public void setCoursesRepository(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    public CoursesService() {
    }

    @Transactional
    public Course getCourseById(Long id) {
        return coursesRepository.findCourseById(id);
    }

    @Transactional
    public List<Course> getAllCourses() {
        return (List)coursesRepository.findAll();
    }

    @Transactional
    public List<Course> getAvailableCoursesForStudent(List<Course> studentCourses) {
        return coursesRepository.getAvailableCoursesByStudent(studentCourses);
    }

//    @Transactional
//    public List<Course> getAvailableCoursesForStudentId(Student student) {
//        return coursesRepository.getAvailableCoursesByStudentId(student);
//    }
}
