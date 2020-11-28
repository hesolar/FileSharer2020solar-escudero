package ejercicioDescargaArchivosConHilos;


import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Hilo implements Runnable {

	long a, b;
	CyclicBarrier b1;
	URL u;

	public Hilo(URL u, CyclicBarrier b1, long a, long b) {
		super();
		this.b1 = b1;
		this.u = u;
		this.a = a;
		this.b = b;
	}

	@Override
	public void run() {
		try (DataInputStream din = new DataInputStream(u.openStream());) {
			b1.await();

			HttpURLConnection con = (HttpURLConnection) u.openConnection();
			con.setDoInput(true);
			con.setRequestProperty("Range", "Bytes=" + a + "-" + b);

			File f = new File("imagen.png");
			RandomAccessFile d = new RandomAccessFile(f, "rws");
			d.seek(a);
			int leidos;

			byte b[] = new byte[1024];

			while ((leidos = din.read(b)) != -1) {
				d.write(b, 0, leidos);

			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
