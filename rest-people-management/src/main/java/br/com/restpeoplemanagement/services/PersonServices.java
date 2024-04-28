package br.com.restpeoplemanagement.services;

import br.com.restpeoplemanagement.exceptions.ResourceNotFoundException;
import br.com.restpeoplemanagement.mapper.ModelMapperGeneric;
import br.com.restpeoplemanagement.model.Addresses;
import br.com.restpeoplemanagement.model.Person;
import br.com.restpeoplemanagement.repositories.AddressesRepository;
import br.com.restpeoplemanagement.repositories.PersonRepository;
import br.com.restpeoplemanagement.vo.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    AddressesRepository addressesRepository;

    public List<PersonVO> findAll() {
        logger.info("Finding all people!");

        List<Person> personDetailsList = repository.findAllPersonDetails();
        List<Person> personVOList = new ArrayList<>();

        for (Person personDetails : personDetailsList) {
            List<Addresses> entityAddresses = addressesRepository.findAddressesDetailsById(personDetails.getId());

            Person personEntity = new Person();
            personEntity.setId(personDetails.getId());
            personEntity.setBirthDate(personDetails.getBirthDate());
            personEntity.setFullName(personDetails.getFullName());
            personEntity.setAddresses(entityAddresses);

            personVOList.add(personEntity);
        }

        return ModelMapperGeneric.parseListObjects(personVOList, PersonVO.class);
    }


    public PersonVO findById(Long id) {
        logger.info("Finding one person!");
        var entityPerson = repository.findPersonDetailsById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        List<Addresses> entityAddresses = addressesRepository.findAddressesDetailsById(id);

        Person personEntity = new Person();
        personEntity.setId(entityPerson.getId());
        personEntity.setBirthDate(entityPerson.getBirthDate());
        personEntity.setFullName(entityPerson.getFullName());
        personEntity.setAddresses(entityAddresses);

        return ModelMapperGeneric.parseObject(personEntity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {

        logger.info("Creating one person!");
        var entity = ModelMapperGeneric.parseObject(person, Person.class);
        var vo =  ModelMapperGeneric.parseObject(repository.save(entity), PersonVO.class);

        return vo;
    }

    public PersonVO update(PersonVO person) {

        logger.info("Updating one person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFullName(person.getFullName());
        entity.setBirthDate(person.getBirthDate());
        entity.setAddresses(person.getAddresses());

        var vo =  ModelMapperGeneric.parseObject(repository.save(entity), PersonVO.class);

        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
