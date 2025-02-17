package org.generation.NerdVault.repositories;

import java.util.List;

import org.generation.NerdVault.entities.Prodotto;
import org.generation.NerdVault.enums.ProdottoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {
	
	List<Prodotto> findByCategoria(ProdottoCategoria prodotto);
//	List<Prodotto> findByCategoria(String categoria);

}
