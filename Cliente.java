import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Cliente {

	Integer port;
	byte[] ip;

	public Cliente(Integer port, byte[] lista) {
		super();
		this.port = port;
		this.ip = lista;
	}

	public void inicioCliente() {

		boolean inicioFin = true;

		try (
				Socket cliente = new Socket(InetAddress.getByAddress(ip),port);

				ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
				DataInputStream dis = new DataInputStream(cliente.getInputStream());
				DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());

		) {

			String LineaLeida;
			String[] linea;
			String orden;

			while (inicioFin) {

				LineaLeida = dis.readLine();
				System.out.println();
				linea = LineaLeida.split(" ");
				orden = linea[1];

				if (orden.equalsIgnoreCase("cd")) {// aplicar comando cd
				}
				if (orden.equalsIgnoreCase("\\..")) {// aplicar ..
				}
				if (orden.equalsIgnoreCase("end")) {// do something
				inicioFin= false;
				}
				
			}

		}

		catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	public void inicioServidor() {
		
		
		ServerSocket ss;
		try {
			ss = new ServerSocket(port);
			Socket s = ss.accept();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		
	}
	
	
	

}