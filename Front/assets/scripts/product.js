document.addEventListener("DOMContentLoaded", () => {
    // Simulazione dei dati dei prodotti
    const products = [
        {
            id: 1,
            categoria: "Videogame",
            immagine: "/assets/img/baldurs_gate_3_collector_DXz6AG6_jpg_1600x900_crop_q85.webp",
            descrizione: "trallalero trallalla",
            disponibile: true,
        },
        {
            id: 2,
            categoria: "Blame",
            immagine: "../assets/img/Blame.jpg",
            descrizione: "pew pew motherfucka",
            disponibile: false,
        }
    ];

    // Funzione per ottenere l'ID dal parametro GET
    function getProductId() {
        const params = new URLSearchParams(window.location.search);
        return parseInt(params.get("id"), 10); // Converte l'ID in un intero
    }

    // Ottieni l'ID del prodotto
    const productId = getProductId();

    if (!productId) {
        // Se non c'è un ID valido, reindirizza alla home
        window.location.href = "index.html";
        return;
    }

    // Trova il prodotto corrispondente
    const product = products.find(p => p.id === productId);

    if (!product) {
        // Se il prodotto non esiste, mostra un errore
        document.getElementById("product-details-container").innerHTML = `
            <div class="alert alert-danger" role="alert">
                Prodotto non trovato!
            </div>`;
        return;
    }

    // Aggiungi i dettagli del prodotto alla pagina
    const productInfoContainer = document.getElementById("product-details-container");
    productInfoContainer.innerHTML = `
        <div class="card">
            <img src="${product.immagine}" class="card-img-top" alt="${product.descrizione}">
            <div class="card-body">
                <h5 class="card-title">Categoria: ${product.categoria}</h5>
                <p class="card-text">
                    Descrizione: ${product.descrizione}
                </p>
                ${product.disponibile 
                    ? `<button class="btn btn-success">Prenota</button>` 
                    : `<span class="badge bg-danger">Non Disponibile</span>`}
            </div>
        </div>`;
});
