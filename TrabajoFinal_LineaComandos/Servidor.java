package TrabajoFinal_LineaComandos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {
	public static void main(String[] args) {
		try (ServerSocket ss = new ServerSocket(1111)){
			Runtime r = Runtime.getRuntime();
			int nNucleos = r.availableProcessors();
			ExecutorService pool = Executors.newFixedThreadPool(nNucleos);
			while(true) {
				Socket s;
				try{
					s = ss.accept();
					Servidor_Hilo hilo = new Servidor_Hilo(s);
					Runnable runnable = hilo;
					Thread t = new Thread(runnable);
					pool.execute(t);
				}catch (IOException e) {
					e.printStackTrace();
				}
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
