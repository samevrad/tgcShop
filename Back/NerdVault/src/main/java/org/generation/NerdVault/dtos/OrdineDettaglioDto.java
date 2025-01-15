package org.generation.NerdVault.dtos;

import org.generation.NerdVault.entities.Ordine;
import org.generation.NerdVault.entities.Prodotto;

public class OrdineDettaglioDto {
	
	private Ordine ordine;

	private Prodotto prodotto;

	private int quantita;

	private double prezzo;

	/*------------------ Getters / Setters ------------------*/
	
	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public OrdineDettaglioDto() {
	}

	public OrdineDettaglioDto(Ordine ordine, Prodotto prodotto, int quantita, double prezzo) {
		this.ordine = ordine;
		this.prodotto = prodotto;
		this.quantita = quantita;
		this.prezzo = prezzo;
	}
	
}
