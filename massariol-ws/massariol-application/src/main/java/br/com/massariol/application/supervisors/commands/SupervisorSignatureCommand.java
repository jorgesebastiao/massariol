package br.com.massariol.application.supervisors.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupervisorSignatureCommand {
    private Long id;
    private String signature;
}
