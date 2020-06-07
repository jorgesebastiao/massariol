package br.com.massariol.infrastructure.repositories.certificates;

import br.com.massariol.domain.features.certificates.Certificate;
import br.com.massariol.infrastructure.repositories.base.RepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends RepositoryBase<Certificate, Long> {

}
