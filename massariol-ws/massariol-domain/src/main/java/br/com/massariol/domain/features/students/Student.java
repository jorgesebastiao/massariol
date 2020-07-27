package br.com.massariol.domain.features.students;

import br.com.massariol.domain.common.EntityBase;
import br.com.massariol.domain.features.businessstudents.BusinessStudent;
import br.com.massariol.domain.features.persons.Person;
import br.com.massariol.domain.features.signatures.Signature;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "students")
public class Student extends EntityBase<Long> implements Signature {
    private String office;
    @OneToOne
    @JoinColumn(name = "personId", referencedColumnName = "id")
    private Person person;
    @OneToMany(mappedBy = "student")
    private List<BusinessStudent> businessStudents;

    public String getSignature() {
        return  person.getSignature();
    }
}
