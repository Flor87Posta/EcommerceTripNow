const { createApp } = Vue

const app = createApp({

    data() {
        return {
            nombre: '',
            apellido: '',
            email: '',
            clientes: [],
            nombreDestino: '',
            cantidadDias: '',
            precioHotelExcursion: '',
            destinos: [],
            pasajes:[],
            nombrePaquete:'',
            dias:'',
            precioTotalUnitario:'',
            stock:'',
            imagen1:'',
            pasaje:null,
            destino:'' ,
            excursiones: [],
            nombreExcursion: '',
            actividad: '',
            precioExcursion:'',
            cantidadStock: '',
            destinoExcursion:''
        


        }
    },
    created() { //         // Hacer una petición a través de una función loadData
        this.loadData();
    },

    methods: {

        loadData() {
            axios.get("/clientes")
                .then(response => {
                    this.clientes = response.data;
                    console.log(response)



                })
                .catch(err => console.log(err));
            axios.get('/api/destinos')
            .then(response => {
                this.destinos = response.data;
                console.log(this.destinos);
            })
            axios.get('/api/pasajes')
            .then(response => {
                this.pasajes = response.data;
                console.log(this.pasajes);
            })
            axios.get('/api/excursiones')
            .then(response => {
                this.excursiones = response.data;
                console.log(this.excursiones);
            })
        },

        addClient() {
            this.postClient();
        },

        postClient() {
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
        },
        añadirDestino() {
            const dias = this.cantidadDias.split(",").map(dias => parseInt(dias.trim()));

            axios.post('/api/admin/destino', {
                nombre: this.nombreDestino,
                cantidadDias: dias,
                precioHotelExcursion: this.precioHotelExcursion
            })
                .then((response) => Swal.fire({
                    icon: 'success',
                    text: 'Destino creado correctamente',

                }

                ))
                .catch((error) => {
                    console.log(error.response.data)
                    Swal.fire({
                        icon: 'error',
                        text: error.response.data,
                        confirmButtonColor: "#7c601893",
                    })
                })
        },
        crearPaquete(){
            axios.post('/api/admin/paquete',{
                nombrePaquete: this.nombrePaquete,
                dias: this.dias,
                precioTotalUnitario: this.precioTotalUnitario,
                stock: this.stock,
                imagen1: this.imagen1,
                pasaje: { id: this.pasaje },
                destino: { id: this.destino }
            })
            .then((response) => Swal.fire({
                icon: 'success',
                text: 'Paquete creado correctamente',

            }

            ))
            .catch((error) => {
                console.log(error.response.data)
                Swal.fire({
                    icon: 'error',
                    text: error.response.data,
                    confirmButtonColor: "#7c601893",
                })
            })
        },
        crearExcursion(){
            axios.post('/api/admin/excursion',{
                nombre: this.nombreExcursion,
                actividad: this.actividad,
                precioExcursion: this.precioExcursion,
                cantidadStock: this.cantidadStock,
                destino: { id: this.destinoExcursion }
            })
            .then((response) => Swal.fire({
                icon: 'success',
                text: 'Excursión creada correctamente',

            }

            ))
            .catch((error) => {
                console.log(error.response.data)
                Swal.fire({
                    icon: 'error',
                    text: error.response.data,
                    confirmButtonColor: "#7c601893",
                })
            })
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