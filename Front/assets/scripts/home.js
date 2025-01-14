console.log("Script loaded correctly");

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

document.addEventListener("DOMContentLoaded", () => {
  
    function fetchProducts() {
        const apiUrl = 'http://localhost:8080/api/prodotto';
        
        fetch(apiUrl)
            .then(response => response.json())
            .then(products => {
                if (Array.isArray(products)) {
                    displayProducts(products);
                } else {
                    console.error("I prodotti non sono un array");
                }
            })
            .catch(error => {
                console.error("Errore durante il recupero dei dati:", error);
            });
    }

   
    function displayProducts(products) {
        const newProductsContainer = document.getElementById("new-products");
        const bestSellersContainer = document.getElementById("best-sellers");

        
        newProductsContainer.innerHTML = "";
        bestSellersContainer.innerHTML = "";

        
        products.forEach(product => {
            const productCard = createProductCard(product);
            newProductsContainer.appendChild(productCard);

            
        });

        // Vogliamo mantenere sezione Best Sellers? Nel caso, implementiamo qui la logica
    }

    
    function createProductCard(product) {
        const productCard = document.createElement("a");
        productCard.classList.add("card-link");
        productCard.href = `product.html?id=${product.prodottoId}`;

        const card = document.createElement("div");
        card.classList.add("card");

        const productImage = document.createElement("img");
        productImage.src = product.immagine || "/assets/img/default.jpg";
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

    
    fetchProducts();
});
