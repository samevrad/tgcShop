package org.generation.NerdVault.enums;

public enum OrdineStato {

	SPEDITO, 
	CONSEGNATO, 
	IN_LAVORAZIONE {
		@Override
		public String toString() {
			return "IN LAVORAZIONE";
		}
	}, 
	CANCELLATO
	
}
