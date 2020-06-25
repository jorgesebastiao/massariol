package br.com.massariol.domain.features.supervisors;

import br.com.massariol.domain.common.EntityBaseImpl;
import br.com.massariol.domain.features.signatures.Signature;
import br.com.massariol.domain.features.trainings.Training;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "supervisors")
public class Supervisor extends EntityBaseImpl<Long> implements Signature {
    @Column( nullable = false)
    private String cpf;
    @Column( nullable = false)
    private String name;
    private String cellPhone;
    private String email;
    @Lob
    @Column(name = "signaturePicture", columnDefinition = "LONGBLOB")
    private byte[] signaturePicture;
    @OneToMany(mappedBy = "supervisor")
    private List<Training> trainings;
}
