package br.com.massariol.application.users.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateCommand {
    private Long id;
    private String email;
    private String name;
}
