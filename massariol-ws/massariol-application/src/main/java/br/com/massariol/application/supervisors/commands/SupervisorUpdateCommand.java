package br.com.massariol.application.supervisors.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupervisorUpdateCommand {
    private Long id;
    private String name;
    private String cellPhone;
    private String email;
}
