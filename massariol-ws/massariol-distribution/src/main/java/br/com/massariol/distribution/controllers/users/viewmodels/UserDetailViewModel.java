package br.com.massariol.distribution.controllers.users.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailViewModel {
    private Long id;
    private String name;
    private String email;
}
