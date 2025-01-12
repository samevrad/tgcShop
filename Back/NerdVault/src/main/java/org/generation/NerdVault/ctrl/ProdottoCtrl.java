package org.generation.NerdVault.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.generation.NerdVault.entities.Prodotto;

import org.generation.NerdVault.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Indica che la classe è un controller e che restituirà dati (ad esempio JSON) anziché una vista HTML.
// Si combina con @RequestMapping per specificare un prefisso URL.
@RestController
@RequestMapping("/api/prodotto")
public class ProdottoCtrl {
	
	@Autowired // Utilizzi l'iniezione delle dipendenze per ottenere un'istanza di UtenteService. Questo permette di delegare la logica di business a UtenteService (buona pratica per separare le preoccupazioni).
	ProdottoService prodottoService;

	@GetMapping // Gestisce una richiesta HTTP GET all'URL 
	public ResponseEntity<List<Prodotto>> getAll() { //  Utilizzi ResponseEntity per restituire una risposta HTTP. Questo ti permette di configurare il codice di stato HTTP e l'eventuale corpo della risposta (nel tuo caso, una lista di utenti).
		try {
			List<Prodotto> prodotti = prodottoService.prendiTutti();
			return ResponseEntity.ok(prodotti);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ArrayList<Prodotto>());
		}
	}
	
	@GetMapping("/{id}")
	// Questa annotazione mappa una richiesta GET con un parametro dinamico nell'URL, rappresentato da {id}. In questo caso, l'ID dell'utente viene estratto dal percorso dell'URL e passato al metodo tramite @PathVariable.
	public ResponseEntity<Prodotto> getById(@PathVariable int id) { // @PathVariable: Mappa il valore {id} dall'URL al parametro del metodo.
		try {
			Prodotto prodotto = prodottoService.cercaPerId(id); // UtenteService: È chiamato per ottenere i dati necessari.
			if (prodotto != null) {
				return ResponseEntity.ok(prodotto);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Prodotto());
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new Prodotto());
		}
	}
	
}
