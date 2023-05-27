# ChallengeMindhubGrupo2
Challenge final E-commerce
Condiciones:
Temática del proyecto: MARKETPLACE/E-COMMERCE.  
Debe ser un sitio donde el usuario pueda comprar agregando a un carrito (por ejemplo, no podría ser una inmobiliaria donde el usuario no va a comprar más de una casa).  
Puede ser un sitio tipo multi-store (como si fuera un shopping on line con distintos "comercios") o monotemático pero con categorías filtrables, etc. 
Se debe poder ir agregando a un carrito que al final pagaré a través de una pasarela de pagos que debe estar integrada como mínimo con una forma de pago y terminando en un checkout. 
Adicionalmente pueden enviar un mail de resumen al usuario con lo que compró.  Pensar en dirección de entrega, costos de envío dinámicos, etc. todo suma como agregados al proyecto.
  
Otros cohorts han hecho tiendas como:  tienda de vinos, tienda de decoración, multistore y festivales de Rock donde el administrador podía crear comercios de distinto rubro, tienda de productos veganos, cursos online, tienda de instrumentos musicales, etc. Tener en mente el manejo de PRODUCTOS y STOCKS a nivel panel de administración.
 
Condiciones técnicas y de funcionamiento que deben respetarse:
-         Se debe realizar una WEBAPP completa (frontend-backend), robusta y de una envergadura/complejidad adecuada para el tiempo del que disponen y la cantidad de miembros del grupo. 
-         Sistema de login/sign up con roles (administrador, miembro y visitante sin loguear).  Se debe usar el sentido común para proteger las rutas que puedan llegar a exponer los datos o la integridad de la app, lo más práctico es hacer un panel de administración.  La protección de rutas de back debe hacerse con Spring Security.
-         Se deben validar los datos que el usuario ingresa (en su creación de cuenta).
Debe existir un CRUD (create-read-update and delete).  Ya el hecho de manejar productos desde un panel de Admin (crearlos, modificar stock, borrarlos y obtenerlos para mostrarlos en la grilla) cumple con este objetivo.
-         El usuario debe estar informado en todo momento de lo que sucede en la web (pensar en preloaders animados, alertas claras y agradables -o toasts- y mensajes de error o advertencia precisos y adecuados).
-         La interfaz de la web hacia el usuario puede estar en cualquier idioma.  Lo mismo para el código (nombres de variables y funciones, comentarios, etc).
-         Las tecnologías a aplicar son Javascript y Vue.Js para el frontend.  Recordar que el código puede ser un buen antecedente para que quede en el repositorio con visualización pública, o todo lo contrario, puede ser una mala propaganda de ustedes como devs: 
   Hacerlo de forma prolija, moderna, concisa y respetando DRY (don't repeat yourself) y KIS (keep it simple).  A nivel backend debe trabajarse con Java, Spring, Gradle y el motor de prueba de base de datos H2.
-         El producto debe superar con éxito distintos tipos de pruebas, sin caerse en ningún momento.
-         Se debe incluir la posibilidad de loguearse/crear cuenta.
-   La compra se tiene que poder realizar con la pasarela de pago hecha en el homebanking (consumir api de pagos).
-  Generar número de ticket de compra (ustedes implementar exportación en pdf).
-         Debe ser hosteado en Railway o cualquier hosting de su confianza y la URL debe ser informada el día de la presentación a las 9 AM.  Cada día pasaremos a controlar los avances y haremos evaluaciones objetivas o apreciaciones personales.
