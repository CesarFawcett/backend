package edu.unimagdalena.pw.myapi.services;

import java.util.List;
import java.util.Optional;
import edu.unimagdalena.pw.myapi.entities.CourseMaterial;

public interface CourseMaterialService {
    CourseMaterial create(CourseMaterial courseMaterial);
    Optional<CourseMaterial> update(Long id, CourseMaterial newCourseMaterial);
    List<CourseMaterial> findAll();
    Optional<CourseMaterial> find(Long id);
    void delete(Long id);
    List<CourseMaterial> findCourseMaterialsByCourseId(Long courseId);
}

