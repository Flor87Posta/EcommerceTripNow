const { createApp } = Vue

const app = createApp({

    data() {
        return {
            nombre: '',
            apellido: '',
            email: '',
            pasaporte: '',
            direccion: '',
            telefono: '',
            fechaNac: '',
            contrasena: '',
            clientes: [],
            nombreDestino: '',
            cantidadDias: '',
            precioHotelExcursion: '',
            destinos: [],
            pasajes: [],
            nombrePaquete: '',
            dias: '',
            precioTotalUnitario: '',
            stock: '',
            imagen1: '',
            pasaje: null,
            destino: '',
            excursiones: [],
            nombreExcursion: '',
            actividad: '',
            precioExcursion: '',
            cantidadStock: '',
            destinoExcursion: '',
            paquetes: [],
            nombreHotel: '',
            categoria: '',
            allInclusive: true,
            desayuno: true,
            mediaPension: true,
            precioHotel: null,
            cantidadStockHotel: null,
            destinoHotel: ''



        }
    },
    created() { //         // Hacer una petición a través de una función loadData
        this.loadData();
    },

    methods: {

        loadData() {
            axios.get("/api/clientes")
                .then(response => {
                    this.clientes = response.data;
                    console.log(this.clientes)
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
            axios.get('/api/paquetes')
                .then(response => {
                    this.paquetes = response.data;
                    console.log(this.paquetes);
                })
        },


        postClient() {
            const params = new URLSearchParams();
            params.append('nombre', this.nombre);
            params.append('apellido', this.apellido);
            params.append('email', this.email);
            params.append('pasaporte', this.pasaporte);
            params.append('direccion', this.direccion);
            params.append('telefono', this.telefono);
            params.append('fechaNac', this.fechaNac);
            params.append('contrasena', this.contrasena);
        
            axios.post('/api/clientes', params)
                .then(response => {
                    // Agregar el nuevo cliente al array clientes
                    this.clientes.push(response.data);
                    // Limpiar los campos del formulario
                    this.nombre = '';
                    this.apellido = '';
                    this.email = '';
                    this.pasaporte = '';
                    this.direccion = '';
                    this.telefono = '';
                    this.fechaNac = '';
                    this.contrasena = '';
                })
                .then(() => Swal.fire({
                    icon: 'success',
                    text: 'Cliente creado correctamente',
                }))
                .catch((error) => {
                    console.log(error.response.data)
                    Swal.fire({
                        icon: 'error',
                        text: error.response.data,
                        confirmButtonColor: "#7c601893",
                    })
                })
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
        crearPaquete() {
            axios.post('/api/admin/paquete', {
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
        crearExcursion() {
            axios.post('/api/admin/excursion', {
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
        },
        añadirHotel() {
            axios.post('/api/admin/hotel', {

                nombre: this.nombreHotel,
                categoria: this.categoria,
                allInclusive: this.allInclusive,
                desayuno: this.desayuno,
                mediaPension: this.mediaPension,
                precioHotel: this.precioHotel,
                cantidadStock: this.cantidadStockHotel,
                destino: { id: this.destinoHotel }
            })
                .then((response) => Swal.fire({
                    icon: 'success',
                    text: 'Hotel creado correctamente',

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