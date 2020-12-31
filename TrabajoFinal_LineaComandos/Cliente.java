package TrabajoFinal_LineaComandos;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Cliente {

	private static String path;
	
	
	//Hace elegir al usuario un directorio válido de trabajo s
	public static void seleccionDirectorioTrabajo() {
		System.out.println("INTRODUCE DONDE SE GUARDARAN TUS ARCHIVOS DESCARGADOS");
		Scanner es = new Scanner(System.in);
		String linea=es.nextLine();
		
		File f = new File(linea);
		//File f = new File("C:\\Users\\usuario\\Desktop\\workspace");
		//----------------arreglar
		if(f.isDirectory()) path= f.getAbsolutePath();
		else {
			System.out.println("El directorio no es correcto");
			seleccionDirectorioTrabajo();
		}
		
	}
	
	
	public static void main(String[] args) {
		try {
			seleccionDirectorioTrabajo();
			String  s1;

			Scanner es = new Scanner(System.in);
			boolean end=true;
			Socket s = new Socket("192.168.0.138",1111);
			DataInputStream dis = null;
			DataOutputStream dos = null;
			while(end && !s.isClosed()) {
				dis=new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				System.out.println("\nINTRODUCE ORDEN\n");
				s1=es.nextLine();
				
				if(s1.equalsIgnoreCase("exit")||s1==null)end=false;		
				
				else {
					
					if(s1.startsWith("select")){
						if(s1.startsWith("selectall")) {
							selectall(dos,dis,s1);
						}else{
							select(dos,dis,s1); 
						}
					}else {
					
						dos.writeBytes(s1+"\r\n");
						String a;
						a=dis.readLine();
						while(a!=null && !a.equals(";")) {
							System.out.println(a);
							a=dis.readLine();
						}
					}
				}
			}
			s.shutdownInput();
			s.shutdownOutput();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	// funcionalidad download que recibe el nombre de un fichero y luego el fichero
	public static void select(DataOutputStream dos, DataInputStream dis, String LineaLeida) {
		
		FileOutputStream fos=null;
		try {		
			dos.writeBytes(LineaLeida + "\r\n");
			
			long tamaño = dis.readLong();
			
			String pathNuevo=path+"\\"+dis.readLine();
			System.out.println("el path nuevo es"+pathNuevo);
			File f = new File(pathNuevo);
			
			System.out.println("\n La nueva ubicación del es: " + f.getAbsolutePath() + "\n");

			fos = new FileOutputStream(f);	
			
			
			byte[] b = new byte[1024];
            long leidos = 0;
            long restantes = tamaño;
            long auxRestantes;

            while (leidos != -1 && restantes > 0) {
                leidos = dis.read(b);

                auxRestantes =  restantes;
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
					if(fos!=null)fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}
	
	public static void selectall(DataOutputStream dos, DataInputStream dis, String LineaLeida) {
			select(dos, dis, LineaLeida);
			ZipUses.UnzipDirectory(path+"\\a.zip",path);
			File f = new File(path+"\\a.zip");
			f.delete();
	
	}
}
