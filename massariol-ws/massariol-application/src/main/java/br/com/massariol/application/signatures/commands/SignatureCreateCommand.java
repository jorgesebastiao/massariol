package br.com.massariol.application.signatures.commands;

import br.com.massariol.domain.features.signatures.SignatureType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignatureCreateCommand {
    private Long entityId;
    private SignatureType type;
    private String signature;
}
