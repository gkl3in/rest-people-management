package br.com.restpeoplemanagement.unittests.mapper.mocks;

import br.com.restpeoplemanagement.model.Addresses;
import br.com.restpeoplemanagement.model.Person;
import br.com.restpeoplemanagement.vo.PersonVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MockPerson {


    public Person mockEntity() {
        return mockEntity(0);
    }
    
    public PersonVO mockVO() {
        return mockVO(0);
    }
    
    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonVO> mockVOList() {
        List<PersonVO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }
    
    public Person mockEntity(Integer number) {
        List<Addresses> addresses = new ArrayList<Addresses>();

        Person person = new Person();
        person.setId(Long.valueOf(number));
        person.setFullName("Gabriel Klein");
        person.setBirthDate(new Date(122, 0, 1));
        person.setAddresses(addresses);
        return person;
    }

    public PersonVO mockVO(Integer number) {
        List<Addresses> addresses = new ArrayList<Addresses>();

        PersonVO person = new PersonVO();
        person.setId(Long.valueOf(number));
        person.setFullName("Gabriel Klein");
        person.setBirthDate(new Date(122, 0, 1));
        person.setAddresses(addresses);
        return person;
    }

    private Addresses createAddress(Long id, String cep, String publicPlace, String numberPhone, String city, String state, Long personId) {
        Addresses address = new Addresses();
        address.setId(id);
        address.setCep(cep);
        address.setPublicPlace(publicPlace);
        address.setNumberPhone(numberPhone);
        address.setCity(city);
        address.setState(state);
        address.setPersonId(personId);
        return address;
    }

}
