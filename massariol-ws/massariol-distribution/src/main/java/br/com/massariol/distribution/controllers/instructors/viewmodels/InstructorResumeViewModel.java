package br.com.massariol.distribution.controllers.instructors.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorResumeViewModel {
    private Long id;
    private String cpf;
    private String name;
    private String cellPhone;
    private String email;
    private String signaturePicture;
}
