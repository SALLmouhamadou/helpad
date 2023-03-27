package fr.helpad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.helpad.entity.Medicament;

public interface MédicamentRepository extends JpaRepository<Medicament, Long> {
	
}
