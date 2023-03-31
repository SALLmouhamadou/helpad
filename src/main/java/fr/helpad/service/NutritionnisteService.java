package fr.helpad.service;

import java.util.List;

import fr.helpad.entity.Nutritionniste;

public interface NutritionnisteService {

	public void sauveNutritionniste(Nutritionniste nutritionniste );
    public List<Nutritionniste> findAll();
    public Nutritionniste get(Long id);
    public void delete(Long id);
    public Nutritionniste login(String email, String password);
	
}
