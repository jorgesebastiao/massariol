package br.com.massariol.application.trainings.commands;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TrainingCreateCommand {
    private LocalDate startDate;
    private LocalDate finishDate;
    private LocalDate expirationDate;
    private LocalDate realizationDate;
    private Long companyId;
    private Long courseId;
    private Long instructorId;
    private Long studentId;
    private Long supervisorId;
}
