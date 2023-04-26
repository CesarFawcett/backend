package edu.unimagdalena.pw.myapi.api.dto;

import org.springframework.stereotype.Component;
import edu.unimagdalena.pw.myapi.entities.Student;

@Component
public class StudentMapper {

    public StudentDto toDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setLastName(student.getLastName());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setCodigo(student.getCodigo());
        studentDto.setGender(student.getGender());

        return studentDto;
    }
    public Student toEntity(StudentDto studentDto){
        Student student = new Student();
        student.setLastName(studentDto.getLastName());
        student.setFirstName(studentDto.getFirstName());
        student.setCodigo(studentDto.getCodigo());
        studentDto.setGender(student.getGender());
        
        return student;
    }
    public StudentCreationDto toStudentCreationDto(Student student) {
        StudentCreationDto studentCreationDto = new StudentCreationDto();
        studentCreationDto.setId(student.getId());
        studentCreationDto.setLastName(student.getLastName());
        studentCreationDto.setFirstName(student.getFirstName());
        studentCreationDto.setCodigo(student.getCodigo());
        studentCreationDto.setGender(student.getGender());

        return studentCreationDto;
    }
    public Student toStudentEntity(StudentCreationDto studentCreationDto){
        Student student = new Student();
        student.setId(studentCreationDto.getId());
        student.setLastName(studentCreationDto.getLastName());
        student.setFirstName(studentCreationDto.getFirstName());
        student.setCodigo(studentCreationDto.getCodigo());
        student.setGender(studentCreationDto.getGender());
        
        return student;
    }
}
