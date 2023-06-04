const {createApp}= Vue;
const app = createApp({
  data(){
    return{
      cantidadPasajeros: '',
      paquetes:[],
      idPaquete: '',
      id: null,
      filtroBusqueda:'',
      paquetes2:[],


    }
  },

  created() {
        
    axios.get('/api/paquetes')
    .then(response =>{
        this.paquetes = response.data
        console.log(this.paquetes);
        this.paquetes2 = this.paquetes
        console.log(this.paquetes2);
     
    })
  },


  methods:{

    // loadDataPaquetes(){
    //     axios.get('/api/paquetes')
    //     .then(response => {
    //       this.paquetes = response.data;
    //       console.log(this.response.data)
    //     })
    // },

    logout(){
        Swal.fire({
            title: '¿Seguro que desea cerrar sesión?',
            inputAttributes: {
                autocapitalize: 'off'
            },
            showCancelButton: true,
            confirmButtonText: 'Si',
            cancelButtonText: 'No',
            confirmButtonColor: '#0DB4F3',
            cancelButtonColor: '#FF8A80',
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
      confirmButtonColor: '#0DB4F3',
      cancelButtonColor: '#FF8A80',
      
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
                confirmButtonText: 'Ok',
                confirmButtonColor: '#0DB4F3',
              });
            } else {
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Error al crear la orden',
                timer: 6000,
                confirmButtonText: 'Ok',
                confirmButtonColor: '#0DB4F3',
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
      confirmButtonColor: '#0DB4F3',
      cancelButtonColor: '#FF8A80',
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
                confirmButtonText: 'Ok',
                confirmButtonColor: '#0DB4F3',
              });
            } else {
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Error al añadir el paquete',
                timer: 6000,
                confirmButtonText: 'Ok',
                confirmButtonColor: '#0DB4F3',
              });
            }
          });
      }
    });
  },

   filtro(){
      this.paquetes = this.paquetes2.filter(paquete => paquete.nombrePaquete.toLowerCase().includes(this.filtroBusqueda.toLowerCase()))
    }
  },
computed:{

}

})
app.mount('#app');
