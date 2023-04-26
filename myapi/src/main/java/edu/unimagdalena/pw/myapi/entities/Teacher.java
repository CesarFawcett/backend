package edu.unimagdalena.pw.myapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Entity
@Table(name = "teachers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column
    private String lastName;
    @Column(unique = true)
    private String codigo;
    @JsonIgnore
    @OneToMany(mappedBy="profe")
    private Set<Course> courses;
    public Teacher updateWith(Teacher teacher){
        return new Teacher(this.id,
                            teacher.firstName,
                            teacher.lastName,
                            teacher.codigo, 
                            teacher.courses);
    }   
}
