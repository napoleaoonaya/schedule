package br.com.onaya.schedule.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import br.com.onaya.schedule.model.Person;

@Service
public interface PersonService extends CrudRepository<Person, Long> {
}
