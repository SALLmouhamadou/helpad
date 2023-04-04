package fr.helpad.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.helpad.entity.Personne;
import fr.helpad.entity.Role;
import fr.helpad.repository.PersonneRepository;

@Service
@Transactional
public class PersonneServiceImpl implements PersonneService {
    @Autowired
    PersonneRepository personneRepository;
    @Autowired
    
    PasswordEncoder passwordEncoder;

    @Override
	public Optional<Personne> findByUsername(String user) {
		return personneRepository.findByUsername(user);
	}

	@Override
	public void save(Personne user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		personneRepository.save(user);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Personne user = findByUsername(username).
						orElseThrow(()-> new UsernameNotFoundException("inconnu"));
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			auths.add(new SimpleGrantedAuthority(role.getLibelle()));
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), auths);
	}

}
