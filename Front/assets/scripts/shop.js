document.addEventListener("DOMContentLoaded", () => {

    console.log("Script caricato correttamente");
  
    function getProducts() {
      const apiUrl = `http://localhost:8080/api/prodotto`; // Corretto l'URL
  
      fetch(apiUrl)
        .then(response => {
          if (!response.ok) {
            throw new Error("Errore nel recupero dei prodotti");
          }
          console.log(response);
          return response.json(); // Parsea la risposta JSON
        })
        .then(prodotti => {
            console.log(prodotti);
          // Se ci sono prodotti, costruisci le cards
          if (prodotti.length > 0) {
            displayProductCards(prodotti);
          } else {
            document.getElementById("available-container").innerHTML = `
              <div class="alert alert-warning" role="alert">
                Nessun prodotto disponibile.
              </div>`;
          }
        })
        .catch(error => {
          console.error("Errore:", error);
          document.getElementById("available-container").innerHTML = `
            <div class="alert alert-danger" role="alert">
              Si è verificato un errore nel recupero dei prodotti.
            </div>`;
        });
    }
  
    // Funzione per creare le cards per ogni prodotto
    function displayProductCards(prodotti) {
      const available = document.getElementById("available-container");
      available.innerHTML = ""; // Pulisce il contenitore prima di aggiungere le nuove cards
  
      prodotti.forEach(prodotto => {
        // Creazione della card HTML per ogni prodotto
        const card = document.createElement("div");
        card.classList.add("card", "m-2","col-xs-12","col-md-5","col-xl-3");
  
        card.innerHTML = `
          <a href="product.html?id=${prodotto.prodottoId}"<img src="${prodotto.immagine}" class="card-img-top" alt="${prodotto.nome}"></a>
          <div class="card-body d-flex flex-column ">
            <h5 class="card-title">${prodotto.nome}</h5>
            <p class="card-text"><strong>€${prodotto.prezzo}</strong></p>
          </div>
        `;
  
        // VISIBILITA' AUMM AUMM
        /*if(prodotto.visibile==false){
            card.classList.add("d-none");
        }*/
        // Aggiungi la card al contenitore
        available.appendChild(card);
      });
    }
  
    // Chiamata alla funzione per caricare i prodotti
    getProducts(); // Richiama i prodotti al caricamento della pagina
  });
  


