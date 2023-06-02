const { createApp } = Vue
createApp({
    data() {
        return {
       digits:null,
       cvv:null,
       amount:null,
       description:null,
       typeCard:null,
       email:null,
       idOrden:null,

        }
    },
    mounted() {

    },
    created() {

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
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.post('http://localhost:8080/api/clientes/current/pagar-orden', payment)
                        .then(response => {
                            if (response.status === 201) {
                                Swal.fire({
                                    icon: 'success',
                                    title: 'Tu Pago ha sido realizado!',
                                    showCancelButton: true,
                                    confirmButtonText: 'Aceptar',
                                    timer: 6000,
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
                                });
                            } else if (error.request) {
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Error',
                                    text: 'No se recibió respuesta del servidor',
                                    timer: 6000,
                                });
                            } else {
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Error',
                                    text: 'Error al realizar la solicitud',
                                    timer: 6000,
                                });
                            }
                        });
                }
            });
        },


    }

}).mount("#app")