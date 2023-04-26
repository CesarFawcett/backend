package edu.unimagdalena.pw.myapi.services;

import java.util.List;
import java.util.Optional;
import edu.unimagdalena.pw.myapi.entities.Course;

public interface CourseService {
    Course create(Course course);
    Optional<Course> update(Long id, Course newCourse);
    List<Course> findAll();
    Optional<Course> find(Long id);
    void delete(Long id);
}
