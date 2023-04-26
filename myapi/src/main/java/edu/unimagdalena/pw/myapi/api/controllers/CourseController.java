package edu.unimagdalena.pw.myapi.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.unimagdalena.pw.myapi.api.dto.CourseCreationDto;
import edu.unimagdalena.pw.myapi.api.dto.CourseDto;
import edu.unimagdalena.pw.myapi.api.dto.CourseMapper;
import edu.unimagdalena.pw.myapi.entities.Course;
import edu.unimagdalena.pw.myapi.services.CourseService;

@RestController
@RequestMapping("/api/v1")
public class CourseController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }
    //buscar
    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<Course> courses = courseService.findAll();
        List<CourseDto> courseDtos = courses.stream()
                                       .map(courseMapper::toDto)
                                       .collect(Collectors.toList());
    return ResponseEntity.ok(courseDtos);
    }
    //buscar id
    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        Optional<Course> optionalCourse = courseService.find(id);
        if (optionalCourse.isPresent()) {
            CourseDto courseDto = courseMapper.toDto(optionalCourse.get());
            return ResponseEntity.ok(courseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //crear
    @PostMapping("/courses")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseCreationDto courseCreationDto) {
        Course course = courseMapper.toCourseEntity(courseCreationDto);
        Course savedCourse = courseService.create(course);
        CourseDto courseDto = courseMapper.toDto(savedCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseDto);
    }
    //editar id
    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id, @RequestBody CourseCreationDto courseCreationDto) {
        Optional<Course> optionalCourse = courseService.update(id, courseMapper.toCourseEntity(courseCreationDto));
        if (optionalCourse.isPresent()) {
            CourseDto courseDto = courseMapper.toDto(optionalCourse.get());
            return ResponseEntity.ok(courseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //eliminar id
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
