package br.com.onaya.schedule.service.impl;

import br.com.onaya.schedule.model.Person;
import br.com.onaya.schedule.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonImplementationService {

    @Autowired
    private PersonService service;

    public Iterable<Person> findAllPerson() {
        return service.findAll();
    }

    public Optional<Person> findPersonById(Person person) {
        return service.findById(person.getId());
    }

    public Person savePerson(Person person) {
        return service.save(person);
    }

    public void deletePersonById(Person person) {
        service.deleteById(person.getId());
    }

    public Person updatePerson(Person person) {
        return service.save(person);
    }

}
