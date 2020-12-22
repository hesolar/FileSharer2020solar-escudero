package ClienteServidor_CompartirDirectorio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
 
public class UnzipFile 
{
	//unzip
    public static void main(String[] args) 
    {
        //Open the file 
        try(ZipFile file = new ZipFile("C:\\Users\\usuario\\Desktop\\enen\\a.zip"))
        {
            FileSystem fileSystem = FileSystems.getDefault();
            //Get file entries
            Enumeration<? extends ZipEntry> entries = file.entries();
             
            //We will unzip files in this folder
            String uncompressedDirectory = "C:\\Users\\usuario\\Desktop\\workspace\\";
//            Files.createDirectory(fileSystem.getPath(uncompressedDirectory));
             
            //Iterate over entries
            while (entries.hasMoreElements()) 
            {
                ZipEntry entry = entries.nextElement();
                //If directory then create a new directory in uncompressed folder
                if (entry.isDirectory()) 
                {
                    System.out.println("Creating Directory:" + uncompressedDirectory + entry.getName());
                    Files.createDirectories(fileSystem.getPath(uncompressedDirectory + entry.getName()));
                } 
                //Else create the file
                else
                {
                    InputStream is = file.getInputStream(entry);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    String uncompressedFileName = uncompressedDirectory + entry.getName();
                    Path uncompressedFilePath = fileSystem.getPath(uncompressedFileName);
                    Files.createFile(uncompressedFilePath);
                    FileOutputStream fileOutput = new FileOutputStream(uncompressedFileName);
                    while (bis.available() > 0) 
                    {
                        fileOutput.write(bis.read());
                    }
                    fileOutput.close();
                    System.out.println("Written :" + entry.getName());
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    //zip
//	public static void main(String[] args) {
//		String zipFilePath="C:\\Users\\usuario\\Desktop\\enen\\a.zip"; 
//		String destDir="C:\\\\Users\\\\usuario\\\\Desktop\\\\workspace";
//		
//	        File dir = new File(destDir);
//	        // create output directory if it doesn't exist
//	        if(!dir.exists()) dir.mkdirs();
//	        FileInputStream fis;
//	        //buffer for read and write data to file
//	        byte[] buffer = new byte[1024];
//	        try {
//	            fis = new FileInputStream(zipFilePath);
//	            ZipInputStream zis = new ZipInputStream(fis);
//	            ZipEntry ze = zis.getNextEntry();
//	            while(ze != null){
//	                String fileName = ze.getName();
//	                File newFile = new File(destDir + File.separator + fileName);
//	                System.out.println("Unzipping to "+newFile.getAbsolutePath());
//	                //create directories for sub directories in zip
//	                new File(newFile.getParent()).mkdirs();
//	                FileOutputStream fos = new FileOutputStream(newFile);
//	                int len;
//	                while ((len = zis.read(buffer)) > 0) {
//	                fos.write(buffer, 0, len);
//	                }
//	                fos.close();
//	                //close this ZipEntry
//	                zis.closeEntry();
//	                ze = zis.getNextEntry();
//	            }
//	            //close last ZipEntry
//	            zis.closeEntry();
//	            zis.close();
//	            fis.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	        
//	    }

    
    
    
}