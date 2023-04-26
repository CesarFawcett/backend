package edu.unimagdalena.pw.myapi.api.dto;

import java.util.HashSet;
import java.util.Set;
import edu.unimagdalena.pw.myapi.entities.CourseMaterial;
import edu.unimagdalena.pw.myapi.entities.Student;
import edu.unimagdalena.pw.myapi.entities.Teacher;
import lombok.Data;

@Data
public class CourseDto {
   private Long id;
   private String name;
   private Teacher teacher;
   private Set<Student> students;
   // método para agregar un estudiante al conjunto
   public void addStudent(Student student) {
       if (students == null) {
           students = new HashSet<>();
       }
       students.add(student);
   }
   public Set<Student> getStudents() {
       return students;
   }
   public void setStudents(Set<Student> students) {
       this.students = students;
   }
   private CourseMaterial courseMaterial;
}
