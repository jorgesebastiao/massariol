package br.com.massariol.application.supervisors.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupervisorCreateCommand {
    private String cpf;
    private String name;
    private String cellPhone;
    private String email;
}
