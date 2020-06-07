package br.com.massariol.application.instructors.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorUpdateCommand {
    private Long id;
    private String name;
    private String cellPhone;
    private String email;
    private String signaturePicture;
}
