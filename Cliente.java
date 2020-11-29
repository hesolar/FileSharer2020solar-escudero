import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Cliente { // escudero calvo

	
	public class Objetos implements Serializable{
		private final long SerialVersionUID= 111L;
		private List<File> listado;
		public Objetos(List<File> f) {this.listado=f;}
		public List<File> getListado(){return this.listado;}
	}
	
	Integer port;
	byte[] ip;
	List<File> listaLectura;

	public Cliente(Integer port, byte[] lista) {
		super();
		this.port = port;
		this.ip = lista;
		listaLectura = new ArrayList<>();
	}

	//varias opciones 
		// cd cambia el directorio de trabajo 
		//.. sale al anterior directorio
		//	exit hace fin
		//get "directorio" dev el listado de directorio
			
			
	public void inicioCliente() {

		boolean inicioFin = true;

		try (Socket cliente = new Socket(InetAddress.getByAddress(ip), port);

				ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
				DataInputStream dis = new DataInputStream(cliente.getInputStream());
				DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());

		) {

			String LineaLeida;
			String[] linea;
			String orden;
			String argumentos;

			while (inicioFin) {

				LineaLeida = dis.readLine();
				System.out.println();
				linea = LineaLeida.split(" ");
				orden = linea[0];

				if (orden.equalsIgnoreCase("cd")) {// aplicar comando cd
				}
				if (orden.equalsIgnoreCase("\\..")) {// aplicar ..
				}
				if (orden.equalsIgnoreCase("end")) {// terminar
					inicioFin = false;
				}
				if (orden.equalsIgnoreCase("get")) {// dar directorio
					
					try {
						inicioFin = false;
						argumentos=linea[1];
						Objetos obs=(Objetos)ois.readObject();
						List<File> l=obs.getListado();
						this.listaLectura=l;
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}
				
			}

		}

		catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	//si se inicia el servidor debería listar los directorio y enviar el listado de directorios
	public void inicioServidor() {

		try (ServerSocket ss = new ServerSocket(port);
				Socket s = ss.accept();
				ObjectOutputStream o = new ObjectOutputStream(s.getOutputStream());
				DataInputStream dis = new DataInputStream(s.getInputStream());) {

				this.listaLectura = ClaseMetodosAuxiliares.lecturaDirectorio(dis.readLine());
				Objetos obs= new Objetos(this.listaLectura);
				o.writeObject(obs);
				o.flush();
				
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	public List<File> getListaLectura() {
		return listaLectura;
	}

	

}