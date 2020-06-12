package br.com.massariol.application.signatures;

import br.com.massariol.application.signatures.commands.SignatureCreateCommand;
import br.com.massariol.application.signatures.queries.SignatureGetEntityQuery;
import br.com.massariol.domain.features.signatures.Signature;

public interface SignatureAppService {
    Signature findByCpfAndType(SignatureGetEntityQuery signatureGetEntityQuery);
    void add(SignatureCreateCommand signatureCreateCommand);
}
