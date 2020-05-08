package rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import rest.database.PersonRepository;
import rest.models.Person;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"rest.controllers"})
@SpringBootTest
@AutoConfigureMockMvc
class PersonsControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PersonRepository base;

    @org.junit.jupiter.api.Test
    public void whenWeTryfindAllThenFoundOnePosition() throws Exception {
        List<Person> temp = List.of(getPerson());

        given(this.base.findAll()).willReturn(temp);

        this.mvc.perform(
                get("/persons/").accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());

        verify(this.base, times(1)).findAll();
    }

    @org.junit.jupiter.api.Test
    public void whenWeTryfindByIdThencheckStatus() throws Exception {
        given(this.base.findById(1)).willReturn(Optional.of(getPerson()));

        this.mvc.perform(
                get("/persons/{id}", getPerson().getId()).accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());

        verify(this.base, times(1)).findById(1);
    }

    @org.junit.jupiter.api.Test
    public void whenWeTryCreateThenCheckStatus() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode pers = mapper.createObjectNode();

        pers.put("id", 1);
        pers.put("login", "person");
        pers.put("password", "person");


        given(this.base.save(getPerson())).willReturn(getPerson());

        this.mvc.perform(
                post("/persons/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pers.toString())
        )
                .andExpect(status().isCreated());

        verify(this.base, times(1)).save(getPerson());


    }

    @org.junit.jupiter.api.Test
    public void whenWeTryUpdateThenCheckStatus() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode pers = mapper.createObjectNode();

        pers.put("id", 1);
        pers.put("login", "person");
        pers.put("password", "person");


        given(this.base.save(getPerson())).willReturn(getPerson());

        this.mvc.perform(
                put("/persons/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pers.toString())
        )
                .andExpect(status().isOk());

        verify(this.base, times(1)).save(getPerson());


    }

    @org.junit.jupiter.api.Test
    public void whenDeletePersonThenCheckStatus() throws Exception {
        Person person = getPerson();
        person.setLogin("null");

        doNothing().when(this.base).delete(person);

        this.mvc.perform(
                delete("/persons/{id}", getPerson().getId())
        )
                .andExpect(status().isOk());

    }

    private Person getPerson() {
        Person person = new Person();
        person.setId(1);
        person.setLogin("person");
        person.setPassword("person");
        return person;
    }

}