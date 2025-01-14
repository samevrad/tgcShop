package org.generation.NerdVault.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomProperties {

	// PERCORSO CARTELLA PER SALVATAGGIO IMMAGINI
	public static final String IMG_FOLDER_PATH = "../../Front/assets/img/";
	public static final String IMG_URL_PATH = "assets/img";
	
	public static final String DEFAULT_IMG_PATH = "assets/img/non-disponibile.jpg";
	
}
