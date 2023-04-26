package edu.unimagdalena.pw.myapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.unimagdalena.pw.myapi.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{  
}
