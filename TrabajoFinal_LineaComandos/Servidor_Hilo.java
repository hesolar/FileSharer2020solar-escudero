package TrabajoFinal_LineaComandos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Servidor_Hilo implements Runnable {

	private static String path;
	private static Socket s;

	public Servidor_Hilo(Socket soc) {
		s = soc;
	}

//FUNCIONALIDADES SERVIDOR : mostrar,cd,.., directorio, select

//	  aplica funcionalidad cd, 

	public static void cd(String linea, DataOutputStream dos) {

		linea = CA.CortarFichero(linea);
		linea = CA.conversorDireccionesAbsolutas(linea, path);
		File f = new File(linea);

		if (!f.exists()) {
			try {
				dos.writeBytes("Directorio Erroneo, vuelves a: " + path +"\r\n");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else
			path = CA.conversorDireccionesAbsolutas(linea, path);

//		ClaseMetodosAuxiliares.EnviarDirectorioPorSalida(dos,new File(path));
	}

//	  aplica funcionalidad .., cambia al directorio padre el directorio de trabajo
	public static void dosPuntos(DataOutputStream dos) {
		File f = new File(path);
		f = f.getParentFile();
		path = f.getPath();
		CA.EnviarDirectorioPorSalida(dos, f);
	}

	// Selecciona un archivo y lo envía
	private static void select(DataOutputStream dos, String linea) {
		String oldPath=path;
		path = CA.conversorDireccionesAbsolutas(CA.CortarFichero(linea), path);

		File f = new File(path);
		try (FileInputStream fis = new FileInputStream(f);) {
			System.out.println(CA.conversorDireccionesRelativas(f.getPath()));
			
			dos.writeLong(f.length());
			dos.writeBytes(CA.conversorDireccionesRelativas(f.getPath()) + "\r\n");
			byte b[] = new byte[1024];
			int leidos;

			
			
			while ((leidos = fis.read(b)) != -1) {
				dos.write(b, 0, leidos);
			}
			
			
			path=oldPath;
			
			
			
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private static void selectAll(DataOutputStream dos, String linea) {
		
		File f;
		String destino;
		

		if(path.equals("C:\\")){
		f = new File(linea);
		linea=CA.CortarFichero(linea);
		}
		else {
			f=new File(path+File.separator+CA.CortarFichero(linea));
			linea=path+File.separator+CA.CortarFichero(linea);

		}
		
		destino=f.getParent()+File.separator+"a.zip";
		path=f.getParent();
		
		ZipUses.ZipDirectory(linea, destino);
		select(dos,"select " +destino);	
		f= new File(destino);
		f.delete();
		
	}

//----------------------------------------------------------------------\\

	public void run() {
		path = "C:\\";
		boolean b = true;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		String linea=null;

		
		while (b) {
			try {
				
				
				
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				linea=dis.readLine();
				
				
				if(linea==null||s.isInputShutdown()||s.isOutputShutdown()||s.isClosed()) {
					b=false;
					linea="exit";
					
				}
				else {
				

				
				
				
				String orden;
				//.. cd .... cd hola
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
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	
	}

}