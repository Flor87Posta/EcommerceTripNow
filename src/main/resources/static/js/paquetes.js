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

    anadirPaquete(id){
      axios.post('/api/clientes/current/seleccionar-paquete', `idPaquete=${id}` )
      .then((result)=> window.location.href = '/html/carrito.html')
      .catch(error => {
        Swal.fire({
          icon: 'error',
          text: error.response.data
        }
        )
      })
    },

   filtro(){
      this.paquetes = this.paquetes2.filter(paquete => paquete.nombrePaquete.toLowerCase().includes(this.filtroBusqueda.toLowerCase()))
    }
  },
computed:{

}

})
app.mount('#app');

// filtro(){
//   this.paquetes =this.paquetes.filter(paquete => paquete.nombrePaquete.toLowerCase().includes(this.filtroBusqueda.toLowerCase()))
// },