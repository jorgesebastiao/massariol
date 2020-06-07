package br.com.massariol.distribution.controllers.companies.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResumeViewModel {
    private Long id;
    private String cnpj;
    private String corporateName;
    private String tradeName;
    private boolean hasUser;
    private boolean active;
}
