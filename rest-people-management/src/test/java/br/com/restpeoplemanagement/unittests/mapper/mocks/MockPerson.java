package br.com.restpeoplemanagement.unittests.mapper.mocks;

import br.com.restpeoplemanagement.model.Person;
import br.com.restpeoplemanagement.v1.PersonVO;

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
        Person person = new Person();
        person.setId(Long.valueOf(number));
        person.setFullName("Gabriel Klein");
        person.setBirthDate(new Date(122, 0, 1));
        person.setAddresses(List.of(1L, 2L, 3L));
        return person;
    }

    public PersonVO mockVO(Integer number) {
        PersonVO person = new PersonVO();
        person.setId(Long.valueOf(number));
        person.setFullName("Gabriel Klein");
        person.setBirthDate(new Date(122, 0, 1));
        person.setAddresses(List.of(1L, 2L, 3L));
        return person;
    }

}
