package br.com.restpeoplemanagement.vo;

import br.com.restpeoplemanagement.model.Addresses;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AddressesVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String cep;
    private String publicPlace;
    private String numberPhone;
    private String city;
    private String state;
    private Long personId;

    public AddressesVO() {}

    public AddressesVO(Long id, String cep, String publicPlace, String numberPhone, String city, String state, Long personId) {
        this.id = id;
        this.cep = cep;
        this.publicPlace = publicPlace;
        this.numberPhone = numberPhone;
        this.city = city;
        this.state = state;
        this.personId = personId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}