package br.com.massariol.domain.features.students;

import br.com.massariol.domain.common.EntityBaseImpl;
import br.com.massariol.domain.features.businessstudents.BusinessStudent;
import br.com.massariol.domain.features.signatures.Signature;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
    private String signaturePicture;

    @OneToMany(mappedBy = "student")
    private List<BusinessStudent> businessstudents;
}
