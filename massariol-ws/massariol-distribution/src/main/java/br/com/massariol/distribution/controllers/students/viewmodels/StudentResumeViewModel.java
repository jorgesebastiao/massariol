package br.com.massariol.distribution.controllers.students.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResumeViewModel {
    private Long id;
    private String cpf;
    private String name;
    private String cellPhone;
    private String email;
}
