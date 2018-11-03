package ru.blinov.mygeekspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.blinov.mygeekspringboot.entities.Student;
import ru.blinov.mygeekspringboot.repositories.StudentsRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentsService {
    private StudentsRepository studentsRepository;

    @Autowired
    public void setStudentsRepository(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public StudentsService() {
    }

    @Transactional
    public Student getStudentById(Long id) {
        return studentsRepository.findStudentById(id);
    }

    @Transactional
    public List<Student> getAllStudentsList() {
//        return (List<Student>)studentsRepository.findAll();
        return studentsRepository.getStudentsByCoursesCountDescHQL();
    }

    @Transactional
    public void mergeStudent(Student s) {
        studentsRepository.save(s);
    }

    @Transactional
    public void removeById(Long id) {
        studentsRepository.deleteById(id);
    }

}
