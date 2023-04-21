package fr.helpad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;

import fr.helpad.entity.Medicament;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
	@Query(value = "SELECT c FROM Medicament c WHERE c.nom LIKE '%' || :nom || '%'")
	public List<Medicament> getByNom(String nom);
=======

import fr.helpad.entity.Medicament;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
	public List<Medicament> findByNomContaining(String nom);
>>>>>>> refs/remotes/origin/main
}
