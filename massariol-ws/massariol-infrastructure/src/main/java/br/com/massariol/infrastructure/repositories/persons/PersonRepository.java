package br.com.massariol.infrastructure.repositories.persons;

import br.com.massariol.domain.features.persons.Person;
import br.com.massariol.infrastructure.repositories.base.RepositoryBase;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends RepositoryBase<Person, Long> {
    Optional<Person> findByCpf(String cpf);
}
