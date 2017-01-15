package de.isr.Imagesortierer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Configfile {

	private String		filePathExtentions	= "Lib\\allowedExtantions";
	private String		filePathConfig		= "Lib\\Config";

	private File		allowedExtantions	= new File(filePathExtentions);
	private File		Config				= new File(filePathConfig);

	ArrayList<Object>	extantionList		= new ArrayList<>();
	ArrayList<Object>	ConfigList			= new ArrayList<>();

	/**
	 * Erstellt ein Configfile mit Standart "Allowed Extantions" fals nicht vorhanden
	 */
	public void createConfigFiles() {
		try {
			if (!allowedExtantions.exists()) {
				allowedExtantions.createNewFile();
				createAllowedExtantion();
			}
			if (!Config.exists()) {
				Config.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * fügt ein "Allowed Extantion" Element der Liste hinzu
	 * 
	 * @param paramExtantion
	 */
	public void setAllowedExtantion(String paramExtantion) {
		extantionList = getAllowedExtantion();
		extantionList.add(paramExtantion);
		insertAllowedExtantion();
	}

	/**
	 * gibt eine Liste der "Allowed Extantions" zurück
	 * 
	 * @return
	 */
	public ArrayList<Object> getAllowedExtantion() {
		ArrayList<Object> allowedExtentions = new ArrayList<>();
		try {
			FileReader fileReader;
			fileReader = new FileReader(filePathExtentions);
			BufferedReader bufferdreader = new BufferedReader(fileReader);
			while (bufferdreader.ready()) {
				allowedExtentions.add(bufferdreader.readLine());
			}
			bufferdreader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return allowedExtentions;
	}

	/**
	 * löscht ein "Allowed Extantions" Element
	 * 
	 * @param paramExtantion
	 */
	public void dellAllowedExtantion(String paramExtantion) {
		int indexofDellItem = extantionList.indexOf(paramExtantion);
		extantionList.remove(indexofDellItem);
		insertAllowedExtantion();
	}

	/**
	 * Erstellt "Allowed Extantions" Elemente im Confifile
	 */
	private void createAllowedExtantion() {
		String filePathExtentions = "allowedExtantions";
		String startExtantions = "JPEG,JPG,WEBP,GIF,ICO,BMP,TIFF,PSD,PCX,RAW,CRW,CR2,NEF,ORF,RAF,RW2,RWL,SRW,ARW,DNG,X3F";//PNG,
		String[] splittetStartExtantions = startExtantions.split(",");
		FileWriter fileWriter;
		BufferedWriter bufferWriter = null;
		try {
			fileWriter = new FileWriter(filePathExtentions);
			bufferWriter = new BufferedWriter(fileWriter);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < splittetStartExtantions.length; i++) {
			String extantion = splittetStartExtantions[i].toString();
			try {
				bufferWriter.append(extantion);
				bufferWriter.append("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			bufferWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * fügt alle Elemente der Liste dem File hinzu
	 */
	private void insertAllowedExtantion() {
		try {
			cleanAllowedExtantion();
			FileWriter fileWriter;
			fileWriter = new FileWriter(filePathExtentions);
			BufferedWriter bufferwriter = new BufferedWriter(fileWriter);
			for (int i = 0; i < extantionList.size(); i++) {
				bufferwriter.write("");
				bufferwriter.write("\n");
			}
			bufferwriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Löscht den Inhalt des ConfigFiles
	 */
	private void cleanAllowedExtantion() {
		try {
			FileWriter fileWriter;
			fileWriter = new FileWriter(filePathExtentions, false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("");
			bufferedWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}