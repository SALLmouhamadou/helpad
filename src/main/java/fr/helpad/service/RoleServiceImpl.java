package fr.helpad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Role;
import fr.helpad.repository.RoleRepository;

@Service
public class RoleServiceImpl {
	@Autowired
	RoleRepository repository;
	
	public List<Role> getRoles(){
		return (List<Role>) repository.findAll();
	}
}
