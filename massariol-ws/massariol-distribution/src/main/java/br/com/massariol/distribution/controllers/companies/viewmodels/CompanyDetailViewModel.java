package br.com.massariol.distribution.controllers.companies.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDetailViewModel {
    private Long id;
    private String corporateName;
    private String tradeName;
    private String cnpj;
    private String cellPhone;
    private String email;
}
