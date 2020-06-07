package br.com.massariol.distribution.controllers.users.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCompanyViewModel {

    private Long id;
    private String name;
    private String email;
    private Long companyId;

}
