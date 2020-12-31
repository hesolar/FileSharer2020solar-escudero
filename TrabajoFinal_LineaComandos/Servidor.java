package TrabajoFinal_LineaComandos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor extends Thread{
	
	private static int puerto = 1111;
	private static List<Socket> clientes;
	private static ServerSocket ss = null;
	private static ExecutorService pool;
	
	public Servidor(int puert) {
		try {
			
			puerto=puert;
			clientes= new ArrayList<>();
			ss= new ServerSocket(puerto);
			Runtime r = Runtime.getRuntime();
			int nNucleos = r.availableProcessors();
			pool = Executors.newFixedThreadPool(nNucleos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Socket> getClientes() {
		return clientes;
	}
	
	public void apagar() {
		try {
			if(ss!=null) {
				ss.close();
				pool.shutdownNow();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		Socket s;
		while (!this.ss.isClosed()){	
			try {
					s = ss.accept();
					clientes.add(s);
					Servidor_Hilo hilo = new Servidor_Hilo(s);
					Runnable runnable = hilo;
					Thread t = new Thread(runnable);
					pool.execute(t);
					for (Socket n : clientes) {
						if (!n.isConnected()) {
							clientes.remove(n);
						}
					}
			} catch (SocketException a) {
				System.out.println("Servidor Cerrado");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		apagar();
	}
}
