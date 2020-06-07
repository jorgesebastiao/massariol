package br.com.massariol.application.users.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyUserUpdateCommand {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Long companyId;
}
