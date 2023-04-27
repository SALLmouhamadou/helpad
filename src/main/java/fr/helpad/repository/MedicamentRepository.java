package fr.helpad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.helpad.entity.Medicament;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
	public List<Medicament> findByNomContaining(String nom);
}
