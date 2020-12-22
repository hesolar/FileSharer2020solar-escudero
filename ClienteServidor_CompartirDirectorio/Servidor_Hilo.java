package ClienteServidor_CompartirDirectorio;

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
		s=soc;
	}

	public String getPath() {
		return this.path;
	}

	public class Objetos implements Serializable {
		private final long SerialVersionUID = 111L;
		private List<File> listado;

		public Objetos(List<File> f) {
			this.listado = f;
		}

		public List<File> getListado() {
			return this.listado;
		}
	}

//FUNCIONALIDADES SERVIDOR : mostrar,cd,.., directorio, select

//	  aplica funcionalidad cd, 

	public static void cd(String linea, DataOutputStream dos) {

		linea = CA.CortarFichero(linea);
		linea = CA.conversorDireccionesAbsolutas(linea, path);
		File f = new File(linea);

		if (!f.exists()) {
			try {
				dos.writeBytes("Directorio Erroneo, vuelves a: " + path);

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

		path = CA.conversorDireccionesAbsolutas(CA.CortarFichero(linea), path);

		File f = new File(path);
		try (FileInputStream fis = new FileInputStream(f);) {
			System.out.println(CA.conversorDireccionesRelativas(f.getPath()));
			dos.writeBytes(CA.conversorDireccionesRelativas(f.getPath()) + "\r\n");
			byte b[] = new byte[1024];
			int leidos;

			while ((leidos = fis.read(b)) != -1) {
				dos.write(b, 0, leidos);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private static void selectAll(DataOutputStream dos, String linea) {

		File f = new File(CA.CortarFichero(linea));
		if (f.isFile())select(dos, linea);
			

		else {
			
			
			try (FileOutputStream fos = new FileOutputStream(f);
					ZipOutputStream zipOut = new ZipOutputStream(fos);
					FileInputStream fis = new FileInputStream(f);

			) {
				f.createNewFile();
				ZipEntry zipEntry = new ZipEntry(f.getName());
				zipOut.putNextEntry(zipEntry);

				byte[] bytes = new byte[1024];
				int length;
				while ((length = fis.read(bytes)) >= 0) {
					zipOut.write(bytes, 0, length);
				}

				select(dos, f.getPath());
//				f.delete();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

//----------------------------------------------------------------------\\

	public void run() {
		path = "C:\\";
	    boolean b = true;
	    DataInputStream dis=null;
	    DataOutputStream dos=null;
		while (b) {
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				String linea = dis.readLine();
				String orden = CA.CortarOrden(linea);
				String dest = CA.CortarFichero(linea);
				
					switch(orden) {
						case "cd":
						if(new File(dest).exists()) {
							cd(linea, dos);
						}else {
							dos.writeBytes("Destino erroneo \r\n");
						}
						break;
						case "..":
							dosPuntos(dos);
						break;
						case "show":
							CA.EnviarDirectorioPorSalida(dos, new File(path));
						break;
						case "select":
							select(dos, linea);
						break;
						case "selectall":
							linea = CA.CortarFichero(linea);
							linea = CA.conversorDireccionesAbsolutas(linea, path);
							selectAll(dos, linea);
						break;
						case "exit":
							b=false;
							s.close();
						break;
						default:
							dos.writeBytes("Comando erroneo \r\n");
	                    break;
					}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}