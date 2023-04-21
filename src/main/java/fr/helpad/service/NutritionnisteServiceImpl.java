package fr.helpad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.helpad.entity.Infirmiere;
import fr.helpad.entity.Nutritionniste;
import fr.helpad.entity.Personne;
import fr.helpad.repository.NutritionnisteRepository;

public class NutritionnisteServiceImpl implements NutritionnisteService {
	@Autowired
	NutritionnisteRepository nutritionnisteRepo;

	
//	@Override
//	public List<Nutritionniste> findAll() {
//		return (List<Nutritionniste>) nutritionnisteRepo.findAll();
//	}

	@Override
	public Nutritionniste get(Long id) {
		return nutritionnisteRepo.findById(id).get();
	}

//	@Override
//	public void delete(Long id) {
//		nutritionnisteRepo.deleteById(id);		
//	}

//	@Override
//	public Nutritionniste login(String email, String password) {
//		return nutritionnisteRepo.findByEmail(email);
//	}

	public NutritionnisteRepository getNutritionnisteRepo() {
		return nutritionnisteRepo;
	}

	public void setNutritionnisteRepo(NutritionnisteRepository nutritionnisteRepo) {
		this.nutritionnisteRepo = nutritionnisteRepo;
	}
	// UNIMPLEMENTED METHODS

	@Override
	public Nutritionniste sauvegarder(Nutritionniste nutritionniste) {
		return nutritionnisteRepo.save(nutritionniste);
	}

	@Override
	public List<Nutritionniste> listerTout() {
		return (List<Nutritionniste>) nutritionnisteRepo.findAll();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		nutritionnisteRepo.deleteById(id);
	}
	
	

}
