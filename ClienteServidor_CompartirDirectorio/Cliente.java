package ClienteServidor_CompartirDirectorio;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		try {
			
			String  s1;

			Scanner es = new Scanner(System.in);
			boolean end=true;
			while(end) {
				System.out.println("\nINTRODUCE ORDEN\n");
				s1=es.nextLine();
				if(s1.equalsIgnoreCase("exit")){
					
					end=false;
				}
				else {
					Socket s = new Socket("localhost",1111);
					DataInputStream dis=new DataInputStream(s.getInputStream());
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					if(s1.startsWith("select")) {
						dos.writeBytes(s1+"\r\n");
						String s2="";
						File f = new File(s2=ClaseMetodosAuxiliares.conversorDireccionesRelativas(dis.readLine()));
						System.out.println(s2);
						
						FileOutputStream fos= new FileOutputStream(f);
						byte b[]=new byte[1024]; int leidos;
						while((leidos=dis.read(b))!=-1) {
							fos.write(b,0,leidos);
						}
						System.out.println(f.getAbsolutePath());
					}
					else {
					
					
					
					dos.writeBytes(s1+"\r\n");
					String a;
					while((a=dis.readLine())!=null) {
						System.out.println(a);
						
					}
					
					}
				}
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
