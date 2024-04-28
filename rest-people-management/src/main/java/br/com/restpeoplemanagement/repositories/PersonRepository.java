package br.com.restpeoplemanagement.repositories;

import br.com.restpeoplemanagement.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(value = "SELECT p.id, p.dt_nascimento, p.ds_full_name " +
            "FROM person p " +
            "WHERE p.id = :person_id", nativeQuery = true)
    Optional<Person> findPersonDetailsById(Long person_id);

    @Query(value = "SELECT p.id, p.dt_nascimento, p.ds_full_name FROM person p", nativeQuery = true)
    List<Person> findAllPersonDetails();

}