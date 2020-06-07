package br.com.massariol.distribution.controllers.businessstudents.viewmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessStudentViewModel {
    private Long id;
    private Long studentId;
    private String studentName;
    private String studentCpf;
    private String studentOffice;
}
