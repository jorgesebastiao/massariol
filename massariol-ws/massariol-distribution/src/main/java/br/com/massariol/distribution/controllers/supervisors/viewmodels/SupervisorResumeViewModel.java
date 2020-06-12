package br.com.massariol.distribution.controllers.supervisors.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupervisorResumeViewModel {
    private Long id;
    private String cpf;
    private String name;
    private String cellPhone;
    private String email;
}
