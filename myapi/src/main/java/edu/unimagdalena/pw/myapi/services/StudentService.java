package edu.unimagdalena.pw.myapi.services;

import java.util.List;
import java.util.Optional;
import edu.unimagdalena.pw.myapi.entities.Student;

public interface StudentService {
    Student create(Student student);
    Optional<Student> update(Long id, Student newStudent);
    List<Student> findAll();
    Optional<Student> find(Long id);
    void delete(Long id);
}

