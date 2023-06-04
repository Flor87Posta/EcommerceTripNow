const {createApp}= Vue;

const app = createApp({
        data(){
            return{
                ordenes:[],  
                id:null,
                idPaquete:'',   
                cliente:'',  
            }
        },

        created() {
           
            axios.get('/api/clientes/orden')
            .then(response =>{
                this.ordenes = response.data
                console.log(this.ordenes);
            });

            axios.get('/api/clientes/current')
      .then(clienteResponse => {
        this.cliente = clienteResponse.data;
        console.log(this.cliente);
      })
      .catch(error => {
        console.error(error);
      });


          },
          
          methods:{
            
            eliminarPaquete(id){
                axios.post('/api/clientes/current/eliminar-paquete', `idPaquete=${id}`)
                    .then(response => {
                     console.log(`paquete borrado`);
                     })
                    .catch(error => {
                    console.error(error);
                     });
                 },

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
        }
    })
    app.mount('#app')