package edu.unimagdalena.pw.myapi.api.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.util.UriComponentsBuilder;
import edu.unimagdalena.pw.myapi.api.dto.CourseMaterialCreationDto;
import edu.unimagdalena.pw.myapi.api.dto.CourseMaterialDto;
import edu.unimagdalena.pw.myapi.api.dto.CourseMaterialMapper;
import edu.unimagdalena.pw.myapi.entities.CourseMaterial;
import edu.unimagdalena.pw.myapi.exceptions.DuplicateCodigoException;
import edu.unimagdalena.pw.myapi.services.CourseMaterialService;

@RestController
@RequestMapping("/api/v1")
public class CourseMaterialController {
    private final CourseMaterialService courseMaterialService;
    private final CourseMaterialMapper courseMaterialMapper;

    @Autowired
    public CourseMaterialController(CourseMaterialService courseMaterialService,
                                     CourseMaterialMapper courseMaterialMapper) {
        this.courseMaterialService = courseMaterialService;
        this.courseMaterialMapper = courseMaterialMapper;
    }
    //buscar
    @GetMapping("/coursematerials")
    public ResponseEntity<List<CourseMaterialDto>> findAll() {
        List<CourseMaterial> courseMaterials = courseMaterialService.findAll();
        return new ResponseEntity<>(courseMaterialMapper.toDtoList(courseMaterials), HttpStatus.OK);
    }
    //buscar id
    @GetMapping("/coursematerials/{id}")
    public ResponseEntity<CourseMaterialDto> findById(@PathVariable Long id) {
        Optional<CourseMaterial> optionalCourseMaterial = courseMaterialService.find(id);
        if (optionalCourseMaterial.isPresent()) {
            CourseMaterial courseMaterial = optionalCourseMaterial.get();
            return new ResponseEntity<>(courseMaterialMapper.toDto(courseMaterial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //crear
    @PostMapping("/coursematerials")
    public ResponseEntity<CourseMaterialCreationDto> create(@RequestBody CourseMaterialDto courseMaterial){
        CourseMaterial newCourseMaterial = courseMaterialMapper.toEntity(courseMaterial);
        CourseMaterial courseMaterialCreated = courseMaterialService.create(newCourseMaterial);
           if (courseMaterialCreated == null) {
              throw new DuplicateCodigoException();
            }
        CourseMaterialCreationDto courseMaterialCreationDto = courseMaterialMapper.toCourseMaterialCreationDto(courseMaterialCreated);
            return ResponseEntity.created(UriComponentsBuilder.fromPath("/{id}")
            .buildAndExpand(courseMaterialCreationDto.getId())
            .toUri())
            .body(courseMaterialCreationDto);
    }
    //editar id
    @PutMapping("/coursematerials/{id}")
    public ResponseEntity<CourseMaterialDto> update(@PathVariable Long id,
                                                     @RequestBody CourseMaterialDto courseMaterialDto) {
        Optional<CourseMaterial> optionalCourseMaterial = courseMaterialService.find(id);
        if (optionalCourseMaterial.isPresent()) {
            CourseMaterial courseMaterial = optionalCourseMaterial.get();
            courseMaterial.setUrl(courseMaterialDto.getUrl());
            courseMaterial.setCourse(courseMaterialDto.getCourse());
            courseMaterial = courseMaterialService.update(id, courseMaterial).get();
            return new ResponseEntity<>(courseMaterialMapper.toDto(courseMaterial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/coursematerials/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        courseMaterialService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
