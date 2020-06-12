package br.com.massariol.application.instructors.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorSignatureCommand {
    private Long id;
    private String signature;
}
