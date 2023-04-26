package edu.unimagdalena.pw.myapi.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.unimagdalena.pw.myapi.entities.Course;
import edu.unimagdalena.pw.myapi.repositories.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {  
    private final CourseRepository courseRepository;    
    
    @Autowired 
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }  
    @Override
    public Course create(Course course) {
        Course copy = new Course(null, 
                                 course.getName(), 
                                 course.getProfe(), 
                                 course.getStudents(), 
                                 null);
        return courseRepository.save(copy);
    }
    @Override
    public Optional<Course> update(Long id, Course newCourse) {
        return courseRepository.findById(id)
                .map(oldCourse -> {
                    Course course = oldCourse.updateWith(newCourse);
                    return courseRepository.save(course);
                });
    }
    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
    @Override
    public Optional<Course> find(Long id) {
        return courseRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }  
}
