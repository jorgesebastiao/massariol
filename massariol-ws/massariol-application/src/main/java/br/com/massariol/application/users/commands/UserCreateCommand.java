package br.com.massariol.application.users.commands;

import br.com.massariol.domain.features.permissions.PermissionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateCommand {
    private String email;
    private String name;
    private PermissionType profile;
    private Long companyId;
}

