package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

public interface BasicBusiness<T> {
	public T sauvegarder(T entity);
	
	public List<T> listerTout();
	
	public T get(Long id) throws NoSuchElementException;
	
	public void supprimer(Long id) throws IllegalArgumentException;
}
