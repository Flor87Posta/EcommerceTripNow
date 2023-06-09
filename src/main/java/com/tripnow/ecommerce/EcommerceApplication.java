package com.tripnow.ecommerce;
import com.tripnow.ecommerce.models.*;
import com.tripnow.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class EcommerceApplication {


	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
	public CommandLineRunner initData(ClienteRepositorio clienteRepositorio, OrdenRepositorio ordenRepositorio, PaqueteRepositorio paqueteRepositorio, HotelRepositorio hotelRepositorio, DestinoRepositorio destinoRepositorio, PasajeRepositorio pasajeRepositorio, ExcursionRepositorio excursionRepositorio) {
		return (args) -> {


			Cliente cliente1 = new Cliente("Melba", "Morel", "X12455888", "Lomas de Zamora 222", "011-41216666", "melba@mindhub.com", passwordEncoder.encode("555669812"), new Date(56/06/06));
			Cliente cliente2 = new Cliente("Lucia", "Lopez", "X33134665", "Los Paraisos 556", "0351-4224455", "lucia@mindhub.com", passwordEncoder.encode("555666"), new Date(87/11/06) );
			Cliente cliente3 = new Cliente("Ines", "Garcia", "X40111666", "Tintines 22", "0344-4258899", "ines@mindhub.com", passwordEncoder.encode("111555"), new Date(90/10/10));
			Cliente admin = new Cliente ("Admin", "Admin", "X8754785487", "Admin", "45698-55465", "florys_211@hotmail.com", passwordEncoder.encode("admin"), new Date (90/05/02));

			clienteRepositorio.save(cliente1);
			clienteRepositorio.save(cliente2);
			clienteRepositorio.save(cliente3);
			clienteRepositorio.save(admin);

			Hotel hotel1 = new Hotel("Andes Retreat", HotelCategoria.Tres_Estrellas, false, false,true, 50000, 10);
			Hotel hotel2 = new Hotel("Mariposa Bay Resort", HotelCategoria.Cinco_Estrellas, true, true, true, 100000, 10);
			Hotel hotel3 = new Hotel("Pampa Oasis Lodge", HotelCategoria.Cuatro_Estrellas, true, true, true, 95000, 10);
			Hotel hotel4 = new Hotel("Tango Palace", HotelCategoria.Dos_Estrellas, false, true, false, 80000, 10);
			Hotel hotel5 = new Hotel("Grand Resort Mar del Plata", HotelCategoria.Cinco_Estrellas, true, true, true, 90000, 10);
			Hotel hotel6 = new Hotel("Hotel del Sol", HotelCategoria.Cinco_Estrellas, true, true, true, 10000, 10);
			Hotel hotel7 = new Hotel("Gran Hotel Lago Azul", HotelCategoria.Tres_Estrellas, false, false, true, 50000, 10);
			Hotel hotel8 = new Hotel("Posada de los Colores", HotelCategoria.Dos_Estrellas, false, false, false, 30000, 10);
			Hotel hotel9 = new Hotel("Sheraton", HotelCategoria.Cinco_Estrellas, false, true, false, 30000, 10);

			Excursion excursion1 = new Excursion ("Ruta de Vinos", "Aire Libre", 4000, 20 );
			Excursion excursion2 = new Excursion ("Cerro Cristo Rey", "Aire Libre", 4000, 20 );
			Excursion excursion3 = new Excursion ("Cerro Catedral", "Nieve", 8000, 20 );
			Excursion excursion4 = new Excursion ("Colonia Suiza", "Comidas", 5000, 20 );
			Excursion excursion5 = new Excursion ("Parque Iguazú", "Aire Libre", 6000, 20 );
			Excursion excursion6 = new Excursion ("Minas de Wanda", "Aire Libre", 5000, 20 );
			Excursion excursion7 = new Excursion ("Parque Los Glaciares", "Nieve", 9000, 20 );
			Excursion excursion8 = new Excursion ("Torres de Paine", "Aire Libre", 10000, 20 );

			Destino destino1 = new Destino("Santa Cruz", new ArrayList<>(Arrays.asList(3, 5, 10, 15)), hotel1.getPrecioHotel()+excursion7.getPrecioExcursion()+excursion8.getPrecioExcursion());
			Destino destino2 = new Destino("Bariloche", new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10)), hotel2.getPrecioHotel()+excursion3.getPrecioExcursion()+excursion4.getPrecioExcursion());
			Destino destino3 = new Destino("Mendoza", new ArrayList<>(Arrays.asList(7, 13, 15, 20)), hotel3.getPrecioHotel()+excursion1.getPrecioExcursion()+excursion2.getPrecioExcursion());
			Destino destino4 = new Destino("Cataratas del Iguazú", new ArrayList<>(Arrays.asList(2, 3, 6, 10)), hotel4.getPrecioHotel()+excursion6.getPrecioExcursion()+excursion5.getPrecioExcursion());
			Destino destino5 = new Destino("Mar del Plata", new ArrayList<>(Arrays.asList(2, 3, 7)), hotel5.getPrecioHotel()+excursion7.getPrecioExcursion());
			Destino destino6 = new Destino("Carilo", new ArrayList<>(Arrays.asList(6, 8, 10)), hotel6.getPrecioHotel()+excursion8.getPrecioExcursion()+excursion1.getPrecioExcursion());
			Destino destino7 = new Destino("Carlos Paz", new ArrayList<>(Arrays.asList(1, 2, 3)), hotel7.getPrecioHotel()+excursion2.getPrecioExcursion()+excursion3.getPrecioExcursion());
			Destino destino8 = new Destino("Cerro de los Siete Colores(Purmamarca)", new ArrayList<>(Arrays.asList(4, 8, 10, 12)), hotel8.getPrecioHotel()+excursion6.getPrecioExcursion()+excursion4.getPrecioExcursion());
			Destino destino9 = new Destino("Cordoba Capital", new ArrayList<>(Arrays.asList(4, 8, 10, 12)), hotel9.getPrecioHotel()+excursion6.getPrecioExcursion()+excursion4.getPrecioExcursion());

			destinoRepositorio.save(destino1);
			destinoRepositorio.save(destino2);
			destinoRepositorio.save(destino3);
			destinoRepositorio.save(destino4);
			destinoRepositorio.save(destino5);
			destinoRepositorio.save(destino6);
			destinoRepositorio.save(destino7);
			destinoRepositorio.save(destino8);
			destinoRepositorio.save(destino9);

			destino1.añadirHotel(hotel1);
			destino2.añadirHotel(hotel2);
			destino3.añadirHotel(hotel3);
			destino4.añadirHotel(hotel4);
			destino5.añadirHotel(hotel5);
			destino6.añadirHotel(hotel6);
			destino7.añadirHotel(hotel7);
			destino8.añadirHotel(hotel8);
			destino9.añadirHotel(hotel9);

			hotelRepositorio.save(hotel1);
			hotelRepositorio.save(hotel2);
			hotelRepositorio.save(hotel3);
			hotelRepositorio.save(hotel4);
			hotelRepositorio.save(hotel5);
			hotelRepositorio.save(hotel6);
			hotelRepositorio.save(hotel7);
			hotelRepositorio.save(hotel8);
			hotelRepositorio.save(hotel9);

			Pasaje pasaje1 = new Pasaje(TipoPasaje.AEREO, "Aerolineas Argentinas", 15000, 20);
			Pasaje pasaje2 = new Pasaje(TipoPasaje.AEREO, "Lan Chile", 15000, 20);
			Pasaje pasaje3 = new Pasaje(TipoPasaje.AEREO, "FlyBondi", 15000, 20);
			Pasaje pasaje4 = new Pasaje(TipoPasaje.TERRESTRE, "FlechaBus", 20000, 20);
			Pasaje pasaje5 = new Pasaje(TipoPasaje.TERRESTRE, "Cata", 20000, 20);
			Pasaje pasaje6 = new Pasaje(TipoPasaje.TERRESTRE, "Andesmar", 20000, 20);

			pasajeRepositorio.save(pasaje1);
			pasajeRepositorio.save(pasaje2);
			pasajeRepositorio.save(pasaje3);
			pasajeRepositorio.save(pasaje4);
			pasajeRepositorio.save(pasaje5);
			pasajeRepositorio.save(pasaje6);


			destino1.añadirExcursion(excursion7);
			destino1.añadirExcursion(excursion8);
			destino2.añadirExcursion(excursion3);
			destino2.añadirExcursion(excursion4);
			destino3.añadirExcursion(excursion1);
			destino3.añadirExcursion(excursion2);
			destino4.añadirExcursion(excursion5);
			destino4.añadirExcursion(excursion6);

			excursionRepositorio.save(excursion1);
			excursionRepositorio.save(excursion2);
			excursionRepositorio.save(excursion3);
			excursionRepositorio.save(excursion4);
			excursionRepositorio.save(excursion5);
			excursionRepositorio.save(excursion6);
			excursionRepositorio.save(excursion7);
			excursionRepositorio.save(excursion8);

			destinoRepositorio.save(destino1);
			destinoRepositorio.save(destino2);
			destinoRepositorio.save(destino3);
			destinoRepositorio.save(destino4);
			destinoRepositorio.save(destino5);
			destinoRepositorio.save(destino6);
			destinoRepositorio.save(destino7);
			destinoRepositorio.save(destino8);

			Paquete paquete1 = new Paquete ("Volá y conocé Mendoza", 13, destino3.getPrecioHotelExcursion()+pasaje1.getPrecioPasaje(), 10, "/assets/mendoza.jpg", destino3.getHoteles());
			Paquete paquete2 = new Paquete ("Volá y conocé Cataratas", 6, destino4.getPrecioHotelExcursion()+pasaje2.getPrecioPasaje(),10, "/assets/cataratas.jpg", destino4.getHoteles());
			Paquete paquete3 = new Paquete ("Conoce Bariloche via FlechaBus", 8, destino2.getPrecioHotelExcursion()+pasaje4.getPrecioPasaje(),10, "/assets/Centro_Civico_y_Puerto_San_Carlos_en_Bariloche.jpg", destino2.getHoteles());
			Paquete paquete4 = new Paquete ("Conoce Santa Cruz con Flybondi", 5, destino1.getPrecioHotelExcursion()+pasaje3.getPrecioPasaje(),10, "/assets/Glaciar2.jpg", destino1.getHoteles());
			Paquete paquete5 = new Paquete("Escapada Costera a Mar del Plata", 9, destino5.getPrecioHotelExcursion()+pasaje5.getPrecioPasaje(), 10, "/assets/marDelPlata.jpg", destino5.getHoteles());
			Paquete paquete6 = new Paquete("Mar y bosque, combinación perfecta en Cariló ", 8, destino6.getPrecioHotelExcursion()+pasaje6.getPrecioPasaje(), 10, "/assets/carilo.jpg", destino6.getHoteles());
			Paquete paquete7 = new Paquete("Descubriendo el Encanto de Carlos Paz", 5, destino7.getPrecioHotelExcursion()+pasaje2.getPrecioPasaje(), 10, "/assets/carlosPaz.jpg", destino7.getHoteles());
			Paquete paquete8 = new Paquete("Experiencia Multicolor en el Cerro de los Siete Colores", 15, destino8.getPrecioHotelExcursion()+pasaje1.getPrecioPasaje(), 10, "/assets/jujuy.jpg", destino8.getHoteles());
			Paquete paquete9 = new Paquete("Conociendo la historica Cordoba", 4, destino9.getPrecioHotelExcursion()+pasaje1.getPrecioPasaje(), 10, "/assets/cordobaCapital.jpg", destino9.getHoteles());

			paqueteRepositorio.save(paquete1);
			paqueteRepositorio.save(paquete2);
			paqueteRepositorio.save(paquete3);
			paqueteRepositorio.save(paquete4);
			paqueteRepositorio.save(paquete5);
			paqueteRepositorio.save(paquete6);
			paqueteRepositorio.save(paquete7);
			paqueteRepositorio.save(paquete8);
			paqueteRepositorio.save(paquete9);

			destino1.añadirDestinoAlPaquete(paquete4);
			destino2.añadirDestinoAlPaquete(paquete3);
			destino3.añadirDestinoAlPaquete(paquete1);
			destino4.añadirDestinoAlPaquete(paquete2);
			destino5.añadirDestinoAlPaquete(paquete5);
			destino6.añadirDestinoAlPaquete(paquete6);
			destino7.añadirDestinoAlPaquete(paquete7);
			destino8.añadirDestinoAlPaquete(paquete8);
			destino9.añadirDestinoAlPaquete(paquete9);

			destinoRepositorio.save(destino1);
			destinoRepositorio.save(destino2);
			destinoRepositorio.save(destino3);
			destinoRepositorio.save(destino4);
			destinoRepositorio.save(destino5);
			destinoRepositorio.save(destino6);
			destinoRepositorio.save(destino7);
			destinoRepositorio.save(destino8);
			destinoRepositorio.save(destino9);

			pasaje1.añadirPasajeAlPaquete(paquete1);
			pasaje1.añadirPasajeAlPaquete(paquete2);
			pasaje4.añadirPasajeAlPaquete(paquete3);
			pasaje3.añadirPasajeAlPaquete(paquete4);
			pasaje5.añadirPasajeAlPaquete(paquete5);
			pasaje6.añadirPasajeAlPaquete(paquete6);
			pasaje2.añadirPasajeAlPaquete(paquete7);
			pasaje1.añadirPasajeAlPaquete(paquete8);
			pasaje1.añadirPasajeAlPaquete(paquete9);

			destino5.añadirDestinoAlPaquete(paquete5);
			destino6.añadirDestinoAlPaquete(paquete6);
			destino7.añadirDestinoAlPaquete(paquete7);
			destino8.añadirDestinoAlPaquete(paquete8);
			destino9.añadirDestinoAlPaquete(paquete9);

			pasajeRepositorio.save(pasaje1);
			pasajeRepositorio.save(pasaje2);
			pasajeRepositorio.save(pasaje3);
			pasajeRepositorio.save(pasaje4);
			pasajeRepositorio.save(pasaje5);
			pasajeRepositorio.save(pasaje6);

			paqueteRepositorio.save(paquete1);
			paqueteRepositorio.save(paquete2);
			paqueteRepositorio.save(paquete3);
			paqueteRepositorio.save(paquete4);
			paqueteRepositorio.save(paquete5);
			paqueteRepositorio.save(paquete6);
			paqueteRepositorio.save(paquete7);
			paqueteRepositorio.save(paquete8);
			paqueteRepositorio.save(paquete9);

			Orden orden1 = new Orden(LocalDateTime.now(), true, 2, paquete1.getPrecioTotalUnitario()*paquete1.getDias(), paquete1.getPrecioTotalUnitario()*paquete1.getDias()*2, false);
			Orden orden2 = new Orden(LocalDateTime.now(), true, 3, paquete2.getPrecioTotalUnitario()*paquete2.getDias(), paquete2.getPrecioTotalUnitario()*paquete2.getDias()*3, false);
			Orden orden3 = new Orden(LocalDateTime.now(), true, 4, paquete3.getPrecioTotalUnitario()*paquete3.getDias(), paquete3.getPrecioTotalUnitario()*paquete3.getDias()*4, false);
			Orden orden4 = new Orden(LocalDateTime.now(), true, 4, paquete4.getPrecioTotalUnitario()*paquete4.getDias(), paquete4.getPrecioTotalUnitario()*paquete4.getDias()*2,false);


			orden1.añadirPaquete(paquete1);
			orden2.añadirPaquete(paquete2);
			orden3.añadirPaquete(paquete3);
			orden4.añadirPaquete(paquete4);


			paqueteRepositorio.save(paquete1);
			paqueteRepositorio.save(paquete2);
			paqueteRepositorio.save(paquete3);
			paqueteRepositorio.save(paquete4);
			paqueteRepositorio.save(paquete5);
			paqueteRepositorio.save(paquete6);
			paqueteRepositorio.save(paquete7);
			paqueteRepositorio.save(paquete8);
			paqueteRepositorio.save(paquete9);

			cliente1.añadirOrden(orden1);
			cliente1.añadirOrden(orden2);
			cliente2.añadirOrden(orden3);
			cliente3.añadirOrden(orden4);

			ordenRepositorio.save(orden1);
			ordenRepositorio.save(orden2);
			ordenRepositorio.save(orden3);
			ordenRepositorio.save(orden4);

			clienteRepositorio.save(cliente1);
			clienteRepositorio.save(cliente2);
			clienteRepositorio.save(cliente3);

		};
	}
}
