package edu.unimagdalena.pw.myapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.unimagdalena.pw.myapi.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
}
 