package content;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

//Clase Servidor multihilo
public class Servidor {
	//Solo permitiremos n conexiones, en este caso solo 2 clientes simultaneos, al ser mecanismos de solo lectura no se producen
	//errores de concurrencia entre los diferentes hilos ,por tanto podría haber varios clientes
	private static Semaphore maxHilos= new Semaphore(2);
	private static Integer Puerto;
	// Inicio del servidor
	
	
	public static void main(String[] args) {

		System.out.println("La ip pública de este servidor es;");
		System.out.println(CA.ipPublica());
		
		
		SeleccionPuerto();
		System.out.println("Puerto correcto, servidor alojado en el puerto:" +Puerto);
		System.out.println("Listo para conectar, esperando petición");
		System.out.println("------------------------------------------");
		Socket s;
		String nombreHilo;
		try (ServerSocket ss = new ServerSocket(Puerto)) {
			int numHilo=0;
			ExecutorService pool = Executors.newFixedThreadPool(4);
			while (true) {
				
				try {
					maxHilos.acquire();
					s = ss.accept();
					
					Servidor_Hilo hilo = new Servidor_Hilo(s,maxHilos);
					
					Thread t = new Thread(hilo);
					nombreHilo="Hilo nº"+numHilo;
					t.setName(nombreHilo);
					System.out.println(nombreHilo + " conectado");
					pool.execute(hilo);
					numHilo++;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	//Funcion para seleccionar el puerto donde se hostea el servidor
	public static void SeleccionPuerto() {
		
		
		Integer Puert;
		
		try {
			Scanner es = new Scanner(System.in);
			System.out.println("introduce el puerto para el servidor");
			String puerto;
			
			puerto = es.nextLine();
			es.close();
			Puert= Integer.parseInt(puerto);
			if (Puert < 1024 || Puert > 49151)throw new NumberFormatException();
			else Puerto=Puert;
		}

		catch (NumberFormatException e) {
			System.out.println("Por favor introduce un número entre 1024 al 49151");
			 SeleccionPuerto();
		}
		
		
		
	}
	
	
	
	

}