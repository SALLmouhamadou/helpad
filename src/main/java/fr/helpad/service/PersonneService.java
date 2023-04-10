package fr.helpad.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import fr.helpad.entity.Personne;

public interface PersonneService {
    public Optional<Personne> findByEmail(String user);
	public void save(Personne user);
	//public UserDetails loadUserByUsername(String username);
}
