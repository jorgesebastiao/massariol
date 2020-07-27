package br.com.massariol.domain.features.supervisors;

import br.com.massariol.domain.common.EntityBase;
import br.com.massariol.domain.features.persons.Person;
import br.com.massariol.domain.features.signatures.Signature;
import br.com.massariol.domain.features.trainings.Training;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "supervisors")
public class Supervisor extends EntityBase<Long> implements Signature {
    @OneToOne
    @JoinColumn(name = "personId", referencedColumnName = "id")
    private Person person;
    @OneToMany(mappedBy = "supervisor")
    private List<Training> trainings;

    public String getSignature() {
      return  person.getSignature();
    }
}
