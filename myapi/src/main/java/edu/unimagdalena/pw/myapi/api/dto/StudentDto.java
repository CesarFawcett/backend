package edu.unimagdalena.pw.myapi.api.dto;

import edu.unimagdalena.pw.myapi.entities.Gender;
import lombok.Data;

@Data
public class StudentDto {
    private String  lastName;
    private String firstName;
    private String codigo;
    private Gender gender;
    
}
