package org.generation.NerdVault.entities;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class OrdineDettaglio {

	@ManyToOne
	@JoinColumn(name = "ordine_id", nullable = false)
	private Ordine ordine;
	
	@ManyToOne
	@JoinColumn(name = "prodotto_id", nullable = false)
	private Prodotto prodotto;
	
	@Column(nullable = false)
	private int quantita;
	
	@Column(nullable = false)
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

	
}
