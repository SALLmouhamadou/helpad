package fr.helpad.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.helpad.entity.Personne;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, Long> {

    @Query(value = "SELECT p FROM Personne p WHERE p.email = ?1 "
            + " AND p.password = ?2 ")
    public Personne findByEmail(String email, String password);
}
