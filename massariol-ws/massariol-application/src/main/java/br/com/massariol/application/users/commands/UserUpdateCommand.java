package br.com.massariol.application.users.commands;

import br.com.massariol.domain.features.permissions.PermissionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateCommand {
    private Long id;
    private String email;
    private String name;
    private PermissionType profile;
    private boolean resendPassword;
}
