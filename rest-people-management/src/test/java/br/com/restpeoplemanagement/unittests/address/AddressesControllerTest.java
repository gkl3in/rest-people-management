package br.com.restpeoplemanagement.unittests.address;
import br.com.restpeoplemanagement.controllers.AddressesController;
import br.com.restpeoplemanagement.exceptions.ResourceNotFoundException;
import br.com.restpeoplemanagement.repositories.AddressesRepository;
import br.com.restpeoplemanagement.services.AddressesServices;
import br.com.restpeoplemanagement.vo.AddressesVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressesControllerTest {

    @Mock
    private AddressesServices addressesServices;

    @Mock
    private AddressesRepository addressesRepository;

    @InjectMocks
    private AddressesController addressesController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<AddressesVO> addressesList = new ArrayList<>();
        // Adicione alguns endereços à lista de endereços
        addressesList.add(new AddressesVO(1L, "12345-678", "Rua Exemplo", "1234-5678", "Cidade Exemplo", "Estado Exemplo", 123L));

        when(addressesServices.findAll()).thenReturn(addressesList);

        List<AddressesVO> result = addressesController.findAll();

        assertEquals(addressesList.size(), result.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        AddressesVO address = new AddressesVO(id, "12345-678", "Rua Exemplo", "1234-5678", "Cidade Exemplo", "Estado Exemplo", 123L);

        when(addressesServices.findById(id)).thenReturn(address);

        AddressesVO result = addressesController.findById(id);

        assertEquals(address.getId(), result.getId());
    }

    @Test
    public void testCreate() {
        AddressesVO addressToCreate = new AddressesVO(null, "12345-678", "Rua Exemplo", "1234-5678", "Cidade Exemplo", "Estado Exemplo", 123L);
        AddressesVO createdAddress = new AddressesVO(1L, "12345-678", "Rua Exemplo", "1234-5678", "Cidade Exemplo", "Estado Exemplo", 123L);

        when(addressesServices.create(addressToCreate)).thenReturn(createdAddress);

        AddressesVO result = addressesController.create(addressToCreate);

        assertEquals(createdAddress.getId(), result.getId());
    }

    @Test
    public void testUpdateSuccessfull() {
        Long id = 1L;
        AddressesVO addressToUpdate = new AddressesVO(id, "12345-678", "Rua Exemplo", "1234-5678", "Cidade Exemplo", "Estado Exemplo", 123L);
        AddressesVO updatedAddress = new AddressesVO(id, "54321-987", "Avenida Exemplo", "9876-5432", "Nova Cidade", "Novo Estado", 456L);

        when(addressesServices.update(addressToUpdate)).thenReturn(updatedAddress);

        AddressesVO result = addressesController.update(addressToUpdate);

        assertEquals(updatedAddress.getId(), result.getId());
        assertEquals(updatedAddress.getCep(), result.getCep());
        assertEquals(updatedAddress.getPublicPlace(), result.getPublicPlace());
        assertEquals(updatedAddress.getNumberPhone(), result.getNumberPhone());
        assertEquals(updatedAddress.getCity(), result.getCity());
        assertEquals(updatedAddress.getState(), result.getState());
        assertEquals(updatedAddress.getPersonId(), result.getPersonId());
    }

    @Test
    public void testUpdateWhenNoRecordsFound() {
        when(addressesServices.update(Mockito.any())).thenThrow(new ResourceNotFoundException("No records found for this ID!"));

        assertThrows(ResourceNotFoundException.class, () -> {
            addressesController.update(new AddressesVO(1L, "12345-678", "Rua Exemplo", "1234-5678", "Cidade Exemplo", "Estado Exemplo", 123L));
        }, "Expected update() in controller to throw ResourceNotFoundException, but it didn't");
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        ResponseEntity<?> expectedResponse = ResponseEntity.noContent().build();

        ResponseEntity<?> result = addressesController.delete(id);

        assertEquals(expectedResponse.getStatusCode(), result.getStatusCode());
    }
}
