package ClienteServidor_CompartirDirectorio;
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

public class Servidor {

	
	public class Objetos implements Serializable{
		private final long SerialVersionUID= 111L;
		private List<File> listado;
		public Objetos(List<File> f) {this.listado=f;}
		public List<File> getListado(){return this.listado;}
	}
	
	
	
	public static void main(String[] args) {
		
	}
	
	

}