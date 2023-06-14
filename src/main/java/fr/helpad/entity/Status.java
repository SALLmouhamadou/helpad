package fr.helpad.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status implements Serializable{
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	private Long idStatus;
	private String libelle;
	
	
	public Status() {
		
	}
	public Status(String libelle) {
		super();
		this.libelle = libelle;
	}
	public Long getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	

}
