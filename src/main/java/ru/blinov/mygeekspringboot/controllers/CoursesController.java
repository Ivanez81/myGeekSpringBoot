package ru.blinov.mygeekspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.blinov.mygeekspringboot.entities.Course;
import ru.blinov.mygeekspringboot.services.CoursesService;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/courses")
@Transactional
public class CoursesController {

    private CoursesService coursesService;

    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    public CoursesController() {
    }

    // http://localhost:8189/courses/showCourseById?id=2
    @RequestMapping(path="/showCourseById", method= RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String showCourseById(Model model, @RequestParam Long id) {
        Course course = coursesService.getCourseById(id);
        model.addAttribute("course", course);
        return "course-by-id";
    }
}
