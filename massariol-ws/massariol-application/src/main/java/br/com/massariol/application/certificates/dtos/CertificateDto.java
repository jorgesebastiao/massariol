package br.com.massariol.application.certificates.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class CertificateDto {
    private Long studentId;
    private String courseGuideline;
    private String courseName;
    private LocalDate finishDate;
    private String instructorCpf;
    private String instructorName;
    private String instructorSignature;
    private String supervisorCpf;
    private String supervisorName;
    private String supervisorSignature;
    private LocalDate startDate;
    private String studentCpf;
    private String studentName;
    private String studentSignature;
    private int workload;
    private static Map<String, Object> parameters;
    private static Map<String, Object> backParameters;

    public Map<String, Object> getParameters(String imagePath) {

        if (parameters == null) {
            parameters = new HashMap<>();
            parameters.put("frontCertificate", imagePath);
        }

        return parameters;
    }

    public Map<String, Object> getLogo(String path) {

        if (backParameters == null) {
            backParameters = new HashMap<>();
            backParameters.put("logo", path);
        }

        return backParameters;
    }

}
