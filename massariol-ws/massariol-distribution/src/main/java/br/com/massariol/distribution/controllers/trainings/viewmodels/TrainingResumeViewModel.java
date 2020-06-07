package br.com.massariol.distribution.controllers.trainings.viewmodels;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TrainingResumeViewModel {
    private Long id;
    private LocalDate startDate;
    private LocalDate finishDate;
    private LocalDate expirationDate;
    private LocalDate realizationDate;
}
