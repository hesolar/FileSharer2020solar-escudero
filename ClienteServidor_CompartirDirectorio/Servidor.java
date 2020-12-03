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
					if (linea.contains("cd")) {
						String a[] = linea.split(" ");

						if (linea.contains("\\")) {
							path = a[1];

						}

						else {
							path = path + "\\" + a[1];
						}

						dos.writeBytes("new Path:" + path + "\n");
						File f = new File(path);

						if (f.listFiles() != null) {
							for (File x : f.listFiles()) {
								dos.writeBytes(x.getName() + "\r\n");

							}
							dos.flush();
						}

					}
//
					if (linea.equalsIgnoreCase("..")) {
						File f = new File(path);

						f = f.getParentFile();

						path = f.getPath();
						dos.writeBytes("new Path:" + path + "\n");

						List<String> names = new ArrayList<>();
						for (File x : f.listFiles()) {
							dos.writeBytes(x.getName() + "\r\n");

						}
						dos.flush();

					}

					if (linea.split(" ").length == 1 && !linea.contains("..")) {

						File f;
						if(linea.contains("mostrar")) f= new File(path);
						
						else 	f= new File(linea);
						 
						
						
						List<String> names = new ArrayList<>();
						for (File x : f.listFiles()) {
							dos.writeBytes(x.getName() + "\r\n");

						}
						dos.flush();
//						
						path = linea;
					
					}
					if (linea.split(" ") == null) {
						File f = new File(path);
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