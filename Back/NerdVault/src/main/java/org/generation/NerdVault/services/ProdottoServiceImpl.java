package org.generation.NerdVault.services;

import java.util.List;
import java.util.Optional;

import org.generation.NerdVault.dtos.ProdottoDto;
import org.generation.NerdVault.entities.Prodotto;
import org.generation.NerdVault.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enums.ProdottoCategoria;

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
	public Prodotto cercaPerCategoria(ProdottoCategoria categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdottoDto aggiungi(Prodotto prodotto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancellaProdotto(int id) {
		// TODO Auto-generated method stub
		
	}

}
