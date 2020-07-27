package br.com.massariol.application.persons;

import br.com.massariol.domain.features.businessstudents.BusinessStudent;
import br.com.massariol.domain.features.companies.Company;
import br.com.massariol.domain.features.persons.Person;
import br.com.massariol.domain.features.students.Student;
import br.com.massariol.infrastructure.repositories.persons.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonAppServiceImpl implements PersonAppService{
    private  final PersonRepository personRepository;

    public PersonAppServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person manager(Person person) {
        return personRepository.findByCpf(person.getCpf())
                .map(personDatabase ->{
                    personDatabase.setName(person.getName());
                    personDatabase.setEmail(person.getEmail());
                    personDatabase.setCellPhone(person.getCellPhone());
                    return  personRepository.save(personDatabase);
                })
                .orElseGet(() -> {
                    return personRepository.save(person);
                });
    }
}
