package de.isr.Imagesortierer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream; 



public class Filezip extends Thread {
	
	public void run(String paramDirToZip,String paramZipName){
		directory2zip(paramDirToZip, paramZipName);
	}
	

	    public void directory2zip(String paramDirToZip,String paramZipName) { 
	        
	        String dirToZip =paramDirToZip;// "/Users/Willi/Desktop/test"; 
	        String zipName =paramZipName;// "/Users/Willi/Desktop/abrakadabra"; 
	        ZipOutputStream zos = null; 
	        try { 
	            File f = new File(zipName + ".zip"); 
	            System.out.println("Erzeuge Archiv " + f.getCanonicalPath()); 
	            zos = new ZipOutputStream(new FileOutputStream( 
	                    f.getCanonicalPath())); 
	            zipDir(zipName, dirToZip, new File(dirToZip), zos); 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } finally { 
	            try { 
	                if (zos != null)
	                {
	                	zos.closeEntry();
	                	zos.finish();
	                	zos.close();
	                }	
	               
	            } catch (IOException ioe) {} 
	        }			
	    } 

	    private void zipDir(String zipName, String dirToZip, File dirToZipFile, 
	            ZipOutputStream zos) { 
	        if (zipName == null || dirToZip == null || dirToZipFile == null 
	                || zos == null || !dirToZipFile.isDirectory()) 
	            return; 

	        FileInputStream fis = null; 
	        try { 
	            File[] fileArr = dirToZipFile.listFiles(); 
	            String path; 
	            for (File f : fileArr) { 
	                if (f.isDirectory()) { 
	                    zipDir(zipName, dirToZip, f, zos); 
	                    continue; 
	                } 
	                fis = new FileInputStream(f); 
	                path = f.getCanonicalPath(); 
	                String name = path.substring(dirToZip.length(), path.length()); 
	                System.out.println("Packe " + name); 
	                zos.putNextEntry(new ZipEntry(name)); 
	                int len; 
	                byte[] buffer = new byte[2048]; 
	                while ((len = fis.read(buffer, 0, buffer.length)) > 0) { 
	                    zos.write(buffer, 0, len);              
	                } 
	            } 
	        } catch (FileNotFoundException e) { 
	            e.printStackTrace(); 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } finally { 
	            try { 
	                if(fis != null) 
	                {		                	
	                	fis.close();
	                }	               		                
	            } catch (IOException ioe) {} 
	        }
	    }	
}
