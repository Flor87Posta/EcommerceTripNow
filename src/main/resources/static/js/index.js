const {createApp}= Vue;
const app = createApp({
  data(){
    return{
      email:'',
      contrasena:'',
    }
  },
  methods:{
    Login(){
      axios.post('/api/login', `email=${this.email}&contrasena=${this.contrasena}`)
      .then(response => window.location.href= '/html/paquetes.html')
      .catch(error => {
        const swalWithBootstrapButtons = Swal.mixin({
          customClass: {
            confirmButton: 'btn btn-success ms-1',
            cancelButton: 'btn btn-danger ms-1'
          },
          buttonsStyling: false
        })
        swalWithBootstrapButtons.fire({
          title: 'Tienes cuenta?',
          text: "No puedes acceder sin ella!",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonText: 'Si tengo',
          cancelButtonText: "No tengo",
          reverseButtons: true
        })
        .then((result) => {
          if (result.isConfirmed) {
            swalWithBootstrapButtons.fire({
              icon:'error',
              title: 'Estas segur@?',
              text: 'Esta infromación es incorrecta, intenta nuevamente!',
            }
            )
          } else if (
            result.dismiss === Swal.DismissReason.cancel
          ) {
            swalWithBootstrapButtons.fire(
              'Cancelado',
              'Create una cuenta por favor :)',
              'error'
            )
          }
        })
      })
    }
  }
})

app.mount('#app');

const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');
const btnPopup = document.querySelector('.btnLogin-popup');
const iconClose = document.querySelector('.icon-close');
registerLink.addEventListener('click', () => {
  wrapper.classList.add('active');
});

loginLink.addEventListener('click', () => {
  wrapper.classList.remove('active');
});

btnPopup.addEventListener('click', () => {
  wrapper.classList.add('active-popup');
});


iconClose.addEventListener('click', () => {
  wrapper.classList.remove('active-popup');
});

//boton
const showOnPx = 100;
const backToTopButton = document.querySelector(".back-to-top")

const scrollContainer = () => {
  return document.documentElement || document.body;
};

document.addEventListener("scroll", () => {
  if (scrollContainer().scrollTop > showOnPx) {
    backToTopButton.classList.remove("hidden")
  } else {
    backToTopButton.classList.add("hidden")
  }
})

const goToTop = () => {
  document.body.scrollIntoView({
    behavior: "smooth",
  });
};

backToTopButton.addEventListener("click", goToTop)
