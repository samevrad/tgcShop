package org.generation.NerdVault.entities;

import java.sql.Date;

import org.generation.NerdVault.dtos.UtenteRuolo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "utente")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int utenteId;
	
	@Column(length = 75, nullable = true)
	private String nome;

	@Column(length = 75, nullable = true)
	private String cognome;

	@Column(nullable = true)
	private Date dataNascita;

	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	@JsonIgnore
	@Column(length = 20, nullable = false)
	private String password;
	
	@Column(columnDefinition = "ENUM('ADMIN', 'UTENTE')", nullable = false)
	@Enumerated(EnumType.STRING)
	private UtenteRuolo ruolo;

	public int getUtenteId() {
		return utenteId;
	}

	public void setUtenteId(int utenteId) {
		this.utenteId = utenteId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UtenteRuolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(UtenteRuolo ruolo) {
		this.ruolo = ruolo;
	}

}
