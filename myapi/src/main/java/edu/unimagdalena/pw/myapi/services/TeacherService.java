package edu.unimagdalena.pw.myapi.services;

import java.util.Optional;
import edu.unimagdalena.pw.myapi.entities.Teacher;
import java.util.List;

public interface TeacherService {
    Teacher create(Teacher teacher);
    Optional<Teacher> update(Long id, Teacher newTeacher);
    List<Teacher> findAll();
    Optional<Teacher> find(Long id);
    void delete(Long id); 
}
