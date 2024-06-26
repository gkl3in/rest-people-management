package br.com.restpeoplemanagement.unittests.person;

import br.com.restpeoplemanagement.controllers.PersonController;
import br.com.restpeoplemanagement.exceptions.ResourceNotFoundException;
import br.com.restpeoplemanagement.model.Addresses;
import br.com.restpeoplemanagement.services.PersonServices;
import br.com.restpeoplemanagement.vo.AddressesVO;
import br.com.restpeoplemanagement.vo.PersonVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PersonControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PersonServices personService;

    @InjectMocks
    private PersonController personController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    public void testFindAll() throws Exception {
        List<PersonVO> personList = new ArrayList<>();
        personList.add(new PersonVO(1L, "John Doe", new Date(), new ArrayList<Addresses>()));
        personList.add(new PersonVO(2L, "Jane Doe", new Date(), new ArrayList<Addresses>()));

        when(personService.findAll()).thenReturn(personList);

        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].fullName").value("John Doe"))
                .andExpect(jsonPath("$[1].fullName").value("Jane Doe"));
    }

    @Test
    public void testFindById() throws Exception {
        PersonVO person = new PersonVO(1L, "John Doe", new Date(), new ArrayList<Addresses>());
        when(personService.findById(1L)).thenReturn(person);

        mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("John Doe"));
    }

    @Test
    public void testCreate() throws Exception {
        PersonVO person = new PersonVO(1L, "John Doe", new Date(), new ArrayList<Addresses>());
        when(personService.create(any(PersonVO.class))).thenReturn(person);

        String jsonRequest = "{\"fullName\": \"John Doe\", \"birthDate\": \"1990-01-01\", \"addresses\": []}";

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("John Doe"));
    }

    @Test
    public void testUpdateSuccessful() throws Exception {
        PersonVO person = new PersonVO(1L, "Updated Name", new Date(), new ArrayList<Addresses>());
        when(personService.update(any(PersonVO.class))).thenReturn(person);

        String jsonRequest = "{\"id\": 1, \"fullName\": \"Updated Name\", \"birthDate\": \"1990-01-01\", \"addresses\": []}";

        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Updated Name"));
    }

    @Test
    public void testUpdateWhenNoRecordsFound() throws Exception {
        when(personService.update(Mockito.any())).thenThrow(new ResourceNotFoundException("No records found for this ID!"));

        assertThrows(ResourceNotFoundException.class, () -> {
            personController.update(new PersonVO(1L, "Updated Name", new Date(), new ArrayList<Addresses>()));
        }, "Expected update() in controller to throw ResourceNotFoundException, but it didn't");
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/person/1"))
                .andExpect(status().isNoContent());

        verify(personService, times(1)).delete(1L);
    }
}
