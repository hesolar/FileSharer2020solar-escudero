package ClienteServidor_CompartirDirectorio;



import java.io.DataInputStream;
import java.io.DataOutputStream;
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
	String path;
	DataOutputStream dos;
	DataInputStream dis;


	public Hilo(DataOutputStream dos,DataInputStream dis,CyclicBarrier b1, long a, long b,String path) {
		super();
		this.b1 = b1;
		this.dos=dos;
		this.dis=dis;
		this.a = a;
		this.b = b;
	}

	@Override
	public void run() {
		try {
			
			b1.await();


			File f = new File(path);
			RandomAccessFile d = new RandomAccessFile(f.getPath(), "rws");
			d.seek(a);
			int leidos;

			byte b[] = new byte[1024];

			while ((leidos = dis.read(b)) != -1) {
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