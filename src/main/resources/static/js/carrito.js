const {createApp}= Vue;

const app = createApp({
        data(){
            return{
                ordenes:[],  
                id:null,
                idPaquete:'',
                cliente:[]     
            }
        },

        created() {
           
            axios.get('/api/clientes/orden')
            .then(response =>{
                this.ordenes = response.data
                console.log(this.ordenes);
            })

            axios.get('/api/clientes/current')
            .then(response=> {
                this.cliente = response.data;
                console.log(this.cliente)
            })
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
                 format(precio){
                    let options = { style: 'currency', currency: 'USD' };
                    let numberFormat = new Intl.NumberFormat('en-US', options);
                    return numberFormat.format(precio);
                },
        }
    })
    app.mount('#app')