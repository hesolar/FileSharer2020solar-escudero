package ClienteServidor_CompartirDirectorio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
	
	
	
	
	
	
	
//FUNCIONALIDADES SERVIDOR : mostrar,cd,.., directorio	
	
	
//	  aplica funcionalidad cd, 
//	*si directorio tipo: cd c\\users\\usuario  el path es directamente es c\\users\\usuario
//	*si  llega : cd usuario , añadiremos al path la nueva subcarpeta : path\\usuario	 
	public static void cd(String linea,DataOutputStream dos) {
		String a[] = linea.split(" ");

		if (linea.contains("\\"))path = a[1]; 	 			
		else path = path + "\\" + a[1];
			
		ClaseMetodosAuxiliares.EnviarDirectorioPorSalida(dos,new File(path));
	}
	
//	  aplica funcionalidad .., cambia al directorio padre el directorio de trabajo
	public static void dosPuntos(DataOutputStream dos) {
		File f = new File(path);
		f = f.getParentFile();
		path = f.getPath();
		ClaseMetodosAuxiliares.EnviarDirectorioPorSalida(dos, f);
	}
	
//----------------------------------------------------------------------\\

	public static void main(String[] args) {
		ServerSocket ss;
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
					if (linea.startsWith("show")) ClaseMetodosAuxiliares.EnviarDirectorioPorSalida(dos, new File(path));
					
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