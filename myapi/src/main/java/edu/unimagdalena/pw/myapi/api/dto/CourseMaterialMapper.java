package edu.unimagdalena.pw.myapi.api.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.unimagdalena.pw.myapi.entities.CourseMaterial;

@Component
public class CourseMaterialMapper {
    public CourseMaterialDto toDto(CourseMaterial courseMaterial) {
        CourseMaterialDto courseMaterialDto = new CourseMaterialDto();
        courseMaterialDto.setUrl(courseMaterial.getUrl());
        courseMaterialDto.setCourse(courseMaterial.getCourse());
        return courseMaterialDto;
    }
    public List<CourseMaterialDto> toDtoList(List<CourseMaterial> courseMaterials){
        List<CourseMaterialDto> courseMaterialDtos = new ArrayList<>();
        for(CourseMaterial courseMaterial : courseMaterials) {
            CourseMaterialDto courseMaterialDto = new CourseMaterialDto();
            courseMaterialDto.setUrl(courseMaterial.getUrl());
            courseMaterialDto.setCourse(courseMaterial.getCourse());
            courseMaterialDtos.add(courseMaterialDto);
        }
        return courseMaterialDtos;
     }
    public CourseMaterial toEntity(CourseMaterialDto courseMaterialDto){
        CourseMaterial courseMaterial =new CourseMaterial();
        courseMaterial.setUrl(courseMaterialDto.getUrl());
        courseMaterial.setCourse(courseMaterialDto.getCourse());

        return courseMaterial;
    }
    public CourseMaterialCreationDto toCourseMaterialCreationDto(CourseMaterial courseMaterial){
        CourseMaterialCreationDto courseMaterialCreationDto = new CourseMaterialCreationDto();
        courseMaterialCreationDto.setId(courseMaterial.getId());
        courseMaterialCreationDto.setUrl(courseMaterial.getUrl());
        courseMaterialCreationDto.setCourse(courseMaterial.getCourse());

        return courseMaterialCreationDto;
    }
    public CourseMaterial toCourseMaterialEntity(CourseMaterialCreationDto courseMaterialCreationDto){
        CourseMaterial courseMaterial = new CourseMaterial();
        courseMaterial.setId(courseMaterialCreationDto.getId());
        courseMaterial.setUrl(courseMaterialCreationDto.getUrl());
        courseMaterial.setCourse(courseMaterialCreationDto.getCourse());

        return courseMaterial;
    }
}
