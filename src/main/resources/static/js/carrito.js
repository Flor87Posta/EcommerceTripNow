const {createApp}= Vue;

const app = createApp({
        data(){
            return{
                ordenes:[],       
            }
        },

        created() {
           
            axios.get('/api/clientes/orden')
            .then(response =>{
                this.ordenes = response.data
                console.log(this.ordenes);
            })
          },
          
        methodos:{
        }
    })
    app.mount('#app')