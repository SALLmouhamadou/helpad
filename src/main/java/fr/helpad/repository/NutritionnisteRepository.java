package fr.helpad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.helpad.entity.Nutritionniste;
@Repository
public interface NutritionnisteRepository extends JpaRepository<Nutritionniste, Long> {
	public Nutritionniste findByEmail(String email);
	
}
