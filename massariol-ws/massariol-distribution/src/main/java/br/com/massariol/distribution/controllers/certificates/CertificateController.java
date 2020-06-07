package br.com.massariol.distribution.controllers.certificates;

import br.com.massariol.application.certificates.CertificateAppService;
import br.com.massariol.distribution.controllers.base.ApiBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certificates")
@Api(value = "certificates", description = "Certificates")
public class CertificateController extends ApiBaseController {
    private  final CertificateAppService certificateAppService;

    public CertificateController(ResourceServerTokenServices resourceServerTokenServices, ModelMapper modelMapper, CertificateAppService certificateAppService) {
        super(resourceServerTokenServices, modelMapper);
        this.certificateAppService = certificateAppService;
    }

    @ApiOperation(value = "return a certificate by trainingId and businessStudentId")
    @GetMapping("training/{trainingId}/business-student/{businessStudentId}")
    @PreAuthorize("hasAuthority('ROLE_COMPANY_CERTIFICATE') and #oauth2.hasScope('read')")
    public ResponseEntity<byte[]> getCertificates(@PathVariable Long trainingId, @PathVariable Long businessStudentId) throws Exception{
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
                .body(certificateAppService.getCertificate(trainingId, businessStudentId, getCompanyId()));
    }
}
