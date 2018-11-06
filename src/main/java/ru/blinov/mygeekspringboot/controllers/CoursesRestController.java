package ru.blinov.mygeekspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.blinov.mygeekspringboot.entities.Course;
import ru.blinov.mygeekspringboot.services.CoursesService;
import ru.blinov.mygeekspringboot.utils.CourseNotFoundException;
import ru.blinov.mygeekspringboot.utils.CoursesErrorResponse;

import java.time.LocalTime;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CoursesRestController {

    private CoursesService coursesService;

    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    public CoursesRestController() {
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourseById(@PathVariable Long courseId) {
        return coursesService.getCourseById(courseId);
    }

    @GetMapping("/courses")
    public List<Course> getAllCoursesList() {
        return coursesService.getAllCourses();
    }

    @PostMapping(
            value = "/courses",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Course addStudent(@RequestBody Course theCourse) {
        theCourse.setId(0L);
        theCourse = coursesService.saveOrUpdate(theCourse);
        return theCourse;
    }

    @PutMapping("/courses")
    public Course updateStudent(@RequestBody Course theCourse) {
        theCourse = coursesService.saveOrUpdate(theCourse);
        return theCourse;
    }

    @DeleteMapping("/courses/{courseId}")
    public int deleteStudent(@PathVariable Long courseId) {
        coursesService.delete(courseId);
        return HttpStatus.OK.value();
    }

    @ExceptionHandler
    public ResponseEntity<CoursesErrorResponse> handleException(CourseNotFoundException exc) {
        CoursesErrorResponse coursesErrorResponse = new CoursesErrorResponse();
        coursesErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        coursesErrorResponse.setMessage(exc.getMessage());
        coursesErrorResponse.setTimestamp(LocalTime.now());
        return new ResponseEntity<>(coursesErrorResponse, HttpStatus.NOT_FOUND);
    }

}
