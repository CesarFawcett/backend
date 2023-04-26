package edu.unimagdalena.pw.myapi.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.unimagdalena.pw.myapi.entities.Teacher;
import edu.unimagdalena.pw.myapi.repositories.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService{  
    private final TeacherRepository teacherRepository;    
    
    @Autowired 
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    @Override
    public Teacher create(Teacher teacher) {
        Teacher copy = new Teacher(null, 
                                 teacher.getFirstName(), 
                                 teacher.getLastName(), 
                                 teacher.getCodigo(), 
                                 null);
        return teacherRepository.save(copy);
    }
    @Override
    public Optional<Teacher> update(Long id, Teacher newTeacher) {
        return teacherRepository.findById(id)
                .map(oldTeacher -> {
                    Teacher teacher = oldTeacher.updateWith(newTeacher);
                    return teacherRepository.save(teacher);
                });
    }
    @Override
    public List<Teacher> findAll() {
       return teacherRepository.findAll();
    }
    @Override
    public Optional<Teacher> find(Long id) {
       return teacherRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }  
}
