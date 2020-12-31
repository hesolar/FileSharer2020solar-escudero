package TrabajoFinal_LineaComandos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor extends Thread{
	
	private static int puerto = 1111;
	private static List<Socket> clientes;
	
	public Servidor(int puert) {
		puerto=puert;
		clientes= new ArrayList<>();
	}
	
	public static List<Socket> getClientes() {
		return clientes;
	}
	
	public void run() {
		try (ServerSocket ss = new ServerSocket(puerto)){
			Runtime r = Runtime.getRuntime();
			int nNucleos = r.availableProcessors();
			ExecutorService pool = Executors.newFixedThreadPool(nNucleos);
			while(true) {
				Socket s;
				try{
					s = ss.accept();
					clientes.add(s);
					Servidor_Hilo hilo = new Servidor_Hilo(s);
					Runnable runnable = hilo;
					Thread t = new Thread(runnable);
					pool.execute(t);
					for(Socket n : clientes) {
						if(!n.isConnected()) {
							clientes.remove(n);
						}
					}
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
