package br.com.restpeoplemanagement.repositories;
import br.com.restpeoplemanagement.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}