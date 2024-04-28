package br.com.restpeoplemanagement.services;

import br.com.restpeoplemanagement.exceptions.ResourceNotFoundException;
import br.com.restpeoplemanagement.mapper.ModelMapperGeneric;
import br.com.restpeoplemanagement.model.Person;
import br.com.restpeoplemanagement.repositories.PersonRepository;
import br.com.restpeoplemanagement.v1.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll() {

        logger.info("Finding all people!");

        return ModelMapperGeneric.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return ModelMapperGeneric.parseObject(entity, PersonVO.class);
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
