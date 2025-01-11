# tgcShop
Project Work JAITA131

## Appunti sul setup del progetto
La creazione di entities, DTO, repository (JPA) e service segue il principio di separazione dei compiti (separation of concerns) ed è fondamentale per un'architettura ben progettata, scalabile e manutenibile in un'applicazione Spring/Maven.

## Entities (Entità)
Le entità rappresentano gli oggetti che mappano le tabelle del database.
* Perché creare le entità?
Servono per comunicare con il database usando JPA (Java Persistence API).
Mappano i dati da una tabella a un oggetto Java, rendendo il codice leggibile e manutenibile.
Sono il cuore del modello dati dell'applicazione.
* Criteri per creare le entità:
Ogni tabella del database dovrebbe avere una classe Entity corrispondente.
Usa annotazioni JPA come @Entity, @Table, @Id, @Column per definire il mapping.
I campi della classe mappano le colonne della tabella.
Se ci sono relazioni tra tabelle (ad esempio, una tabella Utente e una tabella Ordini), usa annotazioni JPA come @OneToMany, @ManyToOne, ecc.
Le entità devono contenere solo campi, costruttori, getter/setter e annotazioni.
Non includere logica o operazioni complesse.

## DTO (Data Transfer Object)
I DTO sono oggetti semplici che servono per trasferire dati tra i vari livelli dell'applicazione.
Perché creare i DTO?
Separano i dati esposti all'utente (frontend o API) dai dati interni del database (entità).
Prevengono l'esposizione di informazioni sensibili (es. password).
Consentono di strutturare i dati in base alle esigenze del frontend/API senza modificare l'entità.
* Criteri per creare i DTO:
Crea DTO in base a ciò che il frontend/API ha bisogno di ricevere o inviare.
Ad esempio, un DTO per i dettagli utente potrebbe escludere la password
Un DTO dovrebbe contenere solo i campi strettamente necessari per il trasferimento.
Fornisci metodi di conversione tra entità e DTO


## JPA Repository
Il repository è l'interfaccia che permette di interagire con il database.
* Perché creare il repository?
Fornisce metodi predefiniti per operazioni comuni sul database (es. salvare, aggiornare, eliminare, cercare).
Grazie a Spring Data JPA, non devi scrivere manualmente query SQL per la maggior parte delle operazioni.
* Criteri per creare il repository:
- Crea un'interfaccia per ogni entità
- Uso di metodi predefiniti:
JpaRepository offre metodi pronti per salvare, trovare, eliminare, ecc. --> save(), findById(), deleteById(), ecc.
- Definisci metodi personalizzati solo se necessario:

## Service
Il service è la logica intermedia tra il controller e il repository.
* Perché creare il service?
Centralizza la logica di business, mantenendo il controller e il repository semplici.
Consente la riusabilità del codice.
Fornisce un livello in cui applicare validazioni, calcoli o regole di business.
* Criteri per creare il service:
- Per ogni entità, crea una classe di service (es. UtenteService).
- Il service dovrebbe interagire con il repository per eseguire operazioni sul database.
* Regole di business:
Implementa nel service la logica necessaria, come:
Validazioni personalizzate.
Operazioni complesse sui dati.


## Schema generale del flusso
* Entità: Rappresenta i dati nel database.
* Controller: Riceve la richiesta dall'utente (API o Frontend).
* Service: Elabora la richiesta e applica la logica di business.
* Repository: Interagisce con il database per leggere/scrivere dati.
* DTO: Converte i dati da/verso il formato richiesto dall'utente.



