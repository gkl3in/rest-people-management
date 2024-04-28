package br.com.restpeoplemanagement.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PersonVO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;
    private String fullName;
    private Date birthDate;
    private List<Long> addresses;


    public PersonVO() {}

    public PersonVO(Long id, String fullName, Date birthDate, List<Long> addresses) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Long> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Long> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonVO personVO = (PersonVO) o;
        return Objects.equals(id, personVO.id) && Objects.equals(fullName, personVO.fullName) && Objects.equals(birthDate, personVO.birthDate) && Objects.equals(addresses, personVO.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, birthDate, addresses);
    }
}