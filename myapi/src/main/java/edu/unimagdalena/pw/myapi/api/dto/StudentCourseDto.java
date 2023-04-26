package edu.unimagdalena.pw.myapi.api.dto;

import java.util.List;

import edu.unimagdalena.pw.myapi.entities.Gender;
import lombok.Data;

@Data
public class StudentCourseDto {
    private Long id;
    private String  lastName;
    private String firstName;
    private String codigo;
    private Gender gender;
    List<CourseDto> courses;
}
