package br.com.restpeoplemanagement.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.restpeoplemanagement.mapper.ModelMapperGeneric;
import br.com.restpeoplemanagement.model.Person;
import br.com.restpeoplemanagement.unittests.mapper.mocks.MockPerson;
import br.com.restpeoplemanagement.vo.PersonVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;


public class ModelConverterTest {

    MockPerson inputObject;
    List<Long> expectedAddresses = List.of(1L, 2L, 3L);

    @BeforeEach
    public void setUp() {
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToVOTest() {
        PersonVO output = ModelMapperGeneric.parseObject(inputObject.mockEntity(), PersonVO.class);

        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Gabriel Klein", output.getFullName());
        assertEquals(expectedAddresses, output.getAddresses());
        assertEquals(new Date(122, 0, 1), output.getBirthDate());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PersonVO> outputList = ModelMapperGeneric.parseListObjects(inputObject.mockEntityList(), PersonVO.class);
        PersonVO outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Gabriel Klein", outputZero.getFullName());
        assertEquals(expectedAddresses, outputZero.getAddresses());
        assertEquals(new Date(122, 0, 1), outputZero.getBirthDate());

        PersonVO outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Gabriel Klein", outputSeven.getFullName());
        assertEquals(expectedAddresses, outputSeven.getAddresses());
        assertEquals(new Date(122, 0, 1), outputSeven.getBirthDate());

        PersonVO outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Gabriel Klein", outputTwelve.getFullName());
        assertEquals(expectedAddresses, outputTwelve.getAddresses());
        assertEquals(new Date(122, 0, 1), outputTwelve.getBirthDate());
    }

    @Test
    public void parseVOToEntityTest() {
        Person output = ModelMapperGeneric.parseObject(inputObject.mockVO(), Person.class);

        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Gabriel Klein", output.getFullName());
        assertEquals(expectedAddresses, output.getAddresses());
        assertEquals(new Date(122, 0, 1), output.getBirthDate());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Person> outputList = ModelMapperGeneric.parseListObjects(inputObject.mockVOList(), Person.class);
        Person outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Gabriel Klein", outputZero.getFullName());
        assertEquals(expectedAddresses, outputZero.getAddresses());
        assertEquals(new Date(122, 0, 1), outputZero.getBirthDate());

        Person outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Gabriel Klein", outputSeven.getFullName());
        assertEquals(expectedAddresses, outputSeven.getAddresses());
        assertEquals(new Date(122, 0, 1), outputSeven.getBirthDate());

        Person outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Gabriel Klein", outputTwelve.getFullName());
        assertEquals(expectedAddresses, outputTwelve.getAddresses());
        assertEquals(new Date(122, 0, 1), outputTwelve.getBirthDate());
    }
}
