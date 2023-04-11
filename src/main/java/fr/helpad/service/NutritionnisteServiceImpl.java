package fr.helpad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Nutritionniste;
import fr.helpad.entity.Personne;
import fr.helpad.repository.NutritionnisteRepository;

@Service
public class NutritionnisteServiceImpl implements NutritionnisteService {
	@Autowired
	NutritionnisteRepository nutritionnisteRepo;

	@Override
	public void sauveNutritionniste(Nutritionniste nutritionniste) {
		nutritionnisteRepo.save(nutritionniste);
		
	}

	@Override
	public List<Nutritionniste> findAll() {
		return (List<Nutritionniste>) nutritionnisteRepo.findAll();
	}

	@Override
	public Nutritionniste get(Long id) {
		return nutritionnisteRepo.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		nutritionnisteRepo.deleteById(id);
		
	}

	public NutritionnisteRepository getNutritionnisteRepo() {
		return nutritionnisteRepo;
	}

	public void setNutritionnisteRepo(NutritionnisteRepository nutritionnisteRepo) {
		this.nutritionnisteRepo = nutritionnisteRepo;
	}
	
	

}