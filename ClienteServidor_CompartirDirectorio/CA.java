package ClienteServidor_CompartirDirectorio;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//clase con métodos auxiliares para no estorbar
public class CA {

	// comprueba si el número puede formar parte de una ipcorrecta
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
				for (File x : f.listFiles()) {
					dos.writeBytes(x.getName() + "\r\n");
				}
				dos.flush();
			} else
				dos.writeBytes("vacio" + "\r\n");

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

	// Ruta absoluta: se indica toda la ruta del archivo incluyendo el directorio
	// raíz. Por ejemplo, C:\carpeta1\carpeta2\archivo1.doc

	// Ruta relativa: se indica la ruta a partir de donde este en ese momento
	// situado. No se incluye el directorio raíz. Por ejemplo, si estamos en la ruta
	// C:\carpeta1 y queremos acceder al archivo1 que esta dentro de la carpeta2,
	// seria carpeta2\archivo1.
	// Para ir al directorio padre, usamos dos puntos seguidos (..)

//	*si directorio tipo: cd c\\users\\usuario  el path es directamente es c\\users\\usuario
//	*si  llega : cd usuario , añadiremos al path la nueva subcarpeta : path\\usuario
	public static String conversorDireccionesAbsolutas(String ruta, String dirRaiz) {

		if (ruta.contains("\\"))
			return ruta;
		else
			return dirRaiz += "\\" + ruta;

	}

	// dada una dirección absoluta c\\users\\usuario devuelve usuario
	public static String conversorDireccionesRelativas(String ruta) {

		if (ruta.contains("\\")) {

			return ruta.substring(ruta.lastIndexOf("\\") + 1, ruta.length());

		} else
			return ruta;

	}

	// dado un file cortar la orden y el nombre del file
	// devuelve el nombre del file

	// arreglar no funciona
	public static String CortarOrdenFichero(String Linea) {

		System.out.println(Linea);
		String ListaEspacios[] = Linea.split(" ",2);

		
		return ListaEspacios[1];

	}

}
