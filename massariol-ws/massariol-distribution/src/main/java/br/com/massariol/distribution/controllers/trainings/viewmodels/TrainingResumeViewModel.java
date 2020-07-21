package br.com.massariol.distribution.controllers.trainings.viewmodels;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TrainingResumeViewModel {
    private Long id;
    private String company;
    private String course;
    private LocalDate startDate;
    private String student;
    private LocalDate finishDate;
}
