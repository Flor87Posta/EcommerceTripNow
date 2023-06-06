const { createApp } = Vue
createApp({
    data() {
        return {
       digits:null,
       cvv:null,
       amount:null,
       description: "pago orden",
       typeCard:null,
       email:null,
       idOrden:null,
       ordenes:[],
       cliente:'',

        }
    },
    mounted() {

    },
    created() {
        axios.get('/api/clientes/orden')
          .then(response => {
            this.ordenes = response.data;
            console.log(this.ordenes);

            axios.get('/api/clientes/current')
            .then(clienteResponse => {
              this.cliente = clienteResponse.data;
              console.log(this.cliente);
            })
            .catch(error => {
              console.error(error);
            });
      
            // Obtener información del cliente autenticado
            axios.get('/api/clientes/current')
              .then(clienteResponse => {
                const cliente = clienteResponse.data;
      
                // Asignar los valores de las órdenes y el email del cliente
                if (this.ordenes.length > 0) {
                  this.email = cliente.email;
                  this.idOrden = this.ordenes[0].id;
                  this.amount = this.ordenes[0].precioTotalOrden;
                }
              })
              .catch(error => {
                console.log(error);
              });
          })
          .catch(error => {
            console.log(error);
          });
      },

    methods: {
        
        postPay() {
            let inputString = this.digits; // Example input string
            let formattedString = inputString.replace(/[\s-]+/g, '') // Quitar espacios y guiones
                                     .replace(/(\d{4})(?=\d)/g, '$1-'); // Agregar guiones cada 4 dígitos
            let payment = {
                idOrden: this.idOrden,
                number: formattedString,
                email: this.email,
                cvv: this.cvv,
                amount: this.amount,
                description: this.description,
                typeCard: this.typeCard
            };
        
            Swal.fire({
                icon: 'warning',
                title: 'Estás por hacer un pago..¿Estás seguro?',
                showCancelButton: true,
                confirmButtonText: 'Sí, realizar pago',
                cancelButtonText: 'Cancelar',
                timer: 6000,
                confirmButtonColor: '#0DB4F3',
                cancelButtonColor: '#FF8A80',
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.post('/api/clientes/current/pagar-orden', payment)
                        .then(response => {
                            if (response.status === 201) {
                                Swal.fire({
                                    icon: 'success',
                                    title: 'Tu Pago ha sido realizado!',
                                    showCancelButton: true,
                                    confirmButtonText: 'Aceptar',
                                    timer: 6000,
                                    confirmButtonColor: '#0DB4F3',
                                    cancelButtonColor: '#FF8A80',
                                });
                            }
                        })
                        .catch(error => {
                            if (error.response) {
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Error',
                                    text: `Error del servidor: ${error.response.data}`,
                                    timer: 6000,
                                    confirmButtonText: 'Ok',
                                    confirmButtonColor: '#0DB4F3',
                                });
                            } else if (error.request) {
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Error',
                                    text: 'No se recibió respuesta del servidor',
                                    timer: 6000,
                                    confirmButtonText: 'Ok',
                                    confirmButtonColor: '#0DB4F3',
                                });
                            } else {
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Error',
                                    text: 'Error al realizar la solicitud',
                                    timer: 6000,
                                    confirmButtonText: 'Ok',
                                    confirmButtonColor: '#0DB4F3',
                                });
                            }
                        });
                }
            });
        },

        enviarPDF(id) {
          axios.post('/api/clientes/current/export-pdf', `idOrden=${id}`)
            .then(response => {
              Swal.fire({
                icon: 'success',
                title: 'PDF enviado',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                confirmButtonColor: '#0DB4F3',
                timer: 6000,
              }).then(response => {
                window.location.href = '/html/paquetes.html';
              });
            })
            .catch(error => {
              if (error.response) {
                Swal.fire({
                  icon: 'error',
                  title: 'Error',
                  text: `Error del servidor: ${error.response.data}`,
                  timer: 6000,
                  confirmButtonText: 'Ok',
                  confirmButtonColor: '#0DB4F3',
                });
              } else if (error.request) {
                Swal.fire({
                  icon: 'error',
                  title: 'Error',
                  text: 'No se recibió respuesta del servidor',
                  timer: 6000,
                  confirmButtonText: 'Ok',
                  confirmButtonColor: '#0DB4F3',
                });
              } else {
                Swal.fire({
                  icon: 'error',
                  title: 'Error',
                  text: 'Error al realizar la solicitud',
                  timer: 6000,
                  confirmButtonText: 'Ok',
                  confirmButtonColor: '#0DB4F3',
                });
              }
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

          format(precio){
            let options = { style: 'currency', currency: 'USD' };
            let numberFormat = new Intl.NumberFormat('en-US', options);
            return numberFormat.format(precio);
        },


    }

}).mount("#app");
window.addEventListener('load', () => {
  const loader = document.querySelector('.loader');

  loader.classList.add('loader-hidden');

  loader.addEventListener('transitioned', () => {
      document.body.removeChild('loader');
  });
});