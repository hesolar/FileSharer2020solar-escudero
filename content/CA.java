package content;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//clase con m?todos auxiliares para no estorbar
public class CA {

	// comprueba si el n?mero puede formar parte de una ipcorrecta
	public static Boolean NumeroCorrecto(Integer i) throws NumberFormatException {

		if (i < 255 && i > 0 && i != null)
			return true;
		else
			throw new NumberFormatException();
	}

	// comprueba si una string es un numero ip correcto
	public static Boolean NumeroCorrecto(String i) throws NumberFormatException {

		if (i == null)
			throw new NumberFormatException();
		Integer x = Integer.parseInt(i);

		if (x < 255 && x > 0)
			return true;
		else
			throw new NumberFormatException();

	}

	// lectura de un directorio;
	public static List<File> lecturaDirectorio(String nombreDirectorio) {

		File f = new File(nombreDirectorio);

		List<File> l = new ArrayList<>();
		// lista solo archivos

		for (File x : f.listFiles()) {
			l.add(x);
		}

		return l;
	}

	// Dado un directorio devuelve su listado de directorios
	public static List<String> listadoDirectorios(File f) {

		List<String> directorios = new ArrayList<>();

		if (f.listFiles() != null) {

			for (File x : f.listFiles()) {
				if (x.isDirectory())
					directorios.add(x.getPath());
			}

		}
		return directorios;

	}

	// Dado un directorio devuelve su listado de no directorios
	public static List<String> listadoArchivos(File f) {

		List<String> directorios = new ArrayList<>();

		if (f.listFiles() != null) {

			for (File x : f.listFiles()) {
				if (!x.isDirectory())
					directorios.add(x.getPath());
			}
		}
		return directorios;

	}

	// Dado un directorio f, escribimos por el canal de salida su nombre y su
	// contenido
	public static void EnviarDirectorioPorSalida(DataOutputStream dos, File f) {
		try {

			if (f.listFiles() != null) {
				dos.writeBytes("Directorio: " + f.getPath() + "\r\n");
				dos.writeBytes("Listado de directorios:\r\n");
				
				for (File x : f.listFiles()) {				
					if(f.isDirectory()) dos.writeBytes(x.getName() + "\r\n");
				}
				dos.writeBytes("\r\n");
				dos.writeBytes("Listado de Archivos: " +"\r\n");

				for (File x : f.listFiles()) {
					if(!f.isDirectory()) dos.writeBytes(x.getName() + "\r\n");				
				}
				dos.flush();
			} else
				dos.writeBytes("Este directorio esta vacio" + "\r\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Dada una lista de cadena la escribimos por el canal de salida
	public static void EnvioListaPorSalida(List<String> lista, DataOutputStream dos) {
		try {
			for (String s : lista) {
				dos.writeBytes(s + "\r\n");
			}
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	*si directorio tipo: cd c\\users\\usuario  el path es directamente es c\\users\\usuario
//	*si  llega : cd usuario , a?adiremos al path la nueva subcarpeta : path\\usuario
	public static String conversorDireccionesAbsolutas(String ruta, String dirRaiz) {

		if (ruta.contains("\\"))
			return ruta;
		else
			return dirRaiz += "\\" + ruta;

	}

	// dada una direccion absoluta c\\users\\usuario devuelve usuario
	public static String conversorDireccionesRelativas(String ruta) {

		if (ruta.contains("\\")) {

			return ruta.substring(ruta.lastIndexOf("\\") + 1, ruta.length());

		} else
			return ruta;

	}

	// dado un file cortar la orden y el nombre del file
	// devuelve el nombre del file

	public static String CortarFichero(String Linea) {

		String ListaEspacios[] = Linea.split(" ", 2);

		return ListaEspacios[1];

	}

//Dado un comando orden - parámetro devuelve la orden.
	public static String CortarOrden(String Linea) {

		String ListaEspacios[] = Linea.split(" ", 2);

		return ListaEspacios[0];

	}
	
	//Devuelve la ip Publica usando un servicio web
	public static String ipPublica() {
		URL whatismyip;
		String ip = "No se pudo obtener ip pública";
		BufferedReader in=null;
		try {
			whatismyip = new URL("http://checkip.amazonaws.com");
			in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			ip = in.readLine();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			return ip;
		}
	 }
	
	
	
	
	
	
}