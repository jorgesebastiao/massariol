package br.com.massariol.application.students.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSignatureCommand {
    private Long id;
    private String signature;
}
