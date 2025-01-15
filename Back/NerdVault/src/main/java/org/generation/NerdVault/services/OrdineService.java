package org.generation.NerdVault.services;

import java.util.List;

import org.generation.NerdVault.dtos.OrdineDto;
import org.generation.NerdVault.entities.Ordine;
//import org.generation.NerdVault.entities.Ordine;

public interface OrdineService {

//	List<Ordine> prendiTutti();
	List<OrdineDto> prendiTutti();
	List<OrdineDto> prendiConUtenteId(int utenteId);
	OrdineDto prendiConId(int id);
	
	OrdineDto aggiungi(Ordine ordine);
	Ordine getById(int ordineId);
	OrdineDto aggiorna(Ordine daModificare, Ordine ordine);
}
