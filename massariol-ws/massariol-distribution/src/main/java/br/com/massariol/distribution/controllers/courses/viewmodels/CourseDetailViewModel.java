package br.com.massariol.distribution.controllers.courses.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDetailViewModel {
    private Long id;
    private String name;
    private String guideline;
    private String courseIdentification;
    private int workload;
    private int validityInYears;
}
