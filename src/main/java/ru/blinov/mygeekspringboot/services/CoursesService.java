package ru.blinov.mygeekspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.blinov.mygeekspringboot.entities.Course;
import ru.blinov.mygeekspringboot.repositories.CoursesRepository;
import ru.blinov.mygeekspringboot.utils.CourseNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CoursesService {

    private CoursesRepository coursesRepository;

    @Autowired
    public void setCoursesRepository(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    public CoursesService() {
    }

    public Course getCourseById(Long id) {
        Optional<Course> course = coursesRepository.findById(id);
        if (!course.isPresent()) {
            throw new CourseNotFoundException("Course with id = " + id + " not found");
        }
        return course.get();
    }

    public Course saveOrUpdate(Course course) {
        return coursesRepository.save(course);
    }

    public void delete(Long id) {
        Optional<Course> course = coursesRepository.findById(id);
        if (!course.isPresent()) {
            throw new CourseNotFoundException("Course with id = " + id + " not found");
        }
        coursesRepository.delete(course.get());
    }

    public List<Course> getAllCourses() {
        return (List<Course>)coursesRepository.findAll();
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
