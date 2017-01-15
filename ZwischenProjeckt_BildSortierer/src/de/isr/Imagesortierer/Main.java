package de.isr.Imagesortierer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.crypto.spec.IvParameterSpec;

public class Main {
	private String rootPath = "";
	private String sortPath = "";
	private String sortTyp = "";
	private String sortTypMemory = "";
	private String sortPathMemory = "";
	private String Transvermode = "";
	private String InvalidMode = "";
	private ArrayList<Object> allowedExtentions;
	private ArrayList<Object> currentFileList;
	private ArrayList<Object> Destinationfilelist;
	private Configfile configFiles = new Configfile();
	CurrentFile currentfile = new CurrentFile();
	
	FileCopy fileCopy;
	Filecheck fileCheck;
	log log = new log();
	

	public void StartSortiringImages(String paramRootPath, String paramSortPath, String paramSortTyp,String paramArchivemode,String paramTransverMethod,String paramInvalidData	) {
		rootPath = paramRootPath;
		sortPath = paramSortPath;
		currentfile.setGrundpath(rootPath);
		currentfile.setGrundDestinationpath(sortPath);
		sortPathMemory = paramSortPath;
		sortTyp = paramSortTyp;
		sortTypMemory = paramSortTyp;
		Transvermode=paramTransverMethod;
		InvalidMode=paramInvalidData;
		System.out.println("Imagesortierung wird gestartet");	
		if (paramArchivemode.toLowerCase().equals("ja")) {
			Filezip zip = new Filezip();
			zip.run(rootPath+"\\", paramSortPath+"\\Backup123");
		}
		readDirectory(rootPath);	
		log.close();
	}

	private void readDirectory(String paramDirectoryPath) {
		System.out.println("Ordner werden durchsucht");
		System.out.println();
		File file = new File(paramDirectoryPath);
		if (file.exists()) {
			try {
				File[] rootfiles = file.listFiles();
				if (file != null) {
					for (int i = 0; i < rootfiles.length; i++) {
						if (rootfiles[i].isDirectory()) {
							System.out.println("neuen ordner gefunden");
							readDirectory(rootfiles[i].getAbsolutePath());
						} else {
							System.out.println("Datei gefunden");
							//currentFileList.add(currentfile.getCurrentfile());
							Workwithfile(rootfiles[i].getAbsolutePath());
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			System.out.println("dont exist");
		}
	}
	
	
	
	private void Workwithfile(String paramCurrentFilePath) {				
		currentfile.SetnewFile(paramCurrentFilePath);
		Hashcheck MdFiveHashcheck = new Hashcheck();
		Datecheck dateValidate = new Datecheck();
		
		allowedExtentions = configFiles.getAllowedExtantion();
		
		
		if (allowedExtentions.contains(currentfile.getFileExtention().toUpperCase()) == true) {
			System.out.println(currentfile.getFilecurrentPath()+" Dateiendung ist erlaubt");
			
			dateValidate.main(currentfile.getCurrentfile(),currentfile);
			String planedDate = dateValidate.getOriginaldatum();
			System.out.println(currentfile.getFilecurrentPath()+" Datei datum="+planedDate);
			if (!planedDate.equals("")) {
				
				if(dateValidate.getFileYear().matches("^[0-9]*$")) {
					if(dateValidate.getFileMonth().matches("^[0-9]*$")) {
						if(dateValidate.getFileDay().matches("^[0-9]*$")) {
							currentfile.SetDestinationFile(currentfile.CreateDestinationpath(currentfile.getFileFullName(),dateValidate.getFileYear(), dateValidate.getFileMonth(), dateValidate.getFileDay(),sortPath ,sortTyp ).getAbsolutePath());
							new FileCopy(currentfile,Transvermode);
				        }
						else {
							//Fehler im Tag
							SaveInvalidData();
						}
			        }
					else {
						//Fehler im Monat
						SaveInvalidData();
					} 
		        }
				else {
					//Fehler im Jahr
					SaveInvalidData();
				}
				//				
				
			}else {
				SaveInvalidData();
				//System.out.println(currentfile.getFileFirstName()+"Datei wird nicht Kopiert, da Sie kein Datum enthält");		
				//log.seterrorFile(currentfile.getFileFirstName()+" Datei enthält kein Datum");
				//fileCopy.CreateFileCopy(currentfile.getCurrentfile(), currentfile.getDestinationtfile());
			}
		}else {
			System.out.println(currentfile.getFileFirstName()+"Datei ist nicht erlaubt");
			//log.seterrorFile(currentfile.getName().toString()+" Dateiendung nicht erlaubt");
		}	
	}

	private void SaveInvalidData() {
		if (InvalidMode.equals("Ja")==true) {
			currentfile.setDestinationtfile(currentfile.CreateDestinationpath(currentfile.getFileFullName(),sortPath,sortTyp));
			new FileCopy(currentfile,Transvermode);
		}
	}	
}
