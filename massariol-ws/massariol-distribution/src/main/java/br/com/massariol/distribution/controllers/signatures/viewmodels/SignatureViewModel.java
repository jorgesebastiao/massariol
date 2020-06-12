package br.com.massariol.distribution.controllers.signatures.viewmodels;

import br.com.massariol.domain.features.signatures.SignatureType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignatureViewModel {
    private Long entityId;
    private String cpf;
    private String name;
    private SignatureType type;
}
