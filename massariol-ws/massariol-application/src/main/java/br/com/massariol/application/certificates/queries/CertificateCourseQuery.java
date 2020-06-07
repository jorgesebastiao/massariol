package br.com.massariol.application.certificates.queries;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CertificateCourseQuery {

    private int studentCourseId;
    private LocalDate emissionDate;
    private LocalDate startCourseDate;
    private LocalDate endCourseDate;
    private LocalDate expirationDate;
    private String courseName;

}
