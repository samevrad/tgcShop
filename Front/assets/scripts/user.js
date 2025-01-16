document.addEventListener("DOMContentLoaded", () => {

console.log("Script loaded correctly");

// Definizione dei valori per lo stato degli ordini
const SPEDITO = "SPEDITO";
const IN_LAVORAZIONE = "IN_LAVORAZIONE";
const CONSEGNATO = "CONSEGNATO";
const CANCELLATO = "CANCELLATO";

function fetchOrders() {
    const apiUrl = 'http://localhost:8080/api/ordine';

    fetch(apiUrl)
    .then(response => {
        if (!response.ok) {
            throw new Error("Errore nel recupero degli ordini");
    }
    return response.json();
})
.then(data=>{
    ordini = data; // memorizza gli ordini
    console.log(ordini);

    const speditiOrdini = ordini.filter(ordine => ordine.statoOrdine === SPEDITO );
    const lavorazioneOrdini = ordini.filter(ordine => ordine.statoOrdine === IN_LAVORAZIONE );
    const consegnatiOrdini = ordini.filter(ordine => ordine.statoOrdine === CONSEGNATO );
    const annullatiOrdini = ordini.filter(ordine => ordine.statoOrdine === CANCELLATO );

    // controllo ordini
    console.log("ordini spediti: ", speditiOrdini);
    console.log("ordini in lavorazione: ", lavorazioneOrdini);
    console.log("ordini consegnati: ", consegnatiOrdini);
    console.log("ordini annullati: ", annullatiOrdini);

    /*const numeroOrdini = document.getElementById("valore-ordini");
    numeroOrdini.textContent = `${ordini.length} ordini`;*/

    /*inserire id containers
    displayOrders(speditiOrdini,);
    displayOrders(lavorazioneOrdini,);
    displayOrders(consegnatiOrdini,);
    displayOrders(annullatiOrdini,);
    */

    filterAndDisplayOrders(ordini);

})
.catch(error => {
    console.error("Errore:", error);
    document.getElementById("ordini-container").innerHTML = `
      <div class="alert alert-danger" role="alert">
        Si è verificato un errore nel recupero dei prodotti.
      </div>`;
  });

}//CHIUSURA FETCH ORDERS

function createOrdersRow(ordini) {
    const container = document.getElementById("ordini-container");
    // Assicurati che il contenitore sia svuotato prima di aggiungere nuove righe
    container.innerHTML = "";
    console.log(("Rendering table in ", container, " with ", ordini.length, " rows"));

    ordini.forEach(ordine => {
        //creazione row per ogni ordine
        const row = document.createElement('tr');
        row.setAttribute('ordine-ID', ordine.ordineId);

        row.innerHTML = `
            <td>${ordine.dataConsegna || "Non disponibile"}</td>
            <td>${ordine.dataOrdine}</td>
            <td>${ordine.indirizzoSpedizione}</td>
            <td>${ordine.statoOrdine}</td>
            <td>
                <button class="btn btn-warning cancel-order-btn">Annulla</button>
                <button class="btn btn-danger delete-order-btn">Elimina</button>
            </td>
        `;

        // Event listeners per i pulsanti
        row.querySelector('.cancel-order-btn').addEventListener('click', () => {
            console.log(`Annulla ordine con ID ${ordine.ordineId}`);
            // Implementa la logica per annullare l'ordine
        });

        row.querySelector('.delete-order-btn').addEventListener('click', () => {
            console.log(`Elimina ordine con ID ${ordine.ordineId}`);
            // Implementa la logica per eliminare l'ordine
        });

        // Aggiungi la riga al contenitore
        container.appendChild(row);
    });
}

 // funzione per creare la tabella degli ordini
 function filterAndDisplayOrders(ordini){

    //ottieni lo stato dei filtri
    const speditiChecked =document.getElementById("CheckSpediti").checked;
    const lavorazioneChecked =document.getElementById("CheckLavorazione").checked;
    const consegnatiChecked =document.getElementById("CheckConsegnati").checked;
    const annullatiChecked =document.getElementById("CheckAnnullati").checked;

    //crea una lista dei filtri selezionati
    const selectedFilters = [];
    if(speditiChecked) selectedFilters.push(SPEDITO);
    if (lavorazioneChecked) selectedFilters.push(IN_LAVORAZIONE);
    if (consegnatiChecked) selectedFilters.push(CONSEGNATO);
    if (annullatiChecked) selectedFilters.push(CANCELLATO);
    console.log("filtriselezionati: ",selectedFilters);

    // Filtra gli ordini in base alle categorie selezionate
    const filteredOrders = ordini.filter(ordine => {
    // Se non è selezionato nessun filtro, restituisce tutti gli ordini
    if (selectedFilters.length === 0) {
      return true
    }
    // Verifica se la categoria del prodotto è nelle categorie selezionate
    return selectedFilters.includes(ordine.statoOrdine);
    });

       //controlli di validità
       console.log("filtri selezionati:" + speditiChecked, lavorazioneChecked,consegnatiChecked,annullatiChecked);
       console.log("Ordini filtrati:", filteredOrders);

    //stampa degli ordini
    createOrdersRow(filteredOrders);
}//chiusura funzione filterAndDisplayOrders

// Aggiungi un listener per le checkbox
const checkboxes = document.querySelectorAll(".form-check-input");
checkboxes.forEach(checkbox => {
  checkbox.addEventListener("change", () => {
    // Dopo ogni cambio, filtra i prodotti disponibili
    filterAndDisplayOrders(ordini);
  });
});

fetchOrders();
})


  
