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
                number:formattedString,
                email: this.email,
                cvv:this.cvv,
                amount:this.amount,
                description:this.description,
                typeCard:this.typeCard
            }
            Swal.fire({
                icon: 'warning',
                title: 'You are requesting a new Pay..¿Are you sure?',
                showCancelButton: true,
                confirmButtonText: 'Yes, request new Pay',
                cancelButtonText: 'Cancel',
                timer: 6000,
            }).then((result) => {
                if (result.isConfirmed) {
            axios.post('http://localhost:8080/api/clients/current/pay-card', payment)
            .then(response => {
                if (response.status === 201) {
                            Swal.fire({
                                icon: 'success',
                                title: 'You have made a new Pay!',
                                showCancelButton: true,
                                confirmButtonText: 'Accepted',
                                cancelButtonText: 'Cancel',
                                timer: 6000,
                            })
                    }
                
            }).catch(error => Swal.fire({
                icon: 'error',
                title: 'Error',
                text: error.response.data,
                timer: 6000,
            }))
        }
            }
            )
    
        },


    }

}).mount("#app")