package org.generation.NerdVault.dtos;

import java.time.LocalDate;

import org.generation.NerdVault.entities.Utente;
import org.generation.NerdVault.enums.OrdineStato;

public class OrdineDto {
	
	private int ordineId;
	private Utente utente;
	private LocalDate dataOrdine;
	private LocalDate dataConsegna;
	private OrdineStato statoOrdine;
	private String indirizzoSpedizione;

	/*------------------ Getters / Setters ------------------*/
	
	public int getOrdineId() {
		return ordineId;
	}

	public void setOrdineId(int ordineId) {
		this.ordineId = ordineId;
	}

	public LocalDate getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(LocalDate dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public LocalDate getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(LocalDate dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public OrdineStato getStatoOrdine() {
		return statoOrdine;
	}

	public void setStatoOrdine(OrdineStato statoOrdine) {
		this.statoOrdine = statoOrdine;
	}

	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}

	public void setIndirizzoSpedizione(String indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public OrdineDto() {
	}

	public OrdineDto(int ordineId, LocalDate dataOrdine, LocalDate dataConsegna, OrdineStato statoOrdine,
			String indirizzoSpedizione) {
		this.ordineId = ordineId;
		this.dataOrdine = dataOrdine;
		this.dataConsegna = dataConsegna;
		this.statoOrdine = statoOrdine;
		this.indirizzoSpedizione = indirizzoSpedizione;
	}

}
