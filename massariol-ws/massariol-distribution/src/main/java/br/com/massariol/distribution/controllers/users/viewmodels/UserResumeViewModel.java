package br.com.massariol.distribution.controllers.users.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResumeViewModel {
    private Long id;
    private String name;
    private String email;
    private boolean active;
}
