package org.generation.NerdVault.repositories;

import org.generation.NerdVault.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import enums.ProdottoCategoria;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {
	
	Prodotto findByCategoria(ProdottoCategoria prodotto);

}
