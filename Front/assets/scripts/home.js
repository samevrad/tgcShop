console.log("Script loaded correctly");

// SLIDER
const slides = document.querySelectorAll('.slide');
const prevBtn = document.querySelector('.prev');
const nextBtn = document.querySelector('.next');
let currentIndex = 0;
let autoPlayInterval;

function showSlide(index) {
    slides.forEach((slide, i) => {
        slide.classList.remove('active');
        if (i === index) {
            slide.classList.add('active');
        }
    });
}

function nextSlide() {
    currentIndex = (currentIndex + 1) % slides.length;
    showSlide(currentIndex);
    resetAutoPlay();
}

function prevSlide() {
    currentIndex = (currentIndex - 1 + slides.length) % slides.length;
    showSlide(currentIndex);
    resetAutoPlay();
}

function startAutoPlay() {
    autoPlayInterval = setInterval(nextSlide, 5000);
}


function resetAutoPlay() {
    clearInterval(autoPlayInterval);
    startAutoPlay();
}


nextBtn.addEventListener('click', nextSlide);
prevBtn.addEventListener('click', prevSlide);

startAutoPlay();
// FINE SLIDER 



document.addEventListener("DOMContentLoaded", () => {

    console.log("Script caricato correttamente");
  
    let prodotti = []; // Variabile per memorizzare i prodotti
    const NOVITA = "NOVITA"; // Definisci la costante per PREVENDITA
    // const BESTSELLER = "BESTSELLER"; // Definisci la costante per BESTSELLER
  
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
  
          /* INNESTO EVENTUALE DATA 
          const presaleProducts = prodotti.filter(p => (p.dataUscita > "2025-01-15" && p.inizioPrevendita < "2025-01-15"));
          const availableProducts = prodotti.filter(p => !(p.dataUscita > "2025-01-15" && p.inizioPrevendita < "2025-01-15"));*/
  
          const newProducts = prodotti.filter(p => p.categoria === NOVITA);
          //const availableProducts = prodotti.filter(p => p.categoria != null && p.categoria !== PREVENDITA);
  
          // Verifica se ci sono effettivamente prodotti in new e bestseller
          console.log("Prodotti in novità:", newProducts);
          //console.log("Prodotti bestseller:", bestProducts);
          
          // Visualizza i prodotti nei rispettivi contenitori
          displayProductCards(newProducts, "new-products");
          //displayProductCards(newProducts, "best-sellers");
        })
        .catch(error => {
          console.error("Errore:", error);
          document.getElementById("new-products").innerHTML = `
            <div class="alert alert-danger" role="alert">
              Si è verificato un errore nel recupero dei prodotti.
            </div>`;
          /*document.getElementById("presale-container").innerHTML = `
            <div class="alert alert-danger" role="alert">
              Si è verificato un errore nel recupero dei prodotti.
            </div>`;*/
        });
    }
  
    // Funzione per creare le cards per ogni prodotto
    //function displayProductCards(prodotti, containerId, NOVITA/BESTSELLER)
    function displayProductCards(prodotti, containerId) {
      console.log(`Rendering cards for container: ${containerId}`, prodotti);
      const container = document.getElementById(containerId);
      container.innerHTML = ""; // Pulisce il contenitore prima di aggiungere le nuove cards
  
      prodotti.forEach(prodotto => {
        // Creazione della card HTML per ogni prodotto
        const card = document.createElement("div");
        card.classList.add("card", "m-2", "col-xs-12", "col-md-5", "col-xl-3");
  
        if(prodotto.categoria==NOVITA){
          card.innerHTML = `
          <a href="product.html?id=${prodotto.prodottoId}"><img src="${prodotto.imgUrl}" class="card-img-top" alt="${prodotto.nome}"></a>
          <div class="card-body d-flex flex-column ">
            <h5 class="card-title">${prodotto.nome}</h5>
            <div class="d-flex justify-content-between">
              <span class="card-text discount"><strong>€${prodotto.prezzo}</strong></span>
            </div>
          </div>
        `;} //else if(prodotto.categoria==bestseller ecc)
        // Aggiungi la card al contenitore
        container.appendChild(card);
      });
      console.log("prodotti totali" + prodotti.length); //non so perchè qua mi prende solo i prodotti in vendit E selezionati
      /*const numeroProdotti = document.getElementById("valore-disponibili");
      numeroProdotti.textContent = `${prodotti.length} prodotti`;*/
    } 
  
    // Chiamata alla funzione per caricare i prodotti
    getProducts(); // Richiama i prodotti al caricamento della pagina
  });
  


/*function createProductCard(product) {
    const productCard = document.createElement("a");
    productCard.classList.add("card-link");
    productCard.href = `product.html?id=${product.prodottoId}`;

    const card = document.createElement("div");
    card.classList.add("card");

    const productImage = document.createElement("img");
    productImage.src = product.imgUrl;
    productImage.alt = product.nome;
    card.appendChild(productImage);

    const cardBody = document.createElement("div");
    cardBody.classList.add("card-body");

    const productName = document.createElement("h3");
    productName.textContent = product.nome;
    cardBody.appendChild(productName);

    const productDescription = document.createElement("p");
    productDescription.textContent = product.descrizione || "Descrizione non disponibile";
    cardBody.appendChild(productDescription);

    card.appendChild(cardBody);
    productCard.appendChild(card);

    return productCard;
}


fetchProducts();*/