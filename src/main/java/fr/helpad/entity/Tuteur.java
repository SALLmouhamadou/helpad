package fr.helpad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPersonne")
public class Tuteur extends Personne{
	@Column(name="lien_parentee")
	private String lienParentee;
	
	
	
	
	
	
}
