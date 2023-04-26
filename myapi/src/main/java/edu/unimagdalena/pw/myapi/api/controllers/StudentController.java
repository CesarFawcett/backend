package edu.unimagdalena.pw.myapi.api.controllers;

import java.util.stream.Collectors;
import org.hibernate.ObjectNotFoundException;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import edu.unimagdalena.pw.myapi.api.dto.StudentMapper;
import edu.unimagdalena.pw.myapi.entities.Student;
import edu.unimagdalena.pw.myapi.api.dto.StudentCreationDto;
import edu.unimagdalena.pw.myapi.api.dto.StudentDto;
import edu.unimagdalena.pw.myapi.services.StudentService;
import edu.unimagdalena.pw.myapi.exceptions.DuplicateCodigoException;
import edu.unimagdalena.pw.myapi.exceptions.StudentNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public StudentController(StudentService studentService, StudentMapper studentMapper){
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }
    //buscar
    @GetMapping("/students")
    public ResponseEntity<List<StudentCreationDto>> findAll(){
        List<Student> students = studentService.findAll();
        List<StudentCreationDto> studentCreationDto = students.stream()
                                                        .map(t -> studentMapper.toStudentCreationDto(t))
                                                        .collect(Collectors.toList());                                             
        return ResponseEntity.ok().body(studentCreationDto);
    }
    //buscar id
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentCreationDto> find(@PathVariable("id") Long id){
        StudentCreationDto student = studentService.find(id)
                    .map(t -> studentMapper.toStudentCreationDto(t))
                    .orElseThrow(StudentNotFoundException::new);
        return ResponseEntity.status(HttpStatus.FOUND).body(student);
    }
    //Crear
    @PostMapping("/students")
    public ResponseEntity<StudentCreationDto> create(@RequestBody StudentDto student){
        Student newStudent = studentMapper.toEntity(student);
        Student studentCreated = null;
        try {
            studentCreated = studentService.create(newStudent);
        } catch (Exception e) {
            throw new DuplicateCodigoException();
        }
        StudentCreationDto studentCreationDto = studentMapper.toStudentCreationDto(studentCreated);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(studentCreationDto.getId())
                        .toUri();
        return ResponseEntity.created(location).body(studentCreationDto);
    }
    //editar id
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentCreationDto> update(@PathVariable("id") Long id,@RequestBody StudentCreationDto studentCreationDto) {
    Optional<Student> optionalStudent = studentService.find(id);
    if (!optionalStudent.isPresent()) {
        throw new ObjectNotFoundException("Teacher not found with id: " + id, null);
    }
    Student studentToUpdate = studentMapper.toStudentEntity(studentCreationDto);
    return studentService.update(id, studentToUpdate)
            .map(studentUpdated -> ResponseEntity.ok().body(studentMapper.toStudentCreationDto(studentToUpdate)))
            .orElseGet(() -> {
              //  StudentCreationDto studentCreationDto = studentMapper.toStudentCreationDto(studentToUpdate);
                URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(studentCreationDto.getId())
                        .toUri();
                return ResponseEntity.created(location).body(studentCreationDto);
            });
    }
    //eliminar id
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
    studentService.delete(id);
    return ResponseEntity.noContent().build();
    }
}  
