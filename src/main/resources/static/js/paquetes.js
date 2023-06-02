const {createApp}= Vue;
const app = createApp({
  data(){
    return{
      cantidadPasajeros: '',
      paquetes:[],
      idPaquete: '',
      id: null,


    }
  },

  created() {
        
    axios.get('/api/paquetes')
    .then(response =>{
        this.paquetes = response.data
        console.log(this.paquetes);
    })
  },


  methods:{

    loadDataPaquetes(){
        axios.get('/api/paquetes')
        .then(response => {
          this.paquetes = response.data;
          console.log(this.response.data)
        })
    },

    logout(){
        Swal.fire({
            title: 'Seguro que desea cerrar sesion?',
            inputAttributes: {
                autocapitalize: 'off'
            },
            showCancelButton: true,
            confirmButtonText: 'Sure',
            confirmButtonColor: "#00E194;",
            preConfirm: () => {
                return axios.post('/api/logout')
                    .then(response => {
                        window.location.href = '/html/index.html'
                    })
                    .catch(error => {
                        Swal.showValidationMessage(
                            `Request failed: ${error}`
                        )
                    })
            },
            allowOutsideClick: () => !Swal.isLoading()
        })
    },

  //   logout(){
  //     console.log("hola")
  //     axios.post('/api/logout')
  //     .then(response => {window.location.href = '/html/index.html'})
  // },

  crearOrden() {
    Swal.fire({
      icon: 'warning',
      title: 'Estás por crear una orden. ¿Estás seguro?',
      showCancelButton: true,
      confirmButtonText: 'Sí, crear orden',
      cancelButtonText: 'Cancelar',
      timer: 6000,
    }).then((result) => {
      if (result.isConfirmed) {
        axios.post('/api/clientes/current/orden', `cantidadPasajeros=${this.cantidadPasajeros}`)
          .then(response => {
            window.location.href = '/html/carrito.html';
          })
          .catch(error => {
            if (error.response) {
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: error.response.data,
                timer: 6000,
              });
            } else {
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Error al crear la orden',
                timer: 6000,
              });
            }
          });
      }
    });
  },

  anadirPaquete(id) {
    Swal.fire({
      icon: 'warning',
      title: 'Estás por añadir un paquete a tu orden. ¿Estás seguro?',
      showCancelButton: true,
      confirmButtonText: 'Sí, añadir paquete',
      cancelButtonText: 'Cancelar',
      timer: 6000,
    }).then((result) => {
      if (result.isConfirmed) {
        axios.post('/api/clientes/current/seleccionar-paquete', `idPaquete=${id}`)
          .then(response => {
            window.location.href = '/html/carrito.html';
          })
          .catch(error => {
            if (error.response) {
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: error.response.data,
                timer: 6000,
              });
            } else {
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Error al añadir el paquete',
                timer: 6000,
              });
            }
          });
      }
    });
  },






  }


})
app.mount('#app');