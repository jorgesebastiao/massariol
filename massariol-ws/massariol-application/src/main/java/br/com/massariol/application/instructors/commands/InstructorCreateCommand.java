package br.com.massariol.application.instructors.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorCreateCommand {
    private String cpf;
    private String name;
    private String cellPhone;
    private String email;
    private String signaturePicture;
}
