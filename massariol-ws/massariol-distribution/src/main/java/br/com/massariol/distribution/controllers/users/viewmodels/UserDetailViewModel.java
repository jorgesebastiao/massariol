package br.com.massariol.distribution.controllers.users.viewmodels;

import br.com.massariol.domain.features.permissions.PermissionType;
import br.com.massariol.domain.features.users.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailViewModel {
    private Long id;
    private String name;
    private String email;
    private UserType type;
    private PermissionType profile;
}
