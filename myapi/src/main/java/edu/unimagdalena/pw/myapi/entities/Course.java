package edu.unimagdalena.pw.myapi.entities;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courses_seq")
    @SequenceGenerator(name = "courses_seq", sequenceName = "courses_seq", allocationSize = 1)
    private Long id;
    @Column()
    private String name;
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher profe;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name="COURSES_STUDENTS", 
    joinColumns=@JoinColumn(name="course_id", referencedColumnName="id" ),
    inverseJoinColumns=@JoinColumn(name="student_id", referencedColumnName="id"))
    private Set<Student> students = new HashSet();
    public void addStudent(Student student) {
        students.add(student);
        student.getCourses().add(this);
    }
    @JsonIgnore
    @OneToOne(mappedBy = "course")
    private CourseMaterial courseMaterial;
    public Course updateWith(Course course){
        return new Course(this.id,
                         course.name,
                         course.profe,
                         course.students,
                         null);               
    } 
}
