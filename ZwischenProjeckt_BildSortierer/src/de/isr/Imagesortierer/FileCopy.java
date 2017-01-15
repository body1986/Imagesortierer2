package de.isr.Imagesortierer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.Md5Crypt;



public class FileCopy {
	
	private ArrayList<Object> Destinationfilelist;
	Hashcheck MdFiveHashcheck = new Hashcheck();
	CurrentFile currentfile;
	String transvermod = "";
	
	public FileCopy(CurrentFile currnetfile, String paramTransvermode) {
		this.currentfile = currnetfile;
		transvermod=paramTransvermode;
				
		boolean md5FileStatus = md5CheckinDestinationDirectory(currnetfile.getFileDestinationPath());
		
		if (md5FileStatus==false) {
			System.out.println("Die md5 Werte stimmen nicht über ein Namen Prüfen");			
			if (!currentfile.getDestinationtfile().exists()) {
				CopyTheFile(currentfile.getFilecurrentPath(),currentfile.getFileDestinationPath());
			}else{
				currentfile.setFileDestinationPath(CreateFileCopy(currentfile.getCurrentfile(), currentfile.getDestinationtfile()).toString());
				//currentfile.setDestinationtfile(); 
				System.out.println("Kopie der Zieldatei wurde erstellt="+currentfile.getFileDestinationPath());
				CopyTheFile(currentfile.getFilecurrentPath(),currentfile.getFileDestinationPath());
			}				
		}
		else
		{
			System.out.println("Die gleiche Datei ist im Zielpfad schon vorhanden");
			SaveDoubleData();			
		}
	}
	
	private void SaveDoubleData() {
			currentfile.setFileDestinationPath(CreateFileCopy(currentfile.getCurrentfile(), new File(currentfile.getGrundDestinationpath()+"\\doubleData\\"+currentfile.getFileFullName())).toString());
			File doubleFile = new File(currentfile.getGrundDestinationpath()+"\\doubleData\\");
			if (!doubleFile.exists()) {
				doubleFile.mkdirs();
			}
			
			CopyTheFile(currentfile.getFilecurrentPath(),currentfile.getFileDestinationPath());		
	}
	

	private boolean md5CheckinDestinationDirectory(String paramDirectoryPath) {
		int charindex = paramDirectoryPath.lastIndexOf("\\");
		String Directorypath = paramDirectoryPath.substring(0, charindex+1);
		System.out.println("Ordner werden durchsucht");
		System.out.println();
		File file = new File(Directorypath);
		boolean md5Status = false;
		if (file.exists()) {
			try {
				File[] rootfiles = file.listFiles();
				if (file != null) {
					
					for (int i = 0; i < rootfiles.length; i++) {						
						if (rootfiles[i].isDirectory()) {							
							md5CheckinDestinationDirectory(rootfiles[i].getAbsolutePath());
						} else {							
							//Destinationfilelist.add(rootfiles[i].getAbsolutePath());
							File destiFile= new File(rootfiles[i].getAbsolutePath()); 
							boolean mdFiveStatus = MdFiveHashcheck.mdFiveHash(currentfile.getCurrentfile(),destiFile);
							if (mdFiveStatus==true) {
								md5Status=true;
								break;
							}
							else{
								md5Status=false;
							}
						}
					}					
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			System.out.println("exist");
		}
		return md5Status;
	}
	
	
	
	public File CreateFileCopy(File currentfile, File destinationSortPath) {		
		// ToDO Counter errechnen lassen
		int CopyCounter = 0;		
		String sCopyCounter="";
		String name;		
		do {
			if (CopyCounter==0) {
				sCopyCounter="";
				CopyCounter=1;
				String[] filesplit = destinationSortPath.getName().split(Pattern.quote("."));
				name = filesplit[0].substring(0, filesplit[0].length())+sCopyCounter; //filesplit[0].replace("_", "").replace("-", "")+" - Kopie"+sCopyCounter;				
			}
			else {
				sCopyCounter="("+CopyCounter+")";
				String[] filesplit = destinationSortPath.getName().split(Pattern.quote("."));
				if (filesplit[0].length()<=8) {
					name = filesplit[0].substring(0, filesplit[0].length())+sCopyCounter;//filesplit[0].replace("_", "").replace("-", "")+" - Kopie "+sCopyCounter;	
				}
				else
				{
					name = filesplit[0].substring(0, filesplit[0].length()-3)+sCopyCounter;//filesplit[0].replace("_", "").replace("-", "")+" - Kopie "+sCopyCounter;
				}
				
			}
			String extantion = currentfile.getName().substring(currentfile.getName().lastIndexOf(".") + 1,currentfile.getName().length());			
			String Path = destinationSortPath.getParent();			
			destinationSortPath = new File(Path + "\\" + name+"."+extantion);
			CopyCounter++;
		} while (destinationSortPath.exists()==true);
		return destinationSortPath;
	}
	

	public void CopyTheFile(String srFile, String dtFile) {		
		 Path FROM = Paths.get(srFile);
		 Path TO = Paths.get(dtFile);
		 System.out.println("datei wird Kopier von="+FROM);
		 System.out.println("datei wird Kopier zu="+TO);
		
		 CopyOption[] copyOptions = new CopyOption[] {
		 StandardCopyOption.REPLACE_EXISTING,
		 StandardCopyOption.ATOMIC_MOVE };
		 try {			 
		if (transvermod.equals("Kopieren")==true) {
			Files.copy(FROM, TO);			 
		}
		else if (transvermod.equals("Verschieben")==true) {
		
		}		 
		 System.out.println("File copied." + TO);
		 } catch (IOException e) {		 
		 e.printStackTrace();
		 System.out.println("File nicht copied." + TO);
		 }
	}

	private static boolean Filecopy(String fROM, String tO) {
		 try{
	            java.io.FileInputStream sourceFile = new java.io.FileInputStream(new File(fROM));

	            try{
	                    java.io.FileOutputStream destinationFile = null;

	                    try{
	                            destinationFile = new FileOutputStream(new File(tO));
	                            byte buffer[] = new byte[512 * 1024];
	                            int reading;

	                            while ((reading = sourceFile.read(buffer)) != -1){
	                                    destinationFile.write(buffer, 0, reading);
	                            }
	                    } finally {
	                            destinationFile.close();
	                    }
	            } finally {
	                    sourceFile.close();
	            }
	    } catch (IOException e){
	            e.printStackTrace();
	            return false;
	    }
	    return true;
	}	
	
	
	
	
	
	
	
}
