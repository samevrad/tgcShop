package org.generation.NerdVault.services;

import java.util.List;

import org.generation.NerdVault.dtos.ProdottoDto;
import org.generation.NerdVault.entities.Prodotto;
import org.generation.NerdVault.enums.ProdottoCategoria;

public interface ProdottoService {

	public List<Prodotto> prendiTutti();

	public Prodotto cercaPerId(int id);
	public List<Prodotto> cercaPerCategoria(ProdottoCategoria categoria);

	public ProdottoDto aggiungi(Prodotto prodotto);
	
	public ProdottoDto aggiorna(Prodotto prodotto, Prodotto prodottoModificato);
	
	public void cancellaProdotto(int id);
	
	
}
