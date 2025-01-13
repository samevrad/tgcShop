package org.generation.NerdVault.services;

import java.util.List;
import java.util.Optional;

import org.generation.NerdVault.dtos.ProdottoDto;
import org.generation.NerdVault.entities.Prodotto;
import org.generation.NerdVault.enums.ProdottoCategoria;
import org.generation.NerdVault.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoServiceImpl implements ProdottoService {
	
	@Autowired
	ProdottoRepository prodottoRepo;

	@Override
	public List<Prodotto> prendiTutti() {
		List<Prodotto> prodotti = prodottoRepo.findAll();
		
		return prodotti;
	}

	@Override
	public Prodotto cercaPerId(int id) {
		Optional<Prodotto> opt = prodottoRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		} else return null;
	}

	@Override
	public List<Prodotto> cercaPerCategoria(ProdottoCategoria categoria) {
//	public List<Prodotto> cercaPerCategoria(String categoria) {
		List<Prodotto> p = prodottoRepo.findByCategoria(categoria);
		return p;
	}

	@Override
	public ProdottoDto aggiungi(Prodotto prodotto) {
		Prodotto prod = prodottoRepo.save(prodotto);
		return this.toProdottoDto(prod);
	}

	@Override
	public void cancellaProdotto(int id) {
		prodottoRepo.deleteById(id);
	}

	@Override
	public ProdottoDto aggiorna(Prodotto prodotto, Prodotto modifiche) {
		prodotto.setNome(modifiche.getNome());
		prodotto.setDescrizione(modifiche.getDescrizione());
		prodotto.setCategoria(modifiche.getCategoria());
		prodotto.setPrezzo(modifiche.getPrezzo());
		prodotto.setRimanenza(modifiche.getRimanenza());
		prodotto.setAbilitato(modifiche.isAbilitato());
		prodotto.setVisibile(modifiche.isVisibile());
		prodotto.setImmagine(modifiche.getImmagine());
		prodotto.setInizioPrevendita(modifiche.getInizioPrevendita());
		prodotto.setDataUscita(modifiche.getDataUscita());
		prodotto.setScontoPrevendita(modifiche.getScontoPrevendita());
		
		prodottoRepo.save(prodotto);
		
		return this.toProdottoDto(prodotto);
	}
	
	private ProdottoDto toProdottoDto(Prodotto prodotto) {
		ProdottoDto dto = new ProdottoDto(
				prodotto.getProdottoId(), 
				prodotto.getNome(), 
				prodotto.getDescrizione(), 
				prodotto.getCategoria(), 
				prodotto.getPrezzo(), 
				prodotto.getRimanenza(), 
				prodotto.isAbilitato(), 
				prodotto.isVisibile(),
				prodotto.getImmagine(), 
				prodotto.getInizioPrevendita(), 
				prodotto.getDataUscita(), 
				prodotto.getScontoPrevendita()
				);
		return dto;
	}

}
