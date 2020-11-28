package ejercicioActualizacionDirectorios;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class main {

	public static void main(String[] args) {

		System.out.println("introduce directorio");
		Scanner es = new Scanner(System.in);

		Calendar init = Calendar.getInstance();
		Timer timer = new Timer();
		Tarea t = new Tarea(es.nextLine());
		timer.scheduleAtFixedRate(t, init.getTime(), 10000);
	//delay 0 , sobra calendar sobra get instance etc.
		
		es.close();

	}

}