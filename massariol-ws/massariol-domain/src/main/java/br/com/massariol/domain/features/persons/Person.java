package br.com.massariol.domain.features.persons;

import br.com.massariol.domain.common.EntityBase;
import br.com.massariol.domain.features.instructors.Instructor;
import br.com.massariol.domain.features.students.Student;
import br.com.massariol.domain.features.supervisors.Supervisor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Base64;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "persons")
public class Person  extends EntityBase<Long> {
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String name;
    private String cellPhone;
    private String email;
    @Lob
    @Column(name = "signaturePicture", columnDefinition = "LONGBLOB")
    private byte[] signaturePicture;
    @OneToOne(mappedBy = "person")
    private Instructor instructor;
    @OneToOne(mappedBy = "person")
    private Student student;
    @OneToOne(mappedBy = "person")
    private Supervisor supervisor;

    public String getSignature() {
        try {
            if (signaturePicture != null) {
                InputStream is = new ByteArrayInputStream(Base64.getDecoder().decode(signaturePicture));
                BufferedImage img = ImageIO.read(is);
                File output = File.createTempFile("signature" + System.currentTimeMillis(), null);
                ImageIO.write(img, "png", output);

                return output.getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
