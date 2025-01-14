// const loginBtn= document.querySelectorAll('.submit')


// const logoutBtn = document.querySelector('#logoutBtn');
// const loginForm = document.querySelector('.form-login');


// const navBrand = document.querySelector('.navbar-brand');
// const userIcon = document.querySelector('.user-icon');
// const navHome = document.querySelector('.nav-link');

// let path = window.location.pathname.split('/');

// let indexPath = path;
// indexPath[indexPath.length-1] = 'index.html';
// indexPath = indexPath.join('/');

// navBrand.href = indexPath;
// navHome.href = indexPath;

// loginBtn.addEventListener('click', e => {
//     e.preventDefault();

//     const user = {
//         email: loginForm.email.value,
//         password: loginForm.password.value
//     };

//     login(user);
// });


// async function login(user) {

//     const response = await fetch('http://127.0.0.1:8080/auth/login', {
//         method: 'POST',
//         headers: { 'Content-Type': 'application/json' },
//         body: JSON.stringify(user)
//     });

//     if (response.ok) {
        
//         let utenteLogged = await response.json();

//         let utente = {
//             utenteId: utenteLogged.utente_id,
//             ruolo: utenteLogged.ruolo,
//             nome: utenteLogged.nome
//         }

//         localStorage.setItem('currentUser', JSON.stringify(utente))

//         let redirect = sessionStorage.getItem('redirect');

//         if (redirect !== null) {
//             path[path.length - 1] = redirect;
//             sessionStorage.clear();
//         } else {
//             path[path.length - 1] = 'utente.html';
//         }
//         window.location.pathname = path.join('/');
//     } else {
//         alert('Invalid credentials!');
//     }
// }

// // if utente email in localStorage -> form email value = localStorage(utenteEmail)

// async function accessProtected() {
//     const response = await fetch('http://127.0.0.1:8080/auth/protected');
//     const text = await response.text();
//     alert(text);
// }

// async function logout() {
//     await fetch('/test/logout', { method: 'POST' });

//     localStorage.clear();

//     path[path.length - 1] = 'login.html';
//     window.location.pathname = path.join('/');
// }


// async function checkLoggato() {
    
//     let utenteLoggato = localStorage.getItem('currentUser');

//     if (utenteLoggato !== null) {
//         path[path.length - 1] = 'utente.html';
//         window.location.pathname = path.join('/');
//     } else {
//         return false;
//     }
    
// }

// checkLoggato();

// SCRIPT DEL MODALE LOGIN - VA AGGIUNTA FUNZIONALITA' CRUD
(() => {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  const forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
    }, false)
  })
})()
// FINE SCRIPT DEL MODALE LOGIN - VA AGGIUNTA FUNZIONALITA' CRUD

document.addEventListener('DOMContentLoaded', () => {
    const loginButton = document.getElementById('loginButton'); 
    const usernameInput = document.getElementById('inputEMail');
    const passwordInput = document.getElementById('inputPassword');
    
    loginButton.addEventListener('click', async (e) => {
      e.preventDefault(); 
  
      const username = usernameInput.value;
      const password = passwordInput.value;
  
      if (username && password) {
        const user = {
          email: username,
          password: password
        };
  
        try {
          const response = await fetch('http://127.0.0.1:8080/auth/login', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(user),
          });
  
          if (response.ok) {
            
            const loggedInUser = await response.json();
  
            
            localStorage.setItem('currentUser', JSON.stringify(loggedInUser));
  
           
            window.location.href = 'index.html';
          } else {
            alert('Credenziali non valide!');
          }
        } catch (error) {
          console.error('Errore nella richiesta di login:', error);
          alert('Si è verificato un errore durante il login. Riprova più tardi.');
        }
      } else {
        alert('Per favore, inserisci sia lo username che la password.');
      }
    });
  });
  