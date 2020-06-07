package br.com.massariol.distribution.controllers.courses.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResumeViewModel {
    private Long id;
    private String name;
    private String courseIdentification;
    private int workload;
    private int validityInYears;
}
