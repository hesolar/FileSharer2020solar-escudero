import java.io.File;
import java.util.ArrayList;
import java.util.List;

//clase con métodos auxiliares para no estorbar
public class ClaseMetodosAuxiliares {

//	public static class HELP{
	// comprueba si el numero ip es correcto
	public static Boolean NumeroCorrecto(Integer i) throws NumberFormatException {
		
		if(i < 255 && i > 0) return true;
		else throw new NumberFormatException();
	}

	public static Boolean NumeroCorrecto(String i) throws NumberFormatException {

		Integer x = Integer.parseInt(i);
		
		
		if(x < 255 && x > 0) return true;
		else throw new NumberFormatException();
		
		
	}
	
	
	//hace la lectura de un directorio;
		public static List<File> lecturaDirectorio(String nombreDirectorio) {

			File f = new File(nombreDirectorio);

			List<File> l = new ArrayList<>();
			// lista solo archivos

			for (File x : f.listFiles()) {
				l.add(x);
			}

			return l;
		}
	
	

}
