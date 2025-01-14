package org.generation.NerdVault.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.generation.NerdVault.dtos.OrdineDto;
import org.generation.NerdVault.entities.Ordine;
import org.generation.NerdVault.services.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ordine")
public class OrdineCtrl {

	@Autowired
	OrdineService ordineService;
	
	@GetMapping
	public ResponseEntity<List<OrdineDto>> getAll() {
		try {
			List<OrdineDto> ordini = ordineService.prendiTutti();
			return ResponseEntity.ok(ordini);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ArrayList<OrdineDto>());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> creaOrdine(@RequestBody Ordine ordine) {
		try {
			OrdineDto dto = ordineService.aggiungi(ordine);
			return ResponseEntity.ok(dto);
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body("Errore inserimento dati, controllare le proprietà dell'oggetto");
			
		}	catch (Exception e) {
			return ResponseEntity.internalServerError().body(new OrdineDto());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificaOrdine(@PathVariable("id") int ordineId, Ordine ordine) {
		try {
			Ordine daModificare = ordineService.getById(ordineId);
			if (daModificare != null && daModificare.getOrdineId() == ordine.getOrdineId()) {
				OrdineDto dto = ordineService.aggiorna(daModificare, ordine);
				return ResponseEntity.ok(dto);
			} else {
				return ResponseEntity.badRequest().body("Errore: Ordine non trovato.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new Ordine());
		}
	}
	
	@GetMapping("/u/{utenteId}")
	public ResponseEntity<List<OrdineDto>> getAllForUtente(@PathVariable int utenteId) {
		try {
			List<OrdineDto> ordini = ordineService.prendiConUtenteId(utenteId);
			return ResponseEntity.ok(ordini);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ArrayList<OrdineDto>());
		}
	}
	
}
