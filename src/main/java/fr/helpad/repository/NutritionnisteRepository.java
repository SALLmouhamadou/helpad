package fr.helpad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.helpad.entity.Nutritionniste;

public interface NutritionnisteRepository extends JpaRepository<Nutritionniste, Long> {
	public Nutritionniste findByEmail(String email);
	
}
