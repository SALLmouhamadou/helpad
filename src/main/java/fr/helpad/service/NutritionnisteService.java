package fr.helpad.service;

import java.util.List;

import fr.helpad.entity.Nutritionniste;

public interface NutritionnisteService {
	public List<Nutritionniste> findAll();
	public void sauveNutritionniste(Nutritionniste nutritionniste);
	public Nutritionniste get(Long id);
	public void delete(Long id);
	public Nutritionniste login(String email, String password);
}
