const {createApp}= Vue;

const app = createApp({
        data(){
            return{
                orden:[],
            }
        },
        methodos:{
            loadDataOrdenes(){
                axios.get('/api/clientes/current/orden')
                .then(response => {
                    this.orden = response.data
                    console.log(this.orden);
                })
            }
        }
      })