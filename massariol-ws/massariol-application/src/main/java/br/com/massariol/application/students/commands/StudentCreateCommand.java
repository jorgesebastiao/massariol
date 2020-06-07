package br.com.massariol.application.students.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCreateCommand {
    private String cpf;
    private String name;
    private String office;
    private String cellPhone;
    private String email;
}
