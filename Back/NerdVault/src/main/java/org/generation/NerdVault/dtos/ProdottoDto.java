package org.generation.NerdVault.dtos;

import java.sql.Date; // è specifico per JDBC e database relazionali, nel contesto di un DTO (che non mappa direttamente il database) potrebbe essere sostituito con un tipo di data più generico come LocalDate
import java.time.LocalDate;

import org.generation.NerdVault.enums.ProdottoCategoria;


// Il costruttore vuoto (default constructor) è essenziale per molte librerie che deserializzano oggetti JSON.
// Il costruttore parametrizzato è utile per creare un DTO direttamente con tutti i campi impostati.
public class ProdottoDto {
	
	private int prodottoId;
	private String nome;
	private String descrizione;
	private ProdottoCategoria categoria;
	private double prezzo;
	private int rimanenza;
	private boolean abilitato;
	private String immagine;
	private LocalDate inizioPrevendita;
	private LocalDate dataUscita;
	private double scontoPrevendita;
	
	
	
	
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getProdottoId() {
		return prodottoId;
	}
	public void setProdottoId(int prodottoId) {
		this.prodottoId = prodottoId;
	}
	public ProdottoCategoria getCategoria() {
		return categoria;
	}
	public void setCategoria(ProdottoCategoria categoria) {
		this.categoria = categoria;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public int getRimanenza() {
		return rimanenza;
	}
	public void setRimanenza(int rimanenza) {
		this.rimanenza = rimanenza;
	}
	public boolean isAbilitato() {
		return abilitato;
	}
	public void setAbilitato(boolean abilitato) {
		this.abilitato = abilitato;
	}
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	public LocalDate getInizioPrevendita() {
		return inizioPrevendita;
	}
	public void setInizioPrevendita(LocalDate inizioPrevendita) {
		this.inizioPrevendita = inizioPrevendita;
	}
	public LocalDate getDataUscita() {
		return dataUscita;
	}
	public void setDataUscita(LocalDate dataUscita) {
		this.dataUscita = dataUscita;
	}
	public double getScontoPrevendita() {
		return scontoPrevendita;
	}
	public void setScontoPrevendita(double scontoPrevendita) {
		this.scontoPrevendita = scontoPrevendita;
	}
	
	
	public ProdottoDto() {
	}
	
	
	
	public ProdottoDto(int prodottoId, String nome, String descrizione, ProdottoCategoria categoria, double prezzo,
			int rimanenza, boolean abilitato, String immagine, LocalDate inizioPrevendita, LocalDate dataUscita,
			double scontoPrevendita) {
		super();
		this.prodottoId = prodottoId;
		this.nome = nome;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.prezzo = prezzo;
		this.rimanenza = rimanenza;
		this.abilitato = abilitato;
		this.immagine = immagine;
		this.inizioPrevendita = inizioPrevendita;
		this.dataUscita = dataUscita;
		this.scontoPrevendita = scontoPrevendita;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}