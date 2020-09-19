package br.com.massariol.distribution.controllers.users.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCompanyViewModel {
    private Long id;
    private String corporateName;
    private String cnpj;
}
