package br.com.massariol.domain.features.students;

import br.com.massariol.domain.common.EntityBaseImpl;
import br.com.massariol.domain.features.businessstudents.BusinessStudent;
import br.com.massariol.domain.features.signatures.Signature;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "students")
public class Student extends EntityBaseImpl<Long> implements Signature {
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String name;
    private String cellPhone;
    private String email;
    private String office;
    private String profilePicture;
    @Lob
    @Column(name = "signaturePicture", columnDefinition = "LONGBLOB")
    private byte[] signaturePicture;

    @OneToMany(mappedBy = "student")
    private List<BusinessStudent> businessstudents;

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
