package br.com.onaya.schedule.controller;

import br.com.onaya.schedule.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ObjectMapper objectMapper;

    public static final long ID = 1L;
    public static final String NAME = "sofia";
    public static final String MIDDLE_NAME = "lorem";
    public static final String LAST_NAME = "jesus";
    public static final String ADDRESS = "rua dos cachorrinhos";
    public static final String EMAIL = "sofia@auau.com";
    public static final String CELL = "1199999-9999";
    public static final String TELEPHONE = "119999-9999";

    private Person person;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        person = new Person(ID, NAME, MIDDLE_NAME, LAST_NAME, ADDRESS, EMAIL, CELL, TELEPHONE);
    }


    @Test
    public void findPersonAll() throws Exception {
        mockMvc.perform(get("/person").accept(status().isOk().toString()));
    }

    @Test
    public void findPersonById() throws Exception {
        Long id = ID;
        mockMvc.perform(get("/person/" + id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(person.getId()))
                .accept(status().isOk().toString())
        );
    }

    @Test
    public void savePerson() throws Exception {
        mockMvc.perform(post("/person")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(person))
                .accept(status().isOk().toString())
        );
    }

    @Test
    public void updatePerson() throws Exception {
        Long id = ID;
        mockMvc.perform(put("/person/" + id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(person))
                .accept(status().isOk().toString())
        );
    }


    @Test
    public void updatePersonException() {
        String exceptionParam = "404 " + "\"There is no this person!\"";
        try {
            ResultActions perform = mockMvc.perform(put("/person/{id}", exceptionParam)
                    .contentType("application/json")
                    .content(exceptionParam)
                    .accept(status().isBadRequest().toString())
                    .accept(String.valueOf(new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no this person!")))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deletePersonById() throws Exception {
        Long id = ID;
        mockMvc.perform(delete("/person/" + id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(person.getId()))
                .accept(status().isOk().toString())
        );
    }

    @Test
    public void deletePersonByIdException() {
        String exceptionParam = "400 " + "\"There is no this person!\"";
        try {
            ResultActions perform = mockMvc.perform(put("/person/{id}", exceptionParam)
                    .contentType("application/json")
                    .content(exceptionParam)
                    .accept(status().isBadRequest().toString())
                    .accept(String.valueOf(new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no this person!")))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}