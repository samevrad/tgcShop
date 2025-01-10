package org.generation.NerdVault.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.generation.NerdVault.entities.Utente;
import org.generation.NerdVault.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utente")
public class UtenteCtrl {
	
	@Autowired
	UtenteService utenteService;

	@GetMapping
	public ResponseEntity<List<Utente>> getAll() {
		try {
			List<Utente> utenti = utenteService.prendiTutti();
			return ResponseEntity.ok(utenti);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ArrayList<Utente>());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Utente> getById(@PathVariable int id) {
		try {
			Utente utente = utenteService.cercaPerId(id);
			if (utente != null) {
				return ResponseEntity.ok(utente);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Utente());
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new Utente());
		}
	}
	
}
