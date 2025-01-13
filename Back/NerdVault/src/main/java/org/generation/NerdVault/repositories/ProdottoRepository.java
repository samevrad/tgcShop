package org.generation.NerdVault.repositories;

import org.generation.NerdVault.entities.Prodotto;
import org.generation.NerdVault.enums.ProdottoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {
	
	Prodotto findByCategoria(ProdottoCategoria prodotto);

}
