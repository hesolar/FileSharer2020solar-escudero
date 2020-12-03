package ClienteServidor_CompartirDirectorio;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//clase con métodos auxiliares para no estorbar
public class ClaseMetodosAuxiliares {

	// comprueba si el número puede formar parte de una ipcorrecta
	public static Boolean NumeroCorrecto(Integer i) throws NumberFormatException {
		
		if(i < 255 && i > 0 && i!=null) return true;
		else throw new NumberFormatException();
	}

	//comprueba si una string es un numero ip correcto
	public static Boolean NumeroCorrecto(String i) throws NumberFormatException {

		if(i==null)throw new NumberFormatException();
		Integer x = Integer.parseInt(i);
		
		
		if(x < 255 && x > 0) return true;
		else throw new NumberFormatException();
		
		
	}
	
	//lectura de un directorio;
	public static List<File> lecturaDirectorio(String nombreDirectorio) {

			File f = new File(nombreDirectorio);

			List<File> l = new ArrayList<>();
			// lista solo archivos

			for (File x : f.listFiles()) {
				l.add(x);
			}

			return l;
		}
	
	//Dado un directorio devuelve su listado de directorios
	public static List<String> listadoDirectorios(File f) {

		List<String> directorios = new ArrayList<>();

		if (f.listFiles() != null) {

			for (File x : f.listFiles()) {
				if (x.isDirectory())directorios.add(x.getPath());				
			}

		}
		return directorios;

	}

	//Dado un directorio devuelve su listado de no directorios
	public static List<String> listadoArchivos(File f) {

		List<String> directorios = new ArrayList<>();

		if (f.listFiles() != null) {

			for (File x : f.listFiles()) {
				if (!x.isDirectory())directorios.add(x.getPath());							
			}
		}
		return directorios;

	}
	
	//Dado un directorio f, escribimos por el canal de salida su nombre y su contenido
	public static void EnviarDirectorioPorSalida(DataOutputStream dos,File f) {
			try {

				if (f.listFiles() != null) {
					dos.writeBytes("Directorio: "+f.getPath() +"\r\n");
					for (File x : f.listFiles()) {
						dos.writeBytes(x.getName() + "\r\n");
					}
					dos.flush();
				} 
				else dos.writeBytes("vacio"+"\r\n");
				
			
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	
	//Dada una lista de cadena la escribimos por el canal de salida
	public static void EnvioListaPorSalida(List<String> lista, DataOutputStream dos) {
			try {
				for (String s : lista) {
					dos.writeBytes(s+"\r\n");
				}
			}

			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
}
