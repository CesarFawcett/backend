package edu.unimagdalena.pw.myapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.unimagdalena.pw.myapi.entities.CourseMaterial;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long>{

    List<CourseMaterial> findByCourseId(Long courseId);   
}
