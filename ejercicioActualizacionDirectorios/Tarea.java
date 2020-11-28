package ejercicioActualizacionDirectorios;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TimerTask;

public class Tarea extends TimerTask {

	private String nombreDirectorio;
	private List<File> directorios;

	public Tarea(String directorio) {
		super();
		this.nombreDirectorio = directorio;
		directorios = new ArrayList<File>();
	}

	public void run() {

		File f = new File(nombreDirectorio);

		List<File> l = new ArrayList<>();
		// lista solo archivos

		for (File x : f.listFiles()) {
			l.add(x);
		}
		
		
		
	

		
		if(l.size()!=this.directorios.size()) {
			
			for (File x : f.listFiles()) {
				System.out.println(x.getName());
			}
		}
		else {
			//fecha modificacion de la carpeta cambios en el archivo
			
			boolean b = true;
			for (int i=0 ; i<l.size();i++) {
				if(!this.directorios.get(i).getName().equals(l.get(i).getName())) b=false;
			}
			
			if(!b) {
				
				for (File x : f.listFiles()) {
					System.out.println(x.getName());
				}
			}
		}
		
		
		System.out.println("------------------------------------------------------------------------------\n");
		this.directorios = l;
	}

}
