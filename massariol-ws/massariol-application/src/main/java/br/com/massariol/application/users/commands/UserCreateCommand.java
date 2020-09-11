package br.com.massariol.application.users.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateCommand {
    private String email;
    private String name;
}

