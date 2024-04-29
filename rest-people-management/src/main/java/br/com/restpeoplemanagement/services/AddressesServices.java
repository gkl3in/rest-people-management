package br.com.restpeoplemanagement.services;

import br.com.restpeoplemanagement.exceptions.ResourceNotFoundException;
import br.com.restpeoplemanagement.mapper.ModelMapperGeneric;
import br.com.restpeoplemanagement.model.Addresses;
import br.com.restpeoplemanagement.repositories.AddressesRepository;
import br.com.restpeoplemanagement.vo.AddressesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class AddressesServices {

    private Logger logger = Logger.getLogger(AddressesServices.class.getName());

    @Autowired
    AddressesRepository repository;

    public List<AddressesVO> findAll() {
        logger.info("Finding all addresses!");

        return ModelMapperGeneric.parseListObjects(repository.findAll(), AddressesVO.class);
    }


    public AddressesVO findById(Long id) {
        logger.info("Finding one address!");
        var entityAddresses = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        Addresses addressesEntity = new Addresses();
        addressesEntity.setId(entityAddresses.getId());
        addressesEntity.setPersonId(entityAddresses.getPersonId());
        addressesEntity.setCep(entityAddresses.getCep());
        addressesEntity.setCity(entityAddresses.getCity());
        addressesEntity.setState(entityAddresses.getState());
        addressesEntity.setNumberPhone(entityAddresses.getNumberPhone());
        addressesEntity.setPublicPlace(addressesEntity.getPublicPlace());

        return ModelMapperGeneric.parseObject(addressesEntity, AddressesVO.class);
    }

    public AddressesVO create(AddressesVO address) {

        logger.info("Creating one address!");
        var entity = ModelMapperGeneric.parseObject(address, Addresses.class);
        var vo =  ModelMapperGeneric.parseObject(repository.save(entity), AddressesVO.class);

        return vo;
    }

    public AddressesVO update(AddressesVO address) throws ResourceNotFoundException {

        logger.info("Updating one address!");

        var entity = repository.findById(address.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setPersonId(address.getPersonId());
        entity.setCep(address.getCep());
        entity.setCity(address.getCity());
        entity.setState(address.getState());
        entity.setNumberPhone(address.getNumberPhone());
        entity.setPublicPlace(address.getPublicPlace());

        var vo =  ModelMapperGeneric.parseObject(repository.save(entity), AddressesVO.class);

        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one address!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
