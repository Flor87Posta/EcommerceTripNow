const {createApp}= Vue;
const app = createApp({
  data(){
    return{
      cantidadPasajeros: '',
      paquetes:{}
    }
  },
  methods:{
    loadDataPaquetes(){
        axios.get('/api/paquetes')
        .then(response => {
          this.paquetes = response.data;
          console.log(this.paquetes)
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
    crearOrden(){ 
      axios.post('/api/clientes/current/orden',`cantidadPasajeros=${this.cantidadPasajeros}`)
      .then(response => window.location.href = '/html/carrito.html')
      .catch(error => {
        Swal.fire({
          icon: 'error',
          text: error.response.data
      })
      })
    },

  }
})

app.mount('#app');