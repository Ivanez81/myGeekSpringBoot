package ru.blinov.mygeekspringboot.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.blinov.mygeekspringboot.entities.Course;

import java.util.List;

@Repository
public interface CoursesRepository extends CrudRepository<Course, Long> {
    Course findCourseById(Long id);

    @Query("SELECT DISTINCT c FROM Course c WHERE c NOT IN (:studentCourses)")
    List<Course> getAvailableCoursesByStudent(@Param("studentCourses") List<Course> studentCourses);

//    @Query("SELECT c FROM Course c LEFT JOIN c.students cs WHERE cs IS NULL OR cs<>:student")
//    List<Course> getAvailableCoursesByStudentId(@Param("student") Student student);
}
