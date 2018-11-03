package ru.blinov.mygeekspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.blinov.mygeekspringboot.entities.Student;
import ru.blinov.mygeekspringboot.services.CoursesService;
import ru.blinov.mygeekspringboot.services.StudentsService;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/students")
@Transactional
public class StudentsController {
    private StudentsService studentsService;
    private CoursesService coursesService;

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    public StudentsController() {
    }

    // http://localhost:8189/students/list
    @RequestMapping("/list")
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public String showStudentsList(Model model) {
        model.addAttribute("studentsList", studentsService.getAllStudentsList());
        return "students-list";
    }

    // http://localhost:8189/students/2/edu
    @RequestMapping(path = "/{id}/edu", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public String getCoursesByStudentIdHQL(Model model, @PathVariable("id") Long id) {
        if (studentsService.getStudentById(id).getCourses().isEmpty()) {
            model.addAttribute("coursesByStudentId", studentsService.getStudentById(id).getCourses());
            model.addAttribute("coursesList", coursesService.getAllCourses());
        } else {
            model.addAttribute("coursesByStudentId", studentsService.getStudentById(id).getCourses());
            model.addAttribute("coursesList",
                    coursesService.getAvailableCoursesForStudent(studentsService.getStudentById(id).getCourses())
            );
        }
        return "courses-by-student-id";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student-form";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddForm(Student student) {
        studentsService.mergeStudent(student);
        return "redirect:/students/list";
    }

    @RequestMapping(path = "/{studentId}/edu/add/{courseId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String addStudentCourse(@PathVariable("studentId") Long studentId,
                                   @PathVariable("courseId") Long courseId) {
        studentsService.getStudentById(studentId).getCourses().add(coursesService.getCourseById(courseId));
        studentsService.mergeStudent(studentsService.getStudentById(studentId));
        return "redirect:/students/" + studentId + "/edu";
    }

    @RequestMapping(path = "/{studentId}/edu/remove/{courseId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String removeStudentCourse(@PathVariable("studentId") Long studentId,
                                   @PathVariable("courseId") Long courseId) {
        studentsService.getStudentById(studentId).getCourses().remove(coursesService.getCourseById(courseId));
        studentsService.mergeStudent(studentsService.getStudentById(studentId));
        return "redirect:/students/" + studentId + "/edu";
    }

//    // http://localhost:8189/students/stream/2/edu
//    @RequestMapping(path = "stream/{id}/edu", method = RequestMethod.GET)
//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
//    public String getCoursesByStudentIdStream(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("coursesByStudentId", studentsService.getStudentById(id).getCourses());
//        model.addAttribute("coursesList",
//                coursesService.getAllCourses().stream().filter(course ->
//                        studentsService.getStudentById(id).getCourses().stream().noneMatch(studentCourse ->
//                                studentCourse.getId().equals(course.getId())))
//                .collect(Collectors.toList())
//        );
//        return "courses-by-student-id";
//    }

//    @RequestMapping(path="/{id}/edu", method= RequestMethod.GET)
//    public String getStudentByIdFromPath(Model model, @PathVariable("id") Long id) {
//        Student studentById = studentsService.getStudentById(id);
//        model.addAttribute("studentById", studentById);
//        return "student-by-id";
//    }

//    // http://localhost:8189/students/showStudentById?id=2
//    @RequestMapping(path = "/showStudentById", method = RequestMethod.GET)
//    public String showStudentById(Model model, @RequestParam Long id) {
//        Student student = studentsService.getStudentById(id);
//        model.addAttribute("student", student);
//        return "student-by-id";
//    }

//    // http://localhost:8189/students/showStudents
//    @RequestMapping("/showStudents")
//    public String showStudents(Model model) {
//        List<Long> studentIdList = studentCourseService.getAllStudents();
//        List<Student> studentList = new ArrayList<>();
//        for (int i = 0; i < studentIdList.size(); i++) {
//            studentList.add(studentsService.getStudentById(studentIdList.get(i)));
//        }
//        model.addAttribute("studentList", studentList);
//        return "students-list";
//    }

//    @RequestMapping("/showForm")
//    public String showSimpleForm(Model model) {
//        Student student = new Student();
//        model.addAttribute("student", student);
//        return "student-form";
//    }
//
//    @RequestMapping("/processForm")
//    public String processForm(@ModelAttribute("student") Student student) {
//        System.out.println(student.getFirstName() + " " + student.getLastName());
//        return "student-form-result";
//    }
//
//    @RequestMapping(path="/getStudentById", method= RequestMethod.GET)
//    @ResponseBody
//    public Student getStudentById(@RequestParam int id) {
//        Student student = studentsService.getStudentById(new Long(id));
//        return student;
//    }
//
//    @RequestMapping(path="/getStudentById/{sid}", method= RequestMethod.GET)
//    @ResponseBody
//    public Student getStudentByIdFromPath(@PathVariable("sid") int id) {
//        Student student = studentsService.getStudentById(new Long(id));
//        return student;
//    }
}
