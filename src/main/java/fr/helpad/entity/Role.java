package fr.helpad.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role", length = 20)
	private Long idRole;
	
	@Column(nullable = false)
	private String libelle;
	
	@ManyToMany(mappedBy = "roles")
	private Set<Personne> users;
	
	public Role() {
		users = new HashSet<Personne>();
	}

	public Long getId() {
		return idRole;
	}

	public void setId(Long idRole) {
		this.idRole = idRole;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Set<Personne> getUsers() {
		return users;
	}

	public void setUsers(Set<Personne> users) {
		this.users = users;
	}
	
	public void addUser(Personne user) {
		this.users.add(user);
	}
}
