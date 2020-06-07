package br.com.massariol.distribution.controllers.trainings.viewmodels;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TrainingDetailViewModel {
    private Long id;
    private LocalDate startDate;
    private LocalDate finishDate;
    private LocalDate expirationDate;
    private LocalDate realizationDate;
    private Long studentId;
    private TrainingStudentDetailViewModel student;
    private Long instructorId;
    private TrainingInstructorDetailViewModel instructor;
    private Long supervisorId;
    private TrainingSupervisorDetailViewModel supervisor;
    private Long courseId;
    private TrainingCourseDetailViewModel course;
    private Long companyId;
    private TrainingCompanyDetailViewModel company;
}
