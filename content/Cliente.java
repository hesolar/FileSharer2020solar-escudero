package content;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	private static String path;
	private static String IP;
	private static String ip;

	// Hace elegir al usuario el puerto del servidor
	public static void seleccionPuerto(Scanner es) {
		try {
			System.out.println("\r\nINTRODUCE EL PUERTO DEL SERVIDOR");
			ip = es.nextLine();
			if (Integer.parseInt(ip) < 1024 || Integer.parseInt(ip) > 49151) {
				System.out.println("\r\nEl puerto es incorrecto, el puerto  debe estar entre los valores 1024 y 49151");
				seleccionPuerto(es);
			}
		} catch (NumberFormatException e) {
			System.out.println(
					"\r\nEl puerto es incorrecto, el puerto  debe ser un entero con valores entre 1024 y 49151");
			seleccionPuerto(es);
		}
	}

	// Hace elegir al usuario el puerto del servidor(recursivo)
	public static void seleccionIP(Scanner es) {
		try {
			System.out.println("\r\nINTRODUCE IP");
			ip = es.nextLine();
			if (!ip.equalsIgnoreCase("LOCALHOST")) {
				String octetos[] = ip.split("\\.");
				if (octetos.length != 4) {
					System.out.println("\r\n IP es incorrecta, debe estar entre los valores 0 y 255,longitud erronea");
					seleccionIP(es);
				} else {
					for (String s : octetos) {
						if (Integer.parseInt(s) < 0 || Integer.parseInt(s) > 255) {
							System.out.println(
									"\r\n IP es incorrecta, debe estar entre los valores 0 y 255,no es numero valido");
							seleccionIP(es);
						}
					}
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("\r\n IP es incorrecta, debe estar entre los valores 0 y 255,no es un numero");
			seleccionIP(es);
		}
	}

	// Hace elegir al usuario un directorio válido de trabajo(recursivo)
	public static void seleccionDirectorioTrabajo(Scanner es) {
		System.out.println("\r\nINTRODUCE DONDE SE GUARDARAN TUS ARCHIVOS DESCARGADOS");
		String linea = es.nextLine();
		File f = new File(linea);
		if (f.isDirectory())
			path = f.getAbsolutePath();
		else {
			System.out.println("El directorio no es correcto");
			seleccionDirectorioTrabajo(es);
		}

	}

	// Ajustar la ip , puerto y dir de trabajo
	public static void setParametrosServer(Scanner es) {
		seleccionIP(es);
		seleccionPuerto(es);
		seleccionDirectorioTrabajo(es);
	}

	// Principal del cliente, establece conexion con servidor, pide las ordenes y
	// realiza las acciones
	public static void main(String[] args) {
		Socket s = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		Scanner es = new Scanner(System.in);
		setParametrosServer(es);

		String linea;
		boolean end = true;

		try {

			s = new Socket(IP, Integer.parseInt(ip));
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());

			while (end && !s.isClosed()) {

				System.out.println("\nINTRODUCE ORDEN\n");
				linea = es.nextLine();

				// opcion salir
				if (linea.equalsIgnoreCase("exit") || linea == null)
					end = false;
				// sino
				else {
					// select(opciones de archivos)
					if (linea.startsWith("select")) {
						if (linea.startsWith("selectall"))
							selectall(dos, dis, linea);
						else
							select(dos, dis, linea);

					} else {
						// opciones(no de archivos)
						dos.writeBytes(linea + "\r\n");

						linea = dis.readLine();
						// ; marca de fin , o cierre de comunicacion
						while (linea != null && !linea.equals(";")) {
							System.out.println(linea);
							linea = dis.readLine();
						}
					}
				}
			}

		} catch (IOException e) {
			System.out.println("\r\nNo se ha podido establecer la conexion con el servidor");
		}

		finally {
			// finalmente cierre de recursos
			try {
				if (s != null) {
					s.shutdownInput();
					s.shutdownOutput();
					s.close();
				}
				if (dis != null)
					dis.close();
				if (dos != null)
					dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// funcionalidad Clonar fichero, que recibe el nombre de un fichero y luego el
	// fichero
	public static void select(DataOutputStream dos, DataInputStream dis, String LineaLeida) {

		FileOutputStream fos = null;
		try {
			dos.writeBytes(LineaLeida + "\r\n");
			long tamaño = dis.readLong();

			String pathNuevo = path + "\\" + dis.readLine();
			System.out.println("el path nuevo es" + pathNuevo);
			File f = new File(pathNuevo);

			System.out.println("\n La nueva ubicación del es: " + f.getParent() + "\n");

			fos = new FileOutputStream(f);

			byte[] b = new byte[1024];
			long leidos = 0;
			long restantes = tamaño;
			long auxRestantes;
			// Estructura para enviar exactamente el número de bites exactos
			while (leidos != -1 && restantes > 0) {
				leidos = dis.read(b);

				auxRestantes = restantes;
				restantes = restantes - leidos;

				if (restantes > 0) {
					fos.write(b, 0, (int) leidos);
					fos.flush();
				} else {
					fos.write(b, 0, (int) auxRestantes);
					fos.flush();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {

			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// funcionalidad Clonar carpetas, que recibe el el zip de un directorio/carpeta,
	// la descipea y almacena en el destino
	public static void selectall(DataOutputStream dos, DataInputStream dis, String LineaLeida) {
		select(dos, dis, LineaLeida); // enviar archivo en zip de la carpeta
		ZipUses.UnzipDirectory(path + "\\a.zip", path); // zipear
		File f = new File(path + "\\a.zip"); // creacion de archivo auxiliar
		f.delete(); // borrar
	}

}
