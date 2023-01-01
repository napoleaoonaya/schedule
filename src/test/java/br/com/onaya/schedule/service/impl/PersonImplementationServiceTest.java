package br.com.onaya.schedule.service.impl;

import br.com.onaya.schedule.model.Person;
import br.com.onaya.schedule.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    @InjectMocks
    private PersonImplementationService implementationService;
    @Mock
    private PersonService service;
    private Person person;
    private Iterable<Person> personIterable;
    private Optional<Person> personOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        person = new Person(ID, NAME, MIDDLE_NAME, LAST_NAME, ADDRESS, EMAIL, CELL, TELEPHONE);
        personOptional = Optional.of(new Person(ID, NAME, MIDDLE_NAME, LAST_NAME, ADDRESS, EMAIL, CELL, TELEPHONE));
        personIterable = mock(Iterable.class);
    }

    @AfterEach
    void tearDown() {
        person = null;
        personOptional = Optional.empty();
        personIterable = null;
    }

    @Test
    void findAllPerson() {
    }

    @Test
    void findPersonById() {
        when(service.findById(anyLong())).thenReturn(personOptional);
        Optional<Person> response = service.findById(ID);
        assertEquals(response.getClass(), service.findById(ID).getClass());
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
    void savePerson() {
    }

    @Test
    void deletePersonById() {
    }


    @Test
    void updatePerson() {
    }

}