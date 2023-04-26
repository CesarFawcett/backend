package edu.unimagdalena.pw.myapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "coursematerials")
@NoArgsConstructor
@AllArgsConstructor
public class CourseMaterial {
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coursematerials_seq")
    @SequenceGenerator(name = "coursematerials_seq", sequenceName = "coursematerials_seq", allocationSize = 1)
    private Long id;
    private String url;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
    public CourseMaterial updateWith(CourseMaterial courseMaterial){
        return new CourseMaterial(this.id,
                         courseMaterial.url,
                         courseMaterial.course);                 
    } 
}
