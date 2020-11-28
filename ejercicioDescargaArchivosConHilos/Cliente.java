package ejercicioDescargaArchivosConHilos;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Cliente {

	public static void main(String[] args) {
		
		ExecutorService pool=null;
		
		try {
			URL u = new URL("https://www.google.es/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
			HttpURLConnection con = (HttpURLConnection) u.openConnection();
			 con.setRequestMethod("HEAD");
			 long tamaño = con.getContentLengthLong();
		
			 CyclicBarrier b = new CyclicBarrier(4);
			 pool = Executors.newFixedThreadPool(3);
		
			 List<Hilo>	l = new ArrayList();
			 l.add( new Hilo( u,b, (long) 0,(tamaño-1)/3 ));
			 l.add( new Hilo(u,b,(tamaño/3),(tamaño-1)/2));
			 l.add( new Hilo(u,b,(tamaño-1)/2,tamaño-1));
		
			 
			 pool.submit(l.get(0));
			 pool.submit(l.get(1));
			 pool.submit(l.get(2));
			 b.await();
			 
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if(pool!=null) pool.shutdown();
		}
		
	}
	
	
	
}
