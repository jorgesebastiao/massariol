package br.com.massariol.domain.features.certificates;

import br.com.massariol.domain.common.EntityBaseImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "certificates")
public class Certificate extends EntityBaseImpl<Long> {

}
