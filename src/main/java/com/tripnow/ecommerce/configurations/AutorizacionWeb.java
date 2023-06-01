package com.tripnow.ecommerce.configurations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@EnableWebSecurity
@Configuration
class AutorizacionWeb {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors().and().authorizeRequests()

                //                PERMIT ALL
                .antMatchers("/html/index.html/").permitAll()
                .antMatchers("/html/login.html").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/posnet.html").permitAll()

                .antMatchers(HttpMethod.POST, "/api/clientes").permitAll()
                .antMatchers(HttpMethod.POST,"/api/login").permitAll()
                .antMatchers(HttpMethod.POST,"/api/logout").permitAll()
                .antMatchers(HttpMethod.POST,"/api/clientes/current/pay-card").permitAll() //para homebanking


//                ADMIN
//                .antMatchers(HttpMethod.POST, "/api/loans/admin-loan").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/clientes").hasAuthority("ADMIN")
                .antMatchers("/api/clientes").hasAuthority("ADMIN")
                .antMatchers("/api/paquetes").hasAuthority("ADMIN")
                .antMatchers("/manager/manager.html").hasAuthority("ADMIN")
                .antMatchers("http://localhost:8080/h2-console").hasAuthority("ADMIN")
                .antMatchers("/rest/**").hasAuthority("ADMIN")

//                CLIENT
                .antMatchers(HttpMethod.POST, "/api/clientes/current").hasAuthority("CLIENTE")
                .antMatchers(HttpMethod.POST, "/api/clientes/current/orden").hasAuthority("CLIENTE")
                .antMatchers(HttpMethod.POST, "/api/clientes/current/pagar-orden").hasAuthority("CLIENTE")
                .antMatchers(HttpMethod.POST, "/api/clientes/current/agregar-paquete").hasAuthority("CLIENTE")
                .antMatchers(HttpMethod.POST, "/api/clientes/current/seleccionar-paquete").hasAuthority("CLIENTE")
                .antMatchers(HttpMethod.POST, "/api/clients/current/eliminar-paquete").hasAuthority("CLIENTE")
                .antMatchers(HttpMethod.POST, "/api/clientes/current/export-pdf").hasAuthority("CLIENTE")
                .antMatchers(HttpMethod.POST, "https://homebanking-mindhub-brothers.up.railway.app/api/clients/current/pay-card").hasAuthority("CLIENTE")
                .antMatchers("/api/clientes/current").hasAuthority("CLIENTE")
                .antMatchers("/api/paquetes").hasAuthority("CLIENTE")
                .antMatchers("/html/nosotros.html").hasAuthority("CLIENTE")
                .antMatchers("/html/conocemas.html").hasAuthority("CLIENTE");
//                .anyRequest().denyAll().and();


// para cualquier otro tipo de peticion:

//     .anyRequest().denyAll();


        http.formLogin() // ruta d acceso al login, método del http security q trabaja el login, defino las reglas del login
                //
                .usernameParameter("email")

                .passwordParameter("contrasena")

                .loginPage("/api/login"); //solo recibe peticiones POST este metodo


        http.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");

        // turn off checking for CSRF tokens: esos tokens (Cross-Site Request Forgery) hacen que cada cosa que salga del back salga
        // con token XML/HTML, para que cuando se realicen peticiones haga match con cada cookie, pero no genera la cookie o token
        // del session ID (eso se maneja aparte con lo que hicimos arriba) y esos tokens hacen intransferibles los JSESSIONID para que
        // no los puedan copiar
        //si los habilito no funciona como API, porq necesita acceso de otros programas, como h2 etc etc;
        http.csrf().disable();


        //disabling frameOptions so h2-console can be accessed:

        http.headers().frameOptions().disable(); // e-frame (como los q usamos en google maps): son configuraciones preestablecidas o
        // por defecto de h2 console, cada metodo devuelve un objeto http
        // las desactivamos a esas configuraciones (que son las que arman la consola desde cero) para poder usar h2 por ej
        //En resumen, http.headers().frameOptions().disable() se utiliza para desactivar la configuración de los encabezados de opciones de marco
        // en las respuestas HTTP, lo que permite que el contenido de la aplicación sea incrustado en iframes o frames de otros sitios web.
        // Sin embargo, esto puede exponer la aplicación a riesgos de seguridad relacionados con el clickjacking,
        // por lo que se deben considerar otras medidas de seguridad adecuadas.


        // if user is not authenticated, just send an authentication failure response: //cuando el usuario quiere entrar en algun del sitio web me fijo si esta o no autenticado

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication:

        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req)); //ejecuta el metodo de abajo para manejar el exito del login

        // if login fails, just send an authentication failure response:

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED)); //si login falla manda un mns,
        // req: peticion q recibimos del cliente, res: respuesta que vamos a mandar, exc: excepción


        // if logout is successful, just send a success response:

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        //Esta clase se utiliza para manejar el éxito de la operación de logout en una aplicación web protegida por Spring Security.
        // Cuando el usuario inicia sesión, se crea una sesión para él en el servidor, y cuando el usuario cierra sesión,
        // se debe eliminar la sesión.
        //La clase "HttpStatusReturningLogoutSuccessHandler" es responsable de devolver una respuesta HTTP adecuada después
        // de que el usuario haya cerrado sesión correctamente en la aplicación. Por defecto,
        // devuelve un código de estado HTTP 200 (OK), lo que indica que la operación se ha completado con éxito.

        return http.build();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }

}
