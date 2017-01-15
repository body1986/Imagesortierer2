package de.isr.Imagesortierer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class log {
	
	private String nonexistentFile = "D:\\nonexistentFile.txt";
	private String doubleFile = "D:\\doubleFile.txt";
	private String originalFile = "D:\\originalFile.txt";
	private String errorFile = "D:\\errorFile.txt";	
	FileWriter fwError;
	BufferedWriter bwError;
	FileWriter fworiginal;
	BufferedWriter bworiginal;
	FileWriter fwdouble;
	BufferedWriter bwdouble;
	FileWriter fwnonexistent;
	BufferedWriter bwnonexistent;
	
	public log() {	
		try {
			fwError = new FileWriter(errorFile);
			bwError = new BufferedWriter(fwError);
			fworiginal = new FileWriter(originalFile);
			bworiginal = new BufferedWriter(fworiginal);
			fwdouble = new FileWriter(doubleFile);
			bwdouble = new BufferedWriter(fwdouble);
			fwnonexistent = new FileWriter(nonexistentFile);
			bwnonexistent = new BufferedWriter(fwnonexistent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
    
	
/**
 * import java.io.*;

class WriteFile
{
  public static void main(String[] args) throws IOException
  {
    FileWriter fw = new FileWriter("ausgabe.txt");
    BufferedWriter bw = new BufferedWriter(fw);

    bw.write("test test test");
    bw.newLine();
    bw.write("tset tset tset");

    bw.close();
  }	
 * @throws IOException 
 */
	
	public void setnonexistentFile(String text) throws IOException {
		File file = new File(nonexistentFile);
		if (file.exists()==false) {
			file.createNewFile();
		}
		bwnonexistent.write(text);
		bwnonexistent.newLine();	
	}
	public void setdoubleFile(String text) throws IOException {
		File file = new File(doubleFile);
		if (file.exists()==false) {
			file.createNewFile();
		}
		bwdouble.write(text);
		bwdouble.newLine();
	}
	public void setoriginalFile(String text) throws IOException {
		File file = new File(originalFile);
		if (file.exists()==false) {
			file.createNewFile();
		}						
		bworiginal.write(text);
		bworiginal.newLine();
	}
	public void seterrorFile(String text) throws IOException {
		File file = new File(errorFile);
		if (file.exists()==false) {
			file.createNewFile();
		}
		bwError.write(text);
		bwError.newLine();
	}
	
	public void close() {
		try {			
			bwdouble.close();
			bwError.close();
			bwnonexistent.close();
			bworiginal.close();
//			fwdouble.close();		
//			fwError.close();
//			fwnonexistent.close();
//			fworiginal.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
//	Handler fileHandlerLog;
//	Formatter klartext;
//
//	private ArrayList<Object> sortiertypen;
//	private ArrayList<Object> copierlog;
//	private ArrayList<Object> nichtsortiert;
//	private ArrayList<Object> fehlerlog;
//
//	public log() throws SecurityException, IOException {
//		copierlog = new ArrayList<>();
//		nichtsortiert = new ArrayList<>();
//		fehlerlog = new ArrayList<>();
//	}
//	
//	public ArrayList<Object> getSortiertypen() {
//		return sortiertypen;
//	}
//
//	public void setSortiertypen(String sortiertyp) {
//		this.sortiertypen.add(sortiertyp);
//
//	}
//
//	public void dellSortiertypen(String sortiertyp) {
//		this.sortiertypen.remove(sortiertyp);
//	}
//
//	public void setcopierlog_item(String Item) {
//		copierlog.add(Item);
//	}
//
//	public void setnichtsortierlog_item(String Item) {
//		nichtsortiert.add(Item);
//	}
//
//	public void setfehlerlog_item(String Item) {
//		fehlerlog.add(Item);
//	}
//
//	public void getcopierlog_item(String Item) {
//
//	}
//
//	public void getnichtsortierlog_item(String Item) {
//
//	}
//
//	public void getfehlerlog_item(String Item) {
//
//	}
//
//	public void readTxtFile() {
//		try {
//			FileReader fr = new FileReader("/ZwischenProjeckt_BildSortierer/src/de/isr/BildSortierer/sortierrypen.txt");
//			BufferedReader br = new BufferedReader(fr);
//			String zeile = br.readLine();
//			// String regexslit = "[\",;]";
//			int linecount = 0;
//
//			// int zeile2 = br.read();
//			// //////////////////////////////////////////// ToDo
//			// /////////////////////////
//
//			while (zeile != null) {
//				if (linecount == 1) {
//					// String txtParameter[] = zeile.split(regexslit);
//					// rootPath = txtParameter[0].toLowerCase();
//					// seachMode = txtParameter[1].toLowerCase();
//					// editMode = txtParameter[2].toLowerCase();
//					// seachNode = txtParameter[3].toLowerCase();
//					// replaceNode = txtParameter[4].toLowerCase();
//				}
//				linecount++;
//				zeile = br.readLine();
//				System.out.println(zeile);
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	
//	public void writeLog(String text) {
//		try {
//			FileWriter fw = new FileWriter("D:\\Errorlog.log");
//			BufferedWriter bw = new BufferedWriter(fw);
//			bw.write(text);
//			bw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
