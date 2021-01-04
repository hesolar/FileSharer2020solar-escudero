package content;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class Servidor_Hilo implements Runnable {

	private static String path;
	private static Socket s;
	private static Semaphore semaphore;

	public Servidor_Hilo(Socket soc,Semaphore s1) {
		s = soc;
		semaphore=s1;
	}

//FUNCIONALIDADES SERVIDOR : mostrar,cd,.., directorio, select

//  funcionalidad cd(Change Directory) 

	public static void cd(String linea, DataOutputStream dos) {

		linea = CA.CortarFichero(linea);
		linea = CA.conversorDireccionesAbsolutas(linea, path);
		File f = new File(linea);

		if (!f.exists()) {
			try {
				dos.writeBytes("Directorio Erroneo, vuelves a: " + path + "\r\n");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else path = CA.conversorDireccionesAbsolutas(linea, path);

	}

//	  aplica funcionalidad .., cambia al directorio padre el directorio de trabajo a
	public static void dosPuntos(DataOutputStream dos) {
		File f = new File(path);
		f = f.getParentFile();
		path = f.getPath();
		CA.EnviarDirectorioPorSalida(dos, f);
	}

	// Selecciona un archivo y lo envia
	private static void select(DataOutputStream dos, String linea) {
		String oldPath = path;
		path = CA.conversorDireccionesAbsolutas(CA.CortarFichero(linea), path);

		File f = new File(path);
		try (FileInputStream fis = new FileInputStream(f);) {

			dos.writeLong(f.length());
			dos.writeBytes(CA.conversorDireccionesRelativas(f.getPath()) + "\r\n");
			byte b[] = new byte[1024];
			int leidos;

			while ((leidos = fis.read(b)) != -1) {
				dos.write(b, 0, leidos);
			}

			path = oldPath;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	//Dado un directorio lo zipea y lo envia, es necesario moverse primero al directorio anterior que el que se quiere pedir
	//Es decir si queremos c:  a/b/c
	//debemos estar trabajando en directorio b;(opcion cd a/b)
	private static void selectAll(DataOutputStream dos, String linea) {

		File f;
		String destino;

		if (path.equals("C:\\")) {
			f = new File(linea);
			linea = CA.CortarFichero(linea);
		} else {
			f = new File(path + File.separator + CA.CortarFichero(linea));
			linea = path + File.separator + CA.CortarFichero(linea);

		}

		destino = f.getParent() + File.separator + "a.zip";
		path = f.getParent();

		ZipUses.ZipDirectory(linea, destino);
		select(dos, "select " + destino);
		f = new File(destino);
		f.delete();

	}

//----------------------------------------------------------------------\\

	
//Metodo ppal del hilo
	public void run() {
		path = "C:\\";
		boolean b = true;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		String linea = null;
		String orden;
		try {
			while (b) {
			

				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				linea = dis.readLine();
				System.out.println("Orden recibida:" + linea+": Hilo:"+Thread.currentThread().getName());

				if (linea == null || s.isInputShutdown() || s.isOutputShutdown() || s.isClosed()) {
					b = false;
					linea = "exit";
					System.out.println("Cliente desconectado");
				} else {

					if (linea.split(" ").length > 1)orden = CA.CortarOrden(linea);
					else orden = linea;

					switch (orden) {
					case "cd":
						cd(linea, dos);
						dos.writeBytes(";\r\n");
						break;
					case "..":
						dosPuntos(dos);
						dos.writeBytes(";\r\n");
						break;
					case "show":
						CA.EnviarDirectorioPorSalida(dos, new File(path));
						dos.writeBytes(";\r\n");
						break;
					case "select":
						select(dos, linea);

						break;
					case "selectall":
						selectAll(dos, linea);
						break;
					case "exit":
						b = false;
						break;
					default:
						dos.writeBytes("Comando erroneo \r\n");
						dos.writeBytes(";\r\n");
						break;
					}
					System.out.println("Proceso de orden terminado,esperando siguietne orden\n");
				
				
				}
			

		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Hilo cerrado de forma abrupta");
			//e.printStackTrace();
		}
		finally {
		System.out.println(Thread.currentThread().getName()+ ": terminado");
		 semaphore.release();	
		}
		
	}

}