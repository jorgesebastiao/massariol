package br.com.massariol.application.certificates;

public interface CertificateAppService {
    byte[] getCertificate(Long trainingId)  throws Exception;

    byte[] getCertificate(Long trainingId, Long businessStudentId, Long companyId)  throws Exception;
}
