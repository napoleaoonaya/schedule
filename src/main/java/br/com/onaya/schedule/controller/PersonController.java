package br.com.onaya.schedule.controller;

import br.com.onaya.schedule.model.Person;
import br.com.onaya.schedule.model.dto.PersonDTO;
import br.com.onaya.schedule.service.impl.PersonImplementationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonImplementationService repository;

    @GetMapping
    public ResponseEntity<Iterable<Person>> findAllPerson() {
        new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.ok(repository.findAllPerson());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Person>> findPersonById(@PathVariable Long id) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(id);

        Person person = new Person();
        person.setId(personDTO.getId());

        var optional = repository.findPersonById(person);

        if (optional.isEmpty()) {
            return new ResponseEntity<Optional<Person>>(HttpStatus.NO_CONTENT);
        }

        new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.ok().body(repository.findPersonById(person));
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody PersonDTO personDTO) {
        Person person = new Person();
        person.setAddress(personDTO.getAddress());
        person.setCell(personDTO.getCell());
        person.setEmail(personDTO.getEmail());
        person.setLastName(personDTO.getLastName());
        person.setMiddleName(personDTO.getMiddleName());
        person.setName(personDTO.getName());
        person.setTelephone(personDTO.getTelephone());
        new ResponseEntity<>(HttpStatus.CREATED);
        return ResponseEntity.ok().body(repository.savePerson(person));
    }

    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable Long id) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(id);

        Person person = new Person();
        person.setId(personDTO.getId());

        var optional = repository.findPersonById(person);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no this person!");
        }
        repository.deletePersonById(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {


        PersonDTO personId = new PersonDTO();
        personId.setId(id);

        Person person = new Person();
        person.setId(personDTO.getId());

        var optional = repository.findPersonById(person);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no this person!");
        }

        person.setId(personId.getId());
        person.setAddress(personDTO.getAddress());
        person.setCell(personDTO.getCell());
        person.setEmail(personDTO.getEmail());
        person.setLastName(personDTO.getLastName());
        person.setMiddleName(personDTO.getMiddleName());
        person.setName(personDTO.getName());
        person.setTelephone(personDTO.getTelephone());
        new ResponseEntity<>(HttpStatus.CREATED);
        return ResponseEntity.ok().body(repository.savePerson(person));

    }
}
