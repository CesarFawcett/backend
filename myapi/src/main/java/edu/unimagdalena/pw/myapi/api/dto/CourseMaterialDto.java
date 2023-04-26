package edu.unimagdalena.pw.myapi.api.dto;

import edu.unimagdalena.pw.myapi.entities.Course;
import lombok.Data;

@Data
public class CourseMaterialDto {
    private Long id;
    private String url;
    private Course course;
}
