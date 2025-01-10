package org.generation.NerdVault.dtos;

import java.sql.Date;

public class UtenteDto {
	
	private int utenteId;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private String email;
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
	
	public UtenteRuolo getRuolo() {
		return ruolo;
	}
	
	public void setRuolo(UtenteRuolo ruolo) {
		this.ruolo = ruolo;
	}
	
	public UtenteDto() {
	}
	
	public UtenteDto(int utenteId, String nome, String cognome, Date dataNascita, String email,
			UtenteRuolo ruolo) {
		this.utenteId = utenteId;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.email = email;
		this.ruolo = ruolo;
	}
	
}
