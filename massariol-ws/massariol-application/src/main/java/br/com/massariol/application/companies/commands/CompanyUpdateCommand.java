package br.com.massariol.application.companies.commands;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyUpdateCommand {
    private Long id;
    private String corporateName;
    private String tradeName;
    private String cellPhone;
    private String email;
}
