package br.com.massariol.infrastructure.repositories.users;

import br.com.massariol.domain.features.users.User;
import br.com.massariol.infrastructure.repositories.base.RepositoryBase;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends RepositoryBase<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByCompanyId(Long companyId);
}
