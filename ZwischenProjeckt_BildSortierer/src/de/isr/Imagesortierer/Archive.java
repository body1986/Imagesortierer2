package de.isr.Imagesortierer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Archive {
	
	public Archive(String paramDirToZip,String paramZipPath,String paramZipName) { 
		  File directory = new File(paramDirToZip);
	      zip(directory,paramZipPath,paramZipName);
	}
	
	
	public static boolean zip(File directory,String paramZipPath,String paramZipName) {
	       
        boolean result = false;
       
        byte[] buffer = new byte[8192];
       
            try {
               
                LinkedList<String> fileList = getAllFiles(directory);
               
                String outFilename = paramZipPath+"\\"+paramZipName+".zip";
                FileOutputStream fos = new FileOutputStream(outFilename);
                ZipOutputStream zos = new ZipOutputStream(fos);
               
                for (String fileName : fileList) {
                   
                    FileInputStream fis = new FileInputStream(fileName);
                    zos.putNextEntry(new ZipEntry(fileName));
                   
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                   
                    zos.closeEntry();
                    fis.close();
                }  
               
                zos.close();
                fos.close();
               
                result = true;
               
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
   
        return result;
       
    }
   
   
    /**
     * Methode legt alle Datei-Pfade in einer LinkedList ab - mit allen Unterordnern
     * @param files
     * @return
     */
    private static LinkedList<String> getAllFiles(File directory) {
       
        File[] files = directory.listFiles();
       
        LinkedList<String> allFiles = new LinkedList<String>();
       
        for (File file : files) {
           
            if (file.isDirectory()) {
                LinkedList<String> moreFiles = getAllFiles(file);
                Iterator<String> iterMoreFiles = moreFiles.iterator();
                while(iterMoreFiles.hasNext()) {
                    allFiles.add(iterMoreFiles.next());
                }
               
            } else {
                allFiles.add(file.getAbsolutePath());
            }  
        }
       
        return allFiles;
    }
	
	
	
//	public Archive(String paramDirToZip,String paramZipPath,String paramZipName) { 
//        
//        String dirToZip = paramDirToZip; 
//        String zipName = paramZipPath+"\\"+paramZipName; 
//        ZipOutputStream zos = null; 
//        try { 
//            File f = new File(zipName + ".zip"); 
//            System.out.println("Erzeuge Archiv " + f.getCanonicalPath()); 
//            zos = new ZipOutputStream(new FileOutputStream( 
//                    f.getCanonicalPath())); 
//            zipDir(zipName, dirToZip, new File(dirToZip), zos); 
//        } catch (IOException e) { 
//            e.printStackTrace(); 
//        } finally { 
//            try { 
//                if (zos != null) zos.close(); 
//            } catch (IOException ioe) {} 
//        } 
//    }
//
//	private void zipDir(String zipName, String dirToZip, File dirToZipFile, ZipOutputStream zos) {
//		if (zipName == null || dirToZip == null || dirToZipFile == null || zos == null || !dirToZipFile.isDirectory())
//			return;
//
//		FileInputStream fis = null;
//		try {
//			File[] fileArr = dirToZipFile.listFiles();
//			String path;
//			for (File f : fileArr) {
//				if (f.isDirectory()) {
//					zipDir(zipName, dirToZip, f, zos);
//					continue;
//				}
//				fis = new FileInputStream(f);
//				path = f.getCanonicalPath();
//				String name = path.substring(dirToZip.length(), path.length());
//				System.out.println("Packe " + name);
//				zos.putNextEntry(new ZipEntry(name));
//				int len = 0;
//				
//				byte[] buffer = new byte[2048];
//				zos.write(buffer, 0, len);
//				while ((len = fis.read(buffer, 0, buffer.length)) > 0) {
//					zos.write(buffer, 0, len);
//				}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (fis != null)
//					fis.close();
//			} catch (IOException ioe) {
//			}
//		}
//	}
}
