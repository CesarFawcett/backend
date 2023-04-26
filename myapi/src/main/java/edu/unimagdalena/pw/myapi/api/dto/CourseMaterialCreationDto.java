package edu.unimagdalena.pw.myapi.api.dto;

import edu.unimagdalena.pw.myapi.entities.Course;
import lombok.Data;

@Data
public class CourseMaterialCreationDto {
    private Long id;
    private String url;
    private Course course;
}
