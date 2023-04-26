package edu.unimagdalena.pw.myapi.api.dto;

import java.util.Set;
import edu.unimagdalena.pw.myapi.entities.CourseMaterial;
import edu.unimagdalena.pw.myapi.entities.Student;
import edu.unimagdalena.pw.myapi.entities.Teacher;
import lombok.Data;

@Data
public class CourseCreationDto {
    private Long id;
    private String name;
    private Teacher teacher;
    private Set<Student> students;
    private CourseMaterial courseMaterial;
}
