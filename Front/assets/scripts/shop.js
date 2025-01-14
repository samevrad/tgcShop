document.addEventListener("DOMContentLoaded", () => {

  console.log("Script caricato correttamente");

  let prodotti = []; // Variabile per memorizzare i prodotti

  function getProducts() {
    const apiUrl = `http://localhost:8080/api/prodotto`; // Corretto l'URL

    fetch(apiUrl)
      .then(response => {
        if (!response.ok) {
          throw new Error("Errore nel recupero dei prodotti");
        }
        return response.json(); // Parsea la risposta JSON
      })
      .then(data => {
        prodotti = data; // Memorizza i prodotti
        console.log(prodotti);
        const presaleProducts = prodotti.filter(p => p.inizioPrevendita != null);
        const availableProducts = prodotti.filter(p => p.inizioPrevendita === null);

        const numeroProdotti = document.getElementById("valore-disponibili");
        numeroProdotti.textContent = `${prodotti.length} prodotti`;
        // Verifica se ci sono effettivamente prodotti in prevendita e disponibili
        console.log("Prodotti in prevendita:", presaleProducts);
        console.log("Prodotti disponibili:", availableProducts);
        
        // Visualizza i prodotti nei rispettivi contenitori
        displayProductCards(presaleProducts, "presale-container");
        filterAndDisplayAvailableProducts(availableProducts);
      })
      .catch(error => {
        console.error("Errore:", error);
        document.getElementById("available-container").innerHTML = `
          <div class="alert alert-danger" role="alert">
            Si è verificato un errore nel recupero dei prodotti.
          </div>`;
        document.getElementById("presale-container").innerHTML = `
          <div class="alert alert-danger" role="alert">
            Si è verificato un errore nel recupero dei prodotti.
          </div>`;
      });
  }

  // Funzione per creare le cards per ogni prodotto
  function displayProductCards(prodotti, containerId) {
    console.log(`Rendering cards for container: ${containerId}`, prodotti);
    const container = document.getElementById(containerId);
    container.innerHTML = ""; // Pulisce il contenitore prima di aggiungere le nuove cards

    prodotti.forEach(prodotto => {
      // Creazione della card HTML per ogni prodotto
      const card = document.createElement("div");
      card.classList.add("card", "m-2", "col-xs-12", "col-md-5", "col-xl-3");

      card.innerHTML = `
        <a href="product.html?id=${prodotto.prodottoId}"><img src="${prodotto.immagine}" class="card-img-top" alt="${prodotto.nome}"></a>
        <div class="card-body d-flex flex-column ">
          <h5 class="card-title">${prodotto.nome}</h5>
          <p class="card-text"><strong>€${prodotto.prezzo}</strong></p>
        </div>
      `;

      // Aggiungi la card al contenitore
      container.appendChild(card);
    });
    console.log("prodotti totali" + prodotti.length); //non so perchè qua mi prende solo i prodotti in vendit E selezionati
    /*const numeroProdotti = document.getElementById("valore-disponibili");
    numeroProdotti.textContent = `${prodotti.length} prodotti`;*/
  }

  // Funzione per filtrare i prodotti disponibili in base alle checkbox selezionate
  function filterAndDisplayAvailableProducts(prodotti) {
    // Ottieni lo stato delle checkbox
    const gamesChecked = document.getElementById("Check1").checked;
    const merchChecked = document.getElementById("Check2").checked;
    const accessoriesChecked = document.getElementById("Check3").checked;
    const specialsChecked = document.getElementById("Check4").checked;
    const otherChecked = document.getElementById("Check5").checked;

    // Crea una lista delle categorie selezionate
    const selectedCategories = [];
    if (gamesChecked) selectedCategories.push("GAMES");
    if (merchChecked) selectedCategories.push("MERCH");
    if (accessoriesChecked) selectedCategories.push("ACCESSORIES");
    if (specialsChecked) selectedCategories.push("SPECIALS");
    if (otherChecked) selectedCategories.push("OTHER");

    // Filtra i prodotti in base alle categorie selezionate
    const filteredProducts = prodotti.filter(prodotto => {
      // Se non è selezionato nessun filtro, restituisce tutti i prodotti
      if (selectedCategories.length === 0) {
        return true;
      }
      // Verifica se la categoria del prodotto è nelle categorie selezionate
      return selectedCategories.includes(prodotto.categoria);
    });

    //controlli di validità
    console.log(gamesChecked, merchChecked,accessoriesChecked,specialsChecked,otherChecked);
    console.log("Categorie selezionate:", selectedCategories);
    console.log("Prodotti filtrati:", filteredProducts);

    // Mostra i prodotti filtrati
    displayProductCards(filteredProducts, "available-container");
  }

  // Aggiungi un listener per le checkbox
  const checkboxes = document.querySelectorAll(".form-check-input");
  checkboxes.forEach(checkbox => {
    checkbox.addEventListener("change", () => {
      // Dopo ogni cambio, filtra i prodotti disponibili
      filterAndDisplayAvailableProducts(prodotti);
    });
  });

  // Chiamata alla funzione per caricare i prodotti
  getProducts(); // Richiama i prodotti al caricamento della pagina
});
