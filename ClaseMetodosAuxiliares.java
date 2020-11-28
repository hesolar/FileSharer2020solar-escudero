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
//	}
}
