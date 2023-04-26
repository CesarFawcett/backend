package edu.unimagdalena.pw.myapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.pw.myapi.entities.CourseMaterial;
import edu.unimagdalena.pw.myapi.repositories.CourseMaterialRepository;

@Service
public class CourseMaterialServicelmpl implements CourseMaterialService {  
    private final CourseMaterialRepository courseMaterialRepository;    
    
    @Autowired 
    public CourseMaterialServicelmpl(CourseMaterialRepository courseMaterialRepository) {
        this.courseMaterialRepository = courseMaterialRepository;
    }  
    @Override
    public CourseMaterial create(CourseMaterial courseMaterial) {
        CourseMaterial copy = new CourseMaterial(null, 
                                 courseMaterial.getUrl(), 
                                 courseMaterial.getCourse());
        return courseMaterialRepository.save(copy);
    }
    @Override
    public Optional<CourseMaterial> update(Long id, CourseMaterial newCourseMaterial) {
        return courseMaterialRepository.findById(id)
                .map(oldCourseMaterial -> {
                    CourseMaterial courseMaterial = oldCourseMaterial.updateWith(newCourseMaterial);
                    return courseMaterialRepository.save(courseMaterial);
                });
    }
    @Override
    public List<CourseMaterial> findCourseMaterialsByCourseId(Long courseId) {
        return courseMaterialRepository.findByCourseId(courseId);
    }
    @Override
    public List<CourseMaterial> findAll() {
        return courseMaterialRepository.findAll();
    }
    @Override
    public Optional<CourseMaterial> find(Long id) {
        return courseMaterialRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        courseMaterialRepository.deleteById(id);
    } 
}
