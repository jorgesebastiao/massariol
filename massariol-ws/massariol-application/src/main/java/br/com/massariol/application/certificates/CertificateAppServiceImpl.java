package br.com.massariol.application.certificates;

import br.com.massariol.application.certificates.dtos.CertificateDto;
import br.com.massariol.infrastructure.repositories.trainings.TrainingRepository;
import br.com.massariol.infrastructure.repositories.trainings.TrainingSpecification;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.awt.*;
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class CertificateAppServiceImpl implements CertificateAppService {
    private final TrainingRepository trainingRepository;
    private final ModelMapper modelMapper;

    public CertificateAppServiceImpl(TrainingRepository trainingRepository, ModelMapper modelMapper) {
        this.trainingRepository = trainingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public byte[] getCertificate(Long trainingId, Long businessStudentId, Long companyId) throws Exception {
        var training = trainingRepository.findOne(TrainingSpecification.findByIdAndBusinessStudentIdAndCompanyId(trainingId, businessStudentId, companyId))
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        var certificateDto = modelMapper.map(training, CertificateDto.class);
        List<CertificateDto> certificateDtoList = Collections.singletonList(certificateDto);

        certificateDtoList.forEach(x -> {
            var parser = Parser.builder().build();
            var document = parser.parse(x.getCourseGuideline());
            var renderer = HtmlRenderer.builder().build();
            x.setCourseGuideline(renderer.render(document).replaceAll("\\r\\n|\\r|\\n", "<br/>"));
        });

        getFont();

        JasperPrint jasperPrint = JasperFillManager.fillReport(getUrlReport(), certificateDto.getParameters(getImagePath()),
                new JRBeanCollectionDataSource(certificateDtoList, false));

        jasperPrint.addPage(JasperFillManager.fillReport(getUrlBackOfCertificate(), certificateDto.getLogo(getLogoPath()),
                new JRBeanCollectionDataSource(certificateDtoList, false)).getPages().get(0));

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public void getFont() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Font font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(classLoader.getResourceAsStream("/fonts/century_schoolbook.ttf")));
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(font);
        } catch (Exception ex) {
            System.out.println("Não foi possivel carregar a font defautl" + ex.getCause());
        }
    }

    private String getImagePath() {
        return this.getClass().getResource("/images/front_certificate.png").toString();
    }

    private String getLogoPath() {
        return this.getClass().getResource("/images/logo.png").toString();
    }

    private InputStream getUrlReport() throws Exception {
        return this.getClass().getResource("/reports/certificado.jasper").openStream();
    }

    private InputStream getUrlBackOfCertificate() throws Exception {
        return this.getClass().getResource("/reports/certificado_verso.jasper").openStream();
    }

}
