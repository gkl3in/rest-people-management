package br.com.restpeoplemanagement.repositories;

import br.com.restpeoplemanagement.model.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressesRepository extends JpaRepository<Addresses, Long> {
    @Query(value = "SELECT b.id, b.ds_cep, b.ds_city, b.ds_phone, b.person_id, b.ds_logradouro, b.ds_state " +
            "FROM person_address b " +
            "WHERE b.person_id = :person_id", nativeQuery = true)
    List<Addresses> findAddressesDetailsById(Long person_id);
}