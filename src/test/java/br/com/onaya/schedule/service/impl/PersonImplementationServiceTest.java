package br.com.onaya.schedule.service.impl;

import br.com.onaya.schedule.model.Person;
import br.com.onaya.schedule.model.dto.PersonDTO;
import br.com.onaya.schedule.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonImplementationServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "sofia";
    public static final String MIDDLE_NAME = "lorem";
    public static final String LAST_NAME = "jesus";
    public static final String ADDRESS = "rua dos cachorrinhos";
    public static final String EMAIL = "sofia@auau.com";
    public static final String CELL = "1199999-9999";
    public static final String TELEPHONE = "119999-9999";
    public static final String NO_CONTENT = "204 NO_CONTENT";
    @InjectMocks
    private PersonImplementationService implementationService;
    @Mock
    private PersonService service;
    private Person person;
    private PersonDTO personDTO;
    private List<Person> personList;
    private Iterable<Person> personIterable;
    private Optional<Person> personOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        person = new Person(ID, NAME, MIDDLE_NAME, LAST_NAME, ADDRESS, EMAIL, CELL, TELEPHONE);
        personDTO = new PersonDTO(ID, NAME, MIDDLE_NAME, LAST_NAME, ADDRESS, EMAIL, CELL, TELEPHONE);
        personOptional = Optional.of(new Person(ID, NAME, MIDDLE_NAME, LAST_NAME, ADDRESS, EMAIL, CELL, TELEPHONE));

        personList = new ArrayList<Person>();
        personList.add(person);

        personIterable = personList;

    }

    @AfterEach
    void tearDown() {
        person = null;
        personDTO = null;
        personOptional = Optional.empty();
        personIterable = null;
    }

    @Test
    void findAllPerson() {
        when(service.findAll()).thenReturn(personIterable);
        Iterable<Person> iterable = implementationService.findAllPerson();
        assertNotNull(iterable);
        assertEquals(true, iterable.iterator().hasNext());
        assertEquals(ID, iterable.iterator().next().getId());
        assertEquals(ADDRESS, iterable.iterator().next().getAddress());
        assertEquals(NAME, iterable.iterator().next().getName());
        assertEquals(MIDDLE_NAME, iterable.iterator().next().getMiddleName());
        assertEquals(LAST_NAME, iterable.iterator().next().getLastName());
        assertEquals(EMAIL, iterable.iterator().next().getEmail());
        assertEquals(CELL, iterable.iterator().next().getCell());
        assertEquals(TELEPHONE, iterable.iterator().next().getTelephone());

    }

    @Test
    void findPersonById() {
        person.setId(personDTO.getId());
        when(service.findById(anyLong())).thenReturn(personOptional);
        Optional<Person> response = implementationService.findPersonById(person);
        assertEquals(response.getClass(), implementationService.findPersonById(person).getClass());
        assertEquals(ID, response.get().getId());
        assertEquals(ADDRESS, response.get().getAddress());
        assertEquals(NAME, response.get().getName());
        assertEquals(MIDDLE_NAME, response.get().getMiddleName());
        assertEquals(LAST_NAME, response.get().getLastName());
        assertEquals(EMAIL, response.get().getEmail());
        assertEquals(CELL, response.get().getCell());
        assertEquals(TELEPHONE, response.get().getTelephone());
    }

    @Test
    void findPersonByIdException() {
        when(service.findById(anyLong())).thenThrow(new ResponseStatusException(HttpStatus.NO_CONTENT));
        try {
            service.findById(ID);
        } catch (ResponseStatusException exception) {
            assertEquals(ResponseStatusException.class, exception.getClass());
            assertEquals("204 NO_CONTENT", exception.getMessage());
        }
    }

    @Test
    void savePerson() {
        when(service.save(any())).thenReturn(person);
        BeanUtils.copyProperties(personDTO, person);
        person = implementationService.savePerson(person);
        assertEquals(ID, person.getId());
        assertEquals(ADDRESS, person.getAddress());
        assertEquals(NAME, person.getName());
        assertEquals(MIDDLE_NAME, person.getMiddleName());
        assertEquals(LAST_NAME, person.getLastName());
        assertEquals(EMAIL, person.getEmail());
        assertEquals(CELL, person.getCell());
        assertEquals(TELEPHONE, person.getTelephone());
    }

    @Test
    void deletePersonById() {
        when(service.findById(anyLong())).thenReturn(personOptional);
        doNothing().when(service).deleteById(anyLong());
        implementationService.deletePersonById(person);
        verify(service, times(1)).deleteById(anyLong());
    }

    @Test
    void deletePersonByIdException() {
        when(service.findById(anyLong())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no this person!"));
        try {
            service.findById(ID);
            doNothing().when(service).deleteById(anyLong());
            implementationService.deletePersonById(person);
        } catch (ResponseStatusException exception) {
            assertEquals(ResponseStatusException.class, exception.getClass());
            assertEquals("400 BAD_REQUEST \"There is no this person!\"", exception.getMessage());
        }
    }

    @Test
    void updatePerson() {
        when(service.save(any())).thenReturn(person);
        BeanUtils.copyProperties(personDTO, person);
        person = implementationService.updatePerson(person);
        assertEquals(ID, person.getId());
        assertEquals(ADDRESS, person.getAddress());
        assertEquals(NAME, person.getName());
        assertEquals(MIDDLE_NAME, person.getMiddleName());
        assertEquals(LAST_NAME, person.getLastName());
        assertEquals(EMAIL, person.getEmail());
        assertEquals(CELL, person.getCell());
        assertEquals(TELEPHONE, person.getTelephone());
    }

}