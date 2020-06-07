package br.com.massariol.application.courses.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCreateCommand {
    private String name;
    private String guideline;
    private String courseIdentification;
    private int workload;
    private int validityInYears;
}
