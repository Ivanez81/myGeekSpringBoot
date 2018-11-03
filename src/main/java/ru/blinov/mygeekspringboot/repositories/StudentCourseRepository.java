package ru.blinov.mygeekspringboot.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.blinov.mygeekspringboot.entities.StudentCourse;
import ru.blinov.mygeekspringboot.entities.StudentCourseKey;

import java.util.List;

public interface StudentCourseRepository extends CrudRepository<StudentCourse, StudentCourseKey> {

    @Query("SELECT sc.studentCourseKey.studentId FROM StudentCourse sc GROUP BY sc.studentCourseKey.studentId" +
            " ORDER BY COUNT (sc.studentCourseKey.courseId) DESC ")
    List<Long> findAllStudents();

//    @Query(value = "SELECT s FROM student s " +
//            "LEFT JOIN student_course c on s.id = c.student_id " +
//            "GROUP BY s.id " +
//            "ORDER BY count(distinct c.course_id) DESC;", nativeQuery = true)
//    List<Student> getStudentsIdsByCoursesCountDesc();
}
