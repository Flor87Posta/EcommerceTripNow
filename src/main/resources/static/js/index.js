const {createApp}= Vue;
const app = createApp({
  data(){
    return{
      email:'',
      contrasena:'',
      email2: '',
      contrasena2:'',
      nombre:'',
      apellido:'',
      pasaporte:'',
      direccion:'',
      telefono:'',
      fechaNac:'',
    }
  },
  methods:{
  Login() {
  axios.post('/api/login', `email=${this.email}&contrasena=${this.contrasena}`)
    .then(response => {
      if (this.email === "florys_211@hotmail.com") {
        window.location.href =('/manager/manager.html');
      } else {
        window.location.href = '/html/paquetes.html';
      }
    })
    .catch(error => {
      const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
          confirmButton: 'btn btn-success ms-1',
          cancelButton: 'btn btn-danger ms-1'
        },
        buttonsStyling: false
      });

      swalWithBootstrapButtons.fire({
        title: '¿Tienes cuenta?',
        text: "No puedes acceder sin ella",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Si tengo',
        cancelButtonText: "No tengo",
        confirmButtonColor: '#0DB4F3',
        cancelButtonColor: '#FF8A80',
        reverseButtons: true
      })
        .then((result) => {
          if (result.isConfirmed) {
            swalWithBootstrapButtons.fire({
              icon: 'error',
              title: '¿Estás seguro?',
              text: 'Esta información es incorrecta, intenta nuevamente por favor',
              confirmButtonText: 'Ok',
              confirmButtonColor: '#0DB4F3',
            });
          } else if (result.dismiss === Swal.DismissReason.cancel) {
            swalWithBootstrapButtons.fire(
              'Cancelado',
              'Crea una cuenta por favor',
              'error'
            );
          }
        });
    });
},


    /* window.location.replace('http://localhost:8080/h2-console');*/


    Register() {
      axios
        .post('/api/clientes', `nombre=${this.nombre}&apellido=${this.apellido}&pasaporte=${this.pasaporte}&direccion=${this.direccion}&telefono=${this.telefono}&email=${this.email2}&contrasena=${this.contrasena2}&fechaNac=${this.fechaNac}`)
        .then(response => axios.post('/api/login', `email=${this.email2}&contrasena=${this.contrasena2}`)
          .then(response => window.location.href = '/html/paquetes.html')
          .catch(error => {
            Swal.fire({
                icon: 'error',
                text: error.response.data,
                confirmButtonText: 'Ok',
                confirmButtonColor: '#0DB4F3',}
            )}))
            .catch(error => {
              Swal.fire({
                  icon: 'error',
                  text: error.response.data,
                  confirmButtonText: 'Ok',
                  confirmButtonColor: '#0DB4F3',}
              )
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
