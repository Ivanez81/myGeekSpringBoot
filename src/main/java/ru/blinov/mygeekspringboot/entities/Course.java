package ru.blinov.mygeekspringboot.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "duration")
    @Getter
    @Setter
    private int duration;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="student_course",
            joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="student_id")
    )
    @Cascade({org.hibernate.annotations.CascadeType.DELETE, org.hibernate.annotations.CascadeType.DETACH})
    @Getter
    @Setter
    private List<Student> students;

    @Transient
    @Setter
    @Getter
    private List<Integer> grades;

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
//                ", students=" + students.size() +
                '}';
    }
}
