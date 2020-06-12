package br.com.massariol.application.signatures.queries;

import br.com.massariol.domain.features.signatures.SignatureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class SignatureGetEntityQuery {
    private String cpf;
    private SignatureType type;
}
