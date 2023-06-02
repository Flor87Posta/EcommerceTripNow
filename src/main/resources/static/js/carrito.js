const {createApp}= Vue;

const app = createApp({
        data(){
            return{
                ordenes:[],    
                id:null,
                idPaquete:''      
            }
        },

        created() {
           
            axios.get('/api/clientes/orden')
            .then(response =>{
                this.ordenes = response.data
                console.log(this.ordenes);
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
            }
    })
    app.mount('#app')