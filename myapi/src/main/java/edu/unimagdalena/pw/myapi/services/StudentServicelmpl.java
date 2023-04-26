package edu.unimagdalena.pw.myapi.services;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.unimagdalena.pw.myapi.entities.Student;
import edu.unimagdalena.pw.myapi.repositories.StudentRepository;

@Service
public class StudentServicelmpl implements StudentService{
    public final StudentRepository studentRepository;    
    @Autowired 
    public StudentServicelmpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public Student create(Student student) {
        Student copy = new Student(null,
                                student.getLastName(),
                                student.getFirstName(),
                                student.getCodigo(),
                                student.getGender(),
                                null);
        return studentRepository.save(copy);
    }
    @Override
    public Optional<Student> update(Long id, Student newStudent) {
        return studentRepository.findById(id)
                .map(oldStudent -> {
                    Student student = oldStudent.updateWith(newStudent);
                    return studentRepository.save(student);
                });
    }
    @Override
    public List<Student> findAll() {
       return studentRepository.findAll();
    }
    @Override
    public Optional<Student> find(Long id) {
       return studentRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}

