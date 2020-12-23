package ClienteServidor_CompartirDirectorio;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;















public class ZipUses
{
	
	
	public static void main(String[] args) {
		ZipDirectory("C:\\Users\\hesolar\\Desktop\\Nueva carpeta","C:\\Users\\hesolar\\Desktop\\Nueva carpeta\\a.zip");
		
		
		UnzipDirectory("C:/Users/hesolar/Desktop/Nueva carpeta/a.zip","C:/Users\\hesolar\\Desktop\\workspace" );
		
		File f=new File("C:/Users/hesolar/Desktop/workspace/Nueva carpeta/a.zip");	 
		f.delete();
		
		
		f=new File("C:/Users/hesolar/Desktop/Nueva carpeta/a.zip");	 
		f.delete();
		
		
		
		
		
		
	}
	
	
    public static void ZipDirectory(String sourceFile, String sourceDestination){
        
        FileOutputStream fos;
		try {
			fos = new FileOutputStream(sourceDestination);
			  ZipOutputStream zipOut = new ZipOutputStream(fos);
			  File fileToZip = new File(sourceFile);

			  zipFile(fileToZip, fileToZip.getName(), zipOut);
			  zipOut.close();
			  fos.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }
    
    
    
    public static void UnzipDirectory(String origen,String d){
        
    	
    	
    	File f = new File(origen);
    	File destination= new File(d);
    	try(InputStream in = new FileInputStream(f);
            ZipInputStream zip=new ZipInputStream(in);) {
    	
        ZipEntry entry;

        while ((entry = zip.getNextEntry()) != null) {
            File file = new File(destination, entry.getName());

            if (!file.toPath().normalize().startsWith(destination.toPath())) {
                throw new IOException("Bad zip entry");
            }

            if (entry.isDirectory()) {
                file.mkdirs();
                continue;
            }

            byte[] buffer = new byte[1024];
            file.getParentFile().mkdirs();
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            int count;

            while ((count = zip.read(buffer)) != -1) {
                out.write(buffer, 0, count);
            }

            out.close();
        }
    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}