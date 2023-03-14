package fr.helpad.repository;

import org.springframework.data.repository.CrudRepository;

import fr.helpad.entity.Employe;

public interface EmployeRepository extends CrudRepository<Employe, Long>{

}
