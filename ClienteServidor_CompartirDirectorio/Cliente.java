package ClienteServidor_CompartirDirectorio;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cliente {

	private static String path;
	
	
	//Hace elegir al usuario un directorio válido de trabajo s
	public static void seleccionDirectorioTrabajo() {
		System.out.println("INTRODUCE DONDE SE GUARDARAN TUS ARCHIVOS DESCARGADOS");
		Scanner es = new Scanner(System.in);
		String linea=es.nextLine();
		
		File f = new File(linea);
		if(f.isDirectory()) path= f.getAbsolutePath();
		else {
			System.out.println("El directorio no es correcto");
			seleccionDirectorioTrabajo();
		}
		
	}
	
	
	public static void main(String[] args) {
		try {
			seleccionDirectorioTrabajo();
			String  s1;

			Scanner es = new Scanner(System.in);
			boolean end=true;
			while(end) {
				System.out.println("\nINTRODUCE ORDEN\n");
				s1=es.nextLine();
				
				
				if(s1.equalsIgnoreCase("exit"))end=false;		
				
				else {
					Socket s = new Socket("localhost",1111);
					DataInputStream dis=new DataInputStream(s.getInputStream());
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					
					
					if(s1.startsWith("select")) {
						
						if(s1.startsWith("selectall")) selectallseq(dos,dis,s1);
						else select(dos,dis,s1);
					}
					else {
					
					
					
					dos.writeBytes(s1+"\r\n");
					String a;
					while((a=dis.readLine())!=null) {
						System.out.println(a);
						
					}
					
					}
				}
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	private static void selectall(DataOutputStream dos, DataInputStream dis) {
//		ExecutorService pool = Executors.newCachedThreadPool();
//		try {
//			
//			int numProcesadores = Runtime.getRuntime().availableProcessors();
//			String linea = dis.readLine();
//			String particion[]= linea.split(" ");
//			
//			
//			File f= new File(particion[1]);
//			FileInputStream fis= new FileInputStream(f);
//			
//			Long l = f.getTotalSpace();
//			CyclicBarrier barrier = new CyclicBarrier(Runtime.getRuntime().availableProcessors()+1);
//			List<Hilo> lista = new ArrayList();
//			
//			//DataOutputStream dos,DataInputStream dis,CyclicBarrier b1, long a, long b,String path
//			
//			long c,d;
//			c=0;
//			d=(l/numProcesadores)-1;
//			for(int i= 0 ;i<numProcesadores;i++) {				
//				lista.add(new Hilo(dos,dis,barrier,c,d,path));
//				c=d+1;
//				d=d+(l/numProcesadores);
//			}
//			
//			
//			for(int i=0;i<lista.size();i++) {
//				pool.submit(lista.get(i));
//			}
//			
//			barrier.await();
//			
//			
//			
//		} catch (InterruptedException | BrokenBarrierException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}


	// funcionalidad download que recibe el nombre de un fichero y luego el fichero
	public static void select(DataOutputStream dos, DataInputStream dis, String LineaLeida) {
		
		FileOutputStream fos=null;
		try {
			dos.writeBytes(LineaLeida + "\r\n");

			String pathNuevo=path+"\\"+dis.readLine();
			System.out.println("el path nuevo es"+pathNuevo);
			File f = new File(pathNuevo);
			
			System.out.println("\n La nueva ubicación del archivo es: " + f.getAbsolutePath() + "\n");

			fos = new FileOutputStream(f);
			byte b[] = new byte[1024];
			int leidos;
			while ((leidos = dis.read(b)) != -1) {
				fos.write(b, 0, leidos);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			
				try {
					if(fos!=null)fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	public static void selectallseq(DataOutputStream dos, DataInputStream dis, String LineaLeida) {
		
		FileOutputStream fos=null;
		try {
			dos.writeBytes(LineaLeida + "\r\n");

			String pathNuevo=path+"\\"+dis.readLine();
			System.out.println("el path nuevo es"+pathNuevo);
			File f = new File(pathNuevo);
			
			System.out.println("\n La nueva ubicación del archivo es: " + f.getAbsolutePath() + "\n");

			fos = new FileOutputStream(f);
			byte b[] = new byte[1024];
			int leidos;
			while ((leidos = dis.read(b)) != -1) {
				fos.write(b, 0, leidos);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			
				try {
					if(fos!=null)fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	
	
	
	
	
}
