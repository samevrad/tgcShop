/* CREATE USER 'app_generation'@'localhost' IDENTIFIED BY 'generation_2022';
CREATE DATABASE IF NOT EXISTS `projectwork`;
GRANT ALL ON projectwork.* TO 'app_generation'@'localhost';
FLUSH PRIVILEGES; */

USE `projectwork`;

DROP TABLES utente, prodotto, ordine, ordine_dettaglio;

CREATE TABLE IF NOT EXISTS `utente` (
`utente_id` int NOT NULL AUTO_INCREMENT,
`nome` varchar(75) DEFAULT NULL,
`cognome` varchar(75) DEFAULT NULL,
`data_nascita` date DEFAULT NULL,
`email` varchar(50) NOT NULL UNIQUE,
`password` varchar(20) NOT NULL,
`ruolo` enum('ADMIN','UTENTE') NOT NULL, # (va anche bene varchar)
PRIMARY KEY (`utente_id`),
KEY `k_email` (`email`)
);

INSERT INTO `utente`(nome,cognome,data_nascita,email,password,ruolo)
VALUES ('Paolo','Rossi','1994-06-07','admin@email.com','admin','ADMIN'),
('Carlo','Verdi','2001-03-19','utente@email.com','utente','UTENTE');

CREATE TABLE IF NOT EXISTS `prodotto` (
	`prodotto_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`nome` VARCHAR(100) NOT NULL,
    `descrizione` TEXT DEFAULT NULL,
    `categoria` ENUM('NOVITA', 'PREVENDITA', 'GAMES', 'MERCH', 'ACCESSORI', 'SPECIALE', 'ALTRO') NOT NULL,
    `prezzo` DECIMAL(6,2) NOT NULL,
    `rimanenza` INT NOT NULL,
    `abilitato` BOOLEAN DEFAULT FALSE,
    `visibile` BOOLEAN DEFAULT TRUE,
    `immagine` VARCHAR(75) DEFAULT NULL,
    `inizio_prevendita` DATE DEFAULT NULL,
	`data_uscita` DATE DEFAULT NULL,
    `sconto_prevendita` DECIMAL(4,2) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `ordine` (
	`ordine_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `utente_id` INT NOT NULL,
    `data_ordine` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `data_consegna` DATETIME DEFAULT NULL,
	`stato_ordine` ENUM ('SPEDITO', 'CONSEGNATO', 'IN_LAVORAZIONE', 'CANCELLATO') DEFAULT 'IN_LAVORAZIONE',
    `indirizzo_spedizione` VARCHAR(75) NOT NULL,
    
    FOREIGN KEY (`utente_id`) REFERENCES utente(utente_id)
);

CREATE TABLE IF NOT EXISTS `ordine_dettaglio` (
	`ordine_id` INT NOT NULL,
    `prodotto_id` INT NOT NULL,
    `quantita` INT NOT NULL,
    `prezzo` DECIMAL(6,2) NOT NULL,
    
    FOREIGN KEY (`ordine_id`) REFERENCES ordine(ordine_id),
    FOREIGN KEY (`prodotto_id`) REFERENCES prodotto(prodotto_id)
);

INSERT INTO `prodotto` (nome, descrizione, categoria, prezzo, rimanenza, abilitato, visibile, immagine, inizio_prevendita, data_uscita, sconto_prevendita) VALUES
('Prodotto Novità 1', 'Descrizione del prodotto Novità 1', 'NOVITA', 29.99, 50, TRUE, TRUE, 'novita1.jpg', NULL, NULL, NULL),
('Prodotto Prevendita 1', 'Descrizione del prodotto in prevendita 1', 'PREVENDITA', 49.99, 100, TRUE, TRUE, 'prevendita1.jpg', '2025-02-01', '2025-03-01', 10.00),
('Gioco 1', 'Descrizione del gioco 1', 'GAMES', 59.99, 20, TRUE, TRUE, 'game1.jpg', NULL, NULL, NULL),
('Merchandise 1', 'Descrizione del merchandise 1', 'MERCH', 19.99, 30, FALSE, TRUE, 'merch1.jpg', NULL, NULL, NULL),
('Accessorio 1', 'Descrizione accessorio 1', 'ACCESSORI', 14.99, 15, TRUE, TRUE, NULL, NULL, NULL, NULL),
('Prodotto Speciale 1', 'Descrizione del prodotto speciale 1', 'SPECIALE', 99.99, 10, TRUE, TRUE, 'speciale1.jpg', NULL, NULL, NULL),
('Altro 1', 'Descrizione di un altro prodotto', 'ALTRO', 9.99, 80, FALSE, FALSE, NULL, NULL, NULL, NULL),
('Prodotto Prevendita 2', 'Descrizione del prodotto in prevendita 2', 'PREVENDITA', 79.99, 50, TRUE, TRUE, 'prevendita2.jpg', '2025-01-15', '2025-02-15', 15.00),
('Gioco 2', 'Descrizione del gioco 2', 'GAMES', 49.99, 25, TRUE, TRUE, 'game2.jpg', NULL, NULL, NULL),
('Merchandise 2', 'Descrizione del merchandise 2', 'MERCH', 24.99, 40, TRUE, TRUE, 'merch2.jpg', NULL, NULL, NULL),
('Merchandise 3', 'Descrizione del merchandise 3', 'MERCH', 34.99, 0, TRUE, TRUE, 'merch2.jpg', NULL, NULL, NULL);

select * from prodotto;

INSERT INTO `ordine` (utente_id, data_ordine, data_consegna, stato_ordine, indirizzo_spedizione) VALUES
(1, '2025-01-05 14:30:00', NULL, 'IN_LAVORAZIONE', 'Via Roma 1, Milano'),
(2, '2025-01-06 10:15:00', '2025-01-09 16:00:00', 'CONSEGNATO', 'Via Torino 12, Torino'),
(1, '2025-01-07 17:45:00', NULL, 'SPEDITO', 'Via Milano 24, Roma'),
(2, '2025-01-08 09:00:00', NULL, 'CANCELLATO', 'Via Napoli 8, Napoli'),
(1, '2025-01-09 11:20:00', NULL, 'IN_LAVORAZIONE', 'Via Venezia 3, Venezia'),
(2, '2025-01-09 13:30:00', NULL, 'SPEDITO', 'Via Firenze 5, Firenze'),
(1, '2025-01-10 08:45:00', NULL, 'IN_LAVORAZIONE', 'Via Genova 6, Genova'),
(2, '2025-01-10 12:00:00', NULL, 'SPEDITO', 'Via Bologna 9, Bologna'),
(1, '2025-01-11 15:30:00', NULL, 'SPEDITO', 'Via Trieste 4, Trieste'),
(2, '2025-01-12 18:00:00', NULL, 'IN_LAVORAZIONE', 'Via Palermo 7, Palermo');

INSERT INTO `ordine_dettaglio` (ordine_id, prodotto_id, quantita, prezzo) VALUES
(1, 1, 2, 29.99),
(1, 3, 1, 59.99),
(2, 2, 1, 49.99),
(2, 4, 3, 19.99),
(3, 6, 1, 99.99),
(3, 5, 2, 14.99),
(4, 7, 4, 9.99),
(5, 8, 1, 79.99),
(5, 9, 2, 49.99),
(6, 10, 3, 24.99),
(7, 1, 1, 29.99),
(7, 4, 2, 19.99),
(8, 5, 5, 14.99),
(8, 2, 1, 49.99),
(9, 3, 1, 59.99),
(9, 6, 1, 99.99),
(10, 7, 2, 9.99),
(10, 10, 1, 24.99);

SELECT * FROM prodotto;
SELECT * FROM ordine;
SELECT * FROM ordine_dettaglio;

-- Primi insert DB