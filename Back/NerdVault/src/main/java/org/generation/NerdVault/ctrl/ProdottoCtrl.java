package org.generation.NerdVault.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.generation.NerdVault.dtos.ProdottoDto;
import org.generation.NerdVault.entities.Prodotto;
import org.generation.NerdVault.enums.ProdottoCategoria;
import org.generation.NerdVault.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Indica che la classe è un controller e che restituirà dati (ad esempio JSON) anziché una vista HTML.
// Si combina con @RequestMapping per specificare un prefisso URL.
@RestController
@RequestMapping("/api/prodotto")
public class ProdottoCtrl {
	
	@Autowired // Utilizzi l'iniezione delle dipendenze per ottenere un'istanza di ProdottoService. Questo permette di delegare la logica di business a ProdottoService (buona pratica per separare le preoccupazioni).
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
	
	@PostMapping
	// Mappiamo una richiesta POST sull'URL base
	public ResponseEntity<?> addOne(@RequestBody Prodotto prodotto) {	// @RequestBody: richiede un JSON che rappresenta un prodotto da inserire all'interno del DB
		
		// DA IMPLEMENTARE CONTROLLO ADMIN
		
		try {
			ProdottoDto dto = prodottoService.aggiungi(prodotto);
			return ResponseEntity.ok(dto);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body("Errore inserimento dati, controllare le proprietà dell'oggetto");
		}	catch (Exception e) {

			return ResponseEntity.internalServerError().body(new ProdottoDto());
		}
	}
	
	@GetMapping("/{id}")
	// Questa annotazione mappa una richiesta GET con un parametro dinamico nell'URL, rappresentato da {id}. In questo caso, l'ID dell'prodotto viene estratto dal percorso dell'URL e passato al metodo tramite @PathVariable.
	public ResponseEntity<Prodotto> getById(@PathVariable int id) { // @PathVariable: Mappa il valore {id} dall'URL al parametro del metodo.
		try {
			Prodotto prodotto = prodottoService.cercaPerId(id); // ProdottoService: È chiamato per ottenere i dati necessari.
			if (prodotto != null) {
				return ResponseEntity.ok(prodotto);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Prodotto());
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new Prodotto());
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateOne(@RequestBody Prodotto prodotto) {
		// DA IMPLEMENTARE CONTROLLO ADMIN
		try {
			Prodotto trovato = prodottoService.cercaPerId(prodotto.getProdottoId());
			
			if (trovato != null && prodotto.getProdottoId() == trovato.getProdottoId()) {
				ProdottoDto dto = prodottoService.aggiorna(trovato, prodotto);
				return ResponseEntity.ok(dto);
				
			} else {
				return ResponseEntity.badRequest().body("Errore: prodotto non trovato");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new Prodotto()); // 500
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		// DA IMPLEMENTARE CONTROLLO ADMIN
		try {
			Prodotto trovato = prodottoService.cercaPerId(id);
			if (trovato != null) {
				prodottoService.cancellaProdotto(id);
				return ResponseEntity.ok("Cancellato prodotto id = " + id);
			}
			return ResponseEntity.badRequest().body("Errore: prodotto non trovato");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ProdottoDto()); // 500
		}
	}
	
	@GetMapping("/categoria/{c}")
	// Mappiamo una richiesta GET con parametro dinamico {c} che sarà una stringa che rappresenta la categoria
	public ResponseEntity<List<Prodotto>> getByCategoria(@PathVariable String c) {
		try {
			String cat = c.toUpperCase().trim();
			// Trasformo la stringa in maiuscolo così che il valore eguagli quello dell'enum ProdottoCategoria
			List<Prodotto> prodotti = prodottoService.cercaPerCategoria(ProdottoCategoria.valueOf(cat));
			// Se il valore della stringa fa parte dell'enum, procede ritornando la lista dei prodotti appartenenti a tale categoria.
			
			return ResponseEntity.ok(prodotti);
			
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ArrayList<Prodotto>());
		}
		
	}
	
}
