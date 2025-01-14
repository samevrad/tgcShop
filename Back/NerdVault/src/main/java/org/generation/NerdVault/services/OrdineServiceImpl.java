package org.generation.NerdVault.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.generation.NerdVault.dtos.OrdineDto;
import org.generation.NerdVault.entities.Ordine;
import org.generation.NerdVault.enums.OrdineStato;
import org.generation.NerdVault.repositories.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdineServiceImpl implements OrdineService{
	
	@Autowired
	public OrdineRepository ordineRepo;
	@Autowired
	public UtenteService utenteService;

	@Override
	public List<OrdineDto> prendiTutti() {
		ArrayList<OrdineDto> dtos = new ArrayList<OrdineDto>();
		List<Ordine> ordini = ordineRepo.findAll();
		ordini.forEach(ordine -> dtos.add(this.toOrdineDto(ordine)));
		return dtos;
	}

	@Override
	public OrdineDto prendiConId(int id) {
		Optional<Ordine> ordine = ordineRepo.findById(id);
		if(ordine.isPresent()) {
			return this.toOrdineDto(ordine.get());
		} else {
			return new OrdineDto();
		}
	}
	
	@Override
	public List<OrdineDto> prendiConUtenteId(int utenteId) {
//		Optional<List<Ordine>> ordini = ordineRepo.findByUtenteId(utenteId);
		ArrayList<OrdineDto> dtos = new ArrayList<OrdineDto>();
		List<OrdineDto> result = prendiTutti();
		
		result.forEach(ordine -> {
					if (ordine.getUtente().getUtenteId() == utenteId) {
						dtos.add(ordine);
					}
		});
		
		return dtos;
		
//		if (ordini.isPresent()) {
//			return ordini.get();
//		} else {
//			return new ArrayList<Ordine>();
//		}
	}
	
	public OrdineDto nuovoOrdine(Ordine ordine) {
		ordineRepo.save(ordine);
		return this.toOrdineDto(ordine);
	}
	
	public void cancellaOrdine(int id) {
		Optional<Ordine> ordine = ordineRepo.findById(id);
		if(ordine.isPresent()) {
			ordine.get().setStatoOrdine(OrdineStato.CANCELLATO);
		}
	}
	
	private OrdineDto toOrdineDto(Ordine ordine) {
		OrdineDto dto = new OrdineDto(
				ordine.getOrdineId(),
				ordine.getDataOrdine(),
				ordine.getDataConsegna(),
				ordine.getStatoOrdine(),
				ordine.getIndirizzoSpedizione()
				);
		dto.setUtente(ordine.getUtente());
		
		return dto;
	}

}
