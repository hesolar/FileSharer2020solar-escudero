package ClienteServidor_CompartirDirectorio;


import java.io.DataInputStream;
import java.io.DataOutputStream;
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
				System.out.println(s1);
				if(s1.equalsIgnoreCase("exit")){
					
					end=false;
				}
				else {
					Socket s = new Socket("localhost",1111);
					DataInputStream dis=new DataInputStream(s.getInputStream());
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					
					dos.writeBytes(s1+"\r\n");
					String a;
					while((a=dis.readLine())!=null) {
						System.out.println(a);
						
					}
				}
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
