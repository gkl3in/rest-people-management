package br.com.restpeoplemanagement.controllers;

import br.com.restpeoplemanagement.services.AddressesServices;
import br.com.restpeoplemanagement.vo.AddressesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/address")
public class AddressesController {

    @Autowired
    private AddressesServices service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AddressesVO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AddressesVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AddressesVO create(@RequestBody AddressesVO address) {
        return service.create(address);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AddressesVO update(@RequestBody AddressesVO address) {
        return service.update(address);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}