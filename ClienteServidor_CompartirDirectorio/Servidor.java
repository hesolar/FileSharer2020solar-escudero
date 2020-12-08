package ClienteServidor_CompartirDirectorio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {

	private static String path;

	public Servidor(String s) {
		this.path = s;
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
	 
	public static void cd(String linea,DataOutputStream dos) {
		
		linea=CA.CortarOrdenFichero(linea);
		linea=CA.conversorDireccionesAbsolutas(linea,path);
		File f= new File(linea);
		
		if(!f.isDirectory()) {
			try {
				dos.writeBytes("Directorio Erroneo, vuelves a: " + path);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else path=CA.conversorDireccionesAbsolutas(linea,path);	

		
		
//		ClaseMetodosAuxiliares.EnviarDirectorioPorSalida(dos,new File(path));
	}
	
//	  aplica funcionalidad .., cambia al directorio padre el directorio de trabajo
	public static void dosPuntos(DataOutputStream dos) {
		File f = new File(path);
		f = f.getParentFile();
		path = f.getPath();
		CA.EnviarDirectorioPorSalida(dos, f);
	}
	
	//Selecciona un archivo y lo envía
	private static void select(DataOutputStream dos, String linea) {
		
		String lineaPorEspacios[]=linea.split(" ");
		path=CA.conversorDireccionesAbsolutas(lineaPorEspacios[1], path);
		
		
		File f = new File(path);
		try(FileInputStream fis= new FileInputStream(f);){
			System.out.println(CA.conversorDireccionesRelativas(f.getPath()));
			dos.writeBytes(CA.conversorDireccionesRelativas(f.getPath())+"\r\n");
			byte b[]= new byte[1024]; int leidos;
			
			while((leidos=fis.read(b))!=-1){
				dos.write(b,0,leidos);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	
	
private static void selectall(DataOutputStream dos, String linea) {


//		String nombreFichero= ClaseMetodosAuxiliares.CortarOrdenFichero(linea);//sale " " arreglar para q haya ficheros con espacios en el nombre

//	String lista[]=	linea.split(" ");
//	String nombreFichero=lista[1];
//	
//		path=ClaseMetodosAuxiliares.conversorDireccionesAbsolutas(nombreFichero, path);
//		path=path+"\\";
//		System.out.println(path);		
//		File directorio = new File(path);
//
//		File archivos[]= directorio.listFiles();
//		
//		System.out.println(f.getAbsolutePath());
//		try(FileInputStream fis= new FileInputStream(f);){
//			System.out.println(ClaseMetodosAuxiliares.conversorDireccionesRelativas(f.getPath()));
//			dos.writeBytes(ClaseMetodosAuxiliares.conversorDireccionesRelativas(f.getPath())+"\r\n");
//			byte b[]= new byte[1024]; int leidos;
//			
//			while((leidos=fis.read(b))!=-1){
//				dos.write(b,0,leidos);
//			}
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
	}
	
//----------------------------------------------------------------------\\

	public static void main(String[] args) {
		ServerSocket ss;
//		ExecutorService pool = Executors.newCachedThreadPool();

		path = "C:\\";
		try {
			ss = new ServerSocket(1111);
			
			while (true) {
				try {
					Socket s = ss.accept();
					DataInputStream dis = new DataInputStream(s.getInputStream());
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					String linea = dis.readLine();
					
					
					if (linea.startsWith("cd")) cd(linea,dos);
					if (linea.equalsIgnoreCase("..")) dosPuntos(dos);
					if (linea.startsWith("show")) CA.EnviarDirectorioPorSalida(dos, new File(path));
					if (linea.startsWith("select")) {
						if(linea.startsWith("selectall")) selectall(dos,linea);
						else select(dos,linea);
					}
					
					s.shutdownOutput();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}





}