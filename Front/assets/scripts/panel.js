
document.addEventListener("DOMContentLoaded", () => {

    // Carica i prodotti all'inizio
    fetchProducts();

    // Gestisce l'apertura della modale per aggiungere un nuovo prodotto
    const openAddProductModalButton = document.getElementById('open-add-product-modal');
    const newProductModal = document.getElementById('new-product-modal');
    const mainContent = document.getElementById('main-content'); // Contenitore del resto della pagina

    openAddProductModalButton.addEventListener('click', () => {
        const modal = new bootstrap.Modal(newProductModal);
        modal.show(); // Mostra la modale

        // Disabilita l'interazione con il resto della pagina
        mainContent.setAttribute('inert', ''); 
    });

    // Gestisce la chiusura della modale
    const closeModalButton = document.querySelector('.btn-close');
    closeModalButton.addEventListener('click', () => {
        const modal = bootstrap.Modal.getInstance(newProductModal);
        modal.hide(); // Chiudi la modale

        // Rimuovi l'attributo inert dal contenuto della pagina
        mainContent.removeAttribute('inert');
    });

    // Gestisce l'invio del modulo per aggiungere un nuovo prodotto
    const addProductForm = document.getElementById('add-product-form');
    addProductForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const newProduct = {
            nome: document.getElementById('nome').value,
            descrizione: document.getElementById('descrizione').value,
            categoria: document.getElementById('categoria').value,
            prezzo: parseFloat(document.getElementById('prezzo').value),
            rimanenza: parseInt(document.getElementById('rimanenza').value),
            abilitato: document.getElementById('abilitato').checked,
            visibile: document.getElementById('visibile').checked,
            immagine: document.getElementById('immagine').value,
            inizio_prevendita: document.getElementById('inizio_prevendita').value,
            data_uscita: document.getElementById('data_uscita').value,
            sconto_prevendita: parseFloat(document.getElementById('sconto_prevendita').value)
        };

        // Inserisci il prodotto nel backend
        fetch('http://localhost:8080/api/prodotto', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newProduct)
        })
        .then(function(response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Errore nell'inserimento del prodotto");
            }
        })
        .then(function(product) {
            alert("Prodotto aggiunto con successo!");
            fetchProducts(); // Ricarica la lista dei prodotti
            const modal = bootstrap.Modal.getInstance(newProductModal);
            modal.hide(); // Chiudi la modale

            // Rimuovi l'attributo inert dal contenuto della pagina
            mainContent.removeAttribute('inert');
        })
        .catch(function(error) {
            console.error("Errore durante l'inserimento del prodotto:", error);
        });
    });

    function fetchProducts() {
        const apiUrl = 'http://localhost:8080/api/prodotto'; // URL per ottenere i prodotti

        fetch(apiUrl)
        .then(function(response) {
            return response.json();
        })
        .then(function(products) {
            if (Array.isArray(products)) {
                displayProducts(products);
            } else {
                console.error("I prodotti non sono un array");
            }
        })
        .catch(function(error) {
            console.error("Errore durante il recupero dei dati:", error);
        });
    }

    function displayProducts(products) {
        const productsContainer = document.getElementById("product-cards-container");
        productsContainer.innerHTML = ""; // Pulisce la container

        products.forEach(function(product) {
            const productCard = createProductCard(product);
            productsContainer.appendChild(productCard);
        });
    }

    function createProductCard(product) {
        const productCard = document.createElement("div");
        productCard.classList.add("product-card");

        const template = document.getElementById("product-template").innerHTML;
        const form = document.createElement('div');
        form.innerHTML = template;

        // Aggiungi l'ID del prodotto come attributo data-product-id
        form.setAttribute('data-product-id', product.prodottoId);

        // Precompila il modulo con i dati del prodotto
        fillFormWithProductData(form, product);

        // Gestisci il bottone di modifica
        form.querySelector("#update-product-btn").addEventListener("click", function() {
            const prodottoId = form.getAttribute('data-product-id');
            console.log(prodottoId);
            if (prodottoId) {
                const confirmUpdate = confirm("Sei sicuro di voler modificare questo prodotto?");
                if (confirmUpdate) {
                    updateProduct(prodottoId, form);
                }
            }
        });

        // Gestisci il bottone di eliminazione
        form.querySelector("#delete-product-btn").addEventListener("click", function() {
            const prodottoId = form.getAttribute('data-product-id');
            console.log(prodottoId);
            
            if (prodottoId) {
                const confirmDelete = confirm("Sei sicuro di voler eliminare questo prodotto?");
                if (confirmDelete) {
                    deleteProduct(prodottoId, form);
                }
            }
        });

        // Gestisci il bottone di reset
        form.querySelector("#reset-form-btn").addEventListener("click", function() {
            resetForm(form, product);
        });

        productCard.appendChild(form);
        return productCard;
    }

    function fillFormWithProductData(form, product) {
        form.querySelector("#nome").value = product.nome;
        form.querySelector("#descrizione").value = product.descrizione || "";
        form.querySelector("#categoria").value = product.categoria || "";
        form.querySelector("#prezzo").value = product.prezzo;
        form.querySelector("#rimanenza").value = product.rimanenza;
        form.querySelector("#abilitato").checked = product.abilitato;
        form.querySelector("#visibile").checked = product.visibile; // Precompila il campo visibile
        form.querySelector("#immagine").value = product.immagine || "";
        form.querySelector("#inizio_prevendita").value = product.inizio_prevendita || "";
        form.querySelector("#data_uscita").value = product.data_uscita || "";
        form.querySelector("#sconto_prevendita").value = product.sconto_prevendita || "";
    }

    function updateProduct(prodottoId, form) {
        const updatedProduct = {
            prodottoId: prodottoId,
            nome: form.querySelector("#nome").value,
            descrizione: form.querySelector("#descrizione").value,
            categoria: form.querySelector("#categoria").value,
            prezzo: parseFloat(form.querySelector("#prezzo").value),
            rimanenza: parseInt(form.querySelector("#rimanenza").value),
            abilitato: form.querySelector("#abilitato").checked,
            visibile: form.querySelector("#visibile").checked, // Aggiungi il valore del campo visibile
            immagine: form.querySelector("#immagine").value,
            inizioPrevendita: form.querySelector("#inizio_prevendita").value,
            dataUscita: form.querySelector("#data_uscita").value,
            scontoPrevendita: parseFloat(form.querySelector("#sconto_prevendita").value)
    
        };

        // if (updatedProduct.data_uscita) {
        //     updatedProduct.data_uscita = new Date(updatedProduct.data_uscita).toISOString().split('T')[0]; // Solo la data
        // }

        const apiUrl = `http://localhost:8080/api/prodotto`;

        console.log(updatedProduct);
        

        fetch(apiUrl, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedProduct)
        })
        .then(function(response) {
            if (response.ok) {
                alert("Prodotto modificato con successo!");
            } else {
                throw new Error("Errore durante la modifica del prodotto");
            }
        })
        .catch(function(error) {
            console.error("Errore durante la modifica del prodotto:", error);
        });
    }

    function deleteProduct(prodottoId, form) {
        if (confirm("Sei sicuro di voler eliminare questo prodotto?")) {
            const apiUrl = `http://localhost:8080/api/prodotto/${prodottoId}`;

            fetch(apiUrl, {
                method: 'DELETE'
            })
            .then(function(response) {
                if (response.ok) {
                    alert("Prodotto eliminato con successo!");
                    form.parentElement.remove(); // Rimuovi il modulo dalla vista
                } else {
                    throw new Error("Errore durante l'eliminazione del prodotto");
                }
            })
            .catch(function(error) {
                console.error("Errore durante l'eliminazione del prodotto:", error);
            });
        }
    }

    function resetForm(form, product) {
        fillFormWithProductData(form, product); // Ripristina i dati originali nel modulo
    }

});




