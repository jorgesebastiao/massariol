package br.com.massariol.application.users.queries;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailQuery   {
    private Long id;
    private String name;
    private String email;
    private String companyId;
    private String permission;
}
