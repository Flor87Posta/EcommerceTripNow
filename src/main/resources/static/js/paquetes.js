const {createApp}= Vue;
const app = createApp({
  data(){
    return{
      email:'',
      contrasena:'',
    }
  },
  methods:{
    loadData(){

    },
    logout() {
        Swal.fire({
            title: 'Seguro que desea cerrar sesion?',
            inputAttributes: {
                autocapitalize: 'off'
            },
            showCancelButton: true,
            confirmButtonText: 'Sure',
            confirmButtonColor: "#00E194;",
            preConfirm: () => {
                return axios.post('/api/logout')
                    .then(response => {
                        window.location.href = "/html/index.html"
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
    crearOrden(){

    },

  }
})

app.mount('#app');