const {createApp} = Vue

const app = createApp ({

    data(){
        return {
            nombre : '',
            apellido : '',
            email : '',
            clientes : [],

        }
    },
    created(){
    //         // Hacer una petición a través de una función loadData
            this.loadData();

            

            },

        methods: {

        loadData(){
                axios.get("http://localhost:8080/clientes")
                    .then(response => { 
                        this.clientes=response.data;
                        console.log(response)    
                        
                    
                    
                    })
                    .catch(err => console.log( err ));
                },

        addClient(){
            this.postClient();
        },

        postClient(){
                axios.post("http://localhost:8080/clientes", {
                    nombre: this.nombre,
                    apellido: this.apellido,
                    email: this.email,
                })
                .then(function (response) {
                    this.loadData();
                })
                .catch(function (error) {
                    console.log(error);
                });
            }

            },
    

/*    loadData: obtiene el listado de clientes usando AJAX al back-end o servicio REST.

    Realiza una petición HTTP de tipo GET a la URL /clientes con la librería axios.

    Cuando la petición es respondida se ejecuta el método then

    El método then guarda en la data de Vue el listado de clientes que llega en el JSON así como el JSON completo.

    Vue se encarga de mostrar esos datos de manera automática, puedes volver a verificar el HTML para que veas las instrucciones de Vue que lo hacen.



    addClient: se ejecuta al pulsar el botón “add client” en la página web.

    Obtiene la data del formulario ya que los campos del mismo se encuentran asociados a la data de Vue.

    Si se han introducido los datos se ejecuta la función postClient



    postClient: obtiene los datos del nuevo cliente usando AJAX (peticiones asíncronas) al back-end o servicio REST.

    Realiza una petición HTTP de tipo POST a la URL /cients con la librería axios.

    Cuando la petición es respondida se ejecuta el método then

    El método then ejecuta la función loadData para que se recargue la información en la página.*/
})

app.mount('#app')