package edu.unimagdalena.pw.myapi.api.dto;

import java.util.Set;
import org.springframework.stereotype.Component;
import edu.unimagdalena.pw.myapi.entities.Course;
import edu.unimagdalena.pw.myapi.entities.Student;

@Component
public class CourseMapper {

    public CourseDto toDto(Course course){
        CourseDto courseDto = new CourseDto();
        courseDto.setName(course.getName());
        courseDto.setTeacher(course.getProfe());
        courseDto.setStudents(course.getStudents());
        courseDto.setCourseMaterial(course.getCourseMaterial());
        
        return courseDto;
    }
    public Course toEntity(CourseDto courseDto){
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setProfe(courseDto.getTeacher());
        
        Set<Student> students = courseDto.getStudents();
        if (students != null) {
            for (Student student : students) {
                course.addStudent(student);
            }
        }
        course.setCourseMaterial(courseDto.getCourseMaterial());
        return course;
    }
    public CourseCreationDto toCourseCreationDto(Course course) {
        CourseCreationDto courseCreationDto = new CourseCreationDto();
        courseCreationDto.setId(course.getId());
        courseCreationDto.setName(course.getName());
        courseCreationDto.setTeacher(course.getProfe());
        courseCreationDto.setStudents(course.getStudents());
        courseCreationDto.setCourseMaterial(course.getCourseMaterial());

        return courseCreationDto;
    }
    public Course toCourseEntity(CourseCreationDto courseCreationDto){
        Course course = new Course();
        course.setId(courseCreationDto.getId());
        course.setName(courseCreationDto.getName());
        course.setProfe(courseCreationDto.getTeacher());
        course.setStudents(courseCreationDto.getStudents());
        course.setCourseMaterial(courseCreationDto.getCourseMaterial());

        return course;
    }
}

