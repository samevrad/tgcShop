package org.generation.NerdVault.ctrl;

import org.generation.NerdVault.dtos.UtenteSessione;
import org.generation.NerdVault.entities.Utente;
import org.generation.NerdVault.dtos.UtenteDto;
import org.generation.NerdVault.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class LoginCtrl {

	@Autowired
	private UtenteRepository utenteRepo;
	
	@PostMapping("/login")
	public ResponseEntity<UtenteDto> login(@RequestBody UtenteSessione datiAccessoUtente, HttpSession session) {
		
		Utente utente = utenteRepo.findByEmail(datiAccessoUtente.getEmail());
		
		if (utente != null && utente.getPassword().equals(datiAccessoUtente.getPassword())) {
			UtenteDto dto = new UtenteDto(utente);
			session.setAttribute("currentUser", dto);
			return ResponseEntity.ok(dto);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UtenteDto());
		}
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok("Logout effettuato con successo");
	}
	
}
