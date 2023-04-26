package edu.unimagdalena.pw.myapi.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="students")
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;
    @Column (nullable = false)
    private String lastName;
    @Column (nullable = false)
    private String firstName;
    @Column(unique = true)
    private String codigo;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;
    public Student updateWith(Student student){
        return new Student(this.id,
                        student.lastName,
                        student.firstName,
                        student.codigo,
                        student.gender,
                        student.courses);
    }
}
