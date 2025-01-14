package org.generation.NerdVault.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.generation.NerdVault.dtos.OrdineDto;
import org.generation.NerdVault.services.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
