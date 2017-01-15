package de.isr.Imagesortierer;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CurrentFile {
	private ArrayList<Object> currentfilelist;
	private File currentfile;
	private String filecurrentPath;
	private String fileFullName;
	private String fileFirstName;
	private String fileExtention;
	private String fileCopyName;
	private String Grundpath;
	private File editfile;

	private ArrayList<Object> destinationfilelist;
	private File destinationtfile;
	private String fileDestinationPath;
	private String fileDestinationFullName;
	private String fileDestinationFirstName;
	private String fileDestinationExtention;
	private String fileDestinationCopyName;
	private File fileDestinationeditfile;
	private String GrundDestinationpath;
	

	public String getGrundpath() {
		return Grundpath;
	}

	public void setGrundpath(String grundpath) {
		Grundpath = grundpath;
	}

	public String getGrundDestinationpath() {
		return GrundDestinationpath;
	}

	public void setGrundDestinationpath(String grundDestinationpath) {
		GrundDestinationpath = grundDestinationpath;
	}

	public void SetnewFile(String filecurrentPath) {
		this.filecurrentPath = filecurrentPath;
		File setcurrentfile = new File(this.filecurrentPath);
		setCurrentfile(setcurrentfile);
		fileFullName = currentfile.getName();
		String[] filesplit = fileFullName.split(Pattern.quote("."));
		fileFirstName = filesplit[0];
		fileExtention = currentfile.getName().substring(currentfile.getName().lastIndexOf(".") + 1,
				currentfile.getName().length());
		System.out.println("Datei wurde zerlegt im Fullfilename=" + fileFullName + ", filename=" + fileFirstName
				+ ", extantion=" + fileExtention);
		// currentfilelist.add(filecurrentPath);
	}

	public void SetDestinationFile(String fileDestinationPath) {
		this.fileDestinationPath = fileDestinationPath;
		destinationtfile = new File(this.fileDestinationPath);
		fileDestinationFullName = destinationtfile.getName();
		String[] filesplit = fileDestinationFullName.split(Pattern.quote("."));
		fileDestinationFirstName = filesplit[0];
		fileDestinationExtention = destinationtfile.getName().substring(destinationtfile.getName().lastIndexOf(".") + 1,
				destinationtfile.getName().length());
		System.out.println("Datei wurde zerlegt im Fullfilename=" + fileDestinationFullName + ", filename="
				+ fileDestinationFirstName + ", extantion=" + fileExtention);
		// destinationfilelist.add(fileDestinationPath);
	}

	public File CreateDestinationpath(String fullName, String year, String month, String day, String sortPath,
			Object sortTyp) {
		File yearfile = new File(sortPath + "\\" + year + "\\");
		File monthsfile = new File(sortPath + "\\" + year + "\\" + month + "\\");
		File dayfile = new File(sortPath + "\\" + year + "\\" + month + "\\" + day + "\\");
		File sortFile = new File(sortPath);
		File destinationSortPath = new File("");

		if (!sortFile.exists()) {
			sortFile.mkdirs();
		}

		if (sortTyp.equals("non") == true) {
			destinationSortPath = new File(sortPath + fullName);
		}

		if (sortTyp.equals("Year") == true) {
			if (!yearfile.exists()) {
				yearfile.mkdir();
			}
			destinationSortPath = new File(sortPath + "\\" + year + "\\" + fullName);
		}

		if (sortTyp.equals("Month") == true) {
			if (!yearfile.exists()) {
				yearfile.mkdir();
			}
			if (!monthsfile.exists()) {
				monthsfile.mkdir();

			}
			destinationSortPath = new File(sortPath + "\\" + year + "\\" + month + "\\" + fullName);
		}

		if (sortTyp.equals("Day") == true) {
			if (!yearfile.exists()) {
				yearfile.mkdir();
			}
			if (!monthsfile.exists()) {
				monthsfile.mkdir();
			}

			if (!dayfile.exists()) {
				dayfile.mkdir();
			}
			destinationSortPath = new File(sortPath + "\\" + year + "\\" + month + "\\" + day + "\\" + fullName);
		}
		return destinationSortPath;
	}

	public File CreateDestinationpath(String fullName, String sortPath, Object sortTyp) {

		File sortFile = new File(sortPath + "\\invalidData\\");
		File destinationSortPath = new File("");

		if (!sortFile.exists()) {
			sortFile.mkdirs();
		}
		fileDestinationPath = sortFile+"\\"+fullName;
		destinationSortPath = new File(sortFile+"\\"+fullName);
		System.out.println("Invalide Datei Pfad="+destinationSortPath);
		return destinationSortPath;
	}
	
	public File CreateDestinationpathforDoubleData(String fullName,String sortPath) {

		File sortFile = new File(sortPath + "\\double\\");
		File destinationSortPath = new File("");

		if (!sortFile.exists()) {
			sortFile.mkdirs();
		}
		fileDestinationPath = sortFile+"\\"+fullName;
		destinationSortPath = new File(sortFile+"\\"+fullName);
		System.out.println("doppelte Datei Pfad="+destinationSortPath);
		return destinationSortPath;
	}

	public File getCurrentfile() {
		return currentfile;
	}

	public void setCurrentfile(File currentfile) {
		this.currentfile = currentfile;
	}

	public File getEditfile() {
		return editfile;
	}

	public void setEditfile(File editfile) {
		this.editfile = editfile;
	}

	public String getFilecurrentPath() {
		return filecurrentPath;
	}

	public void setFilecurrentPath(String filecurrentPath) {
		this.filecurrentPath = filecurrentPath;
	}

	public String getFileDestinationPath() {
		return fileDestinationPath;
	}

	public void setFileDestinationPath(String fileDestinationPath) {
		this.fileDestinationPath = fileDestinationPath;
	}

	public String getFileFullName() {
		return fileFullName;
	}

	public void setFileFullName(String fileFullName) {
		this.fileFullName = fileFullName;
	}

	public String getFileFirstName() {
		return fileFirstName;
	}

	public void setFileFirstName(String fileFirstName) {
		this.fileFirstName = fileFirstName;
	}

	public String getFileExtention() {
		return fileExtention;
	}

	public void setFileExtention(String fileExtention) {
		this.fileExtention = fileExtention;
	}

	public String getFileCopyName() {
		return fileCopyName;
	}

	public void setFileCopyName(String fileCopyName) {
		this.fileCopyName = fileCopyName;
	}

	public ArrayList<Object> getCurrentfilelist() {
		return currentfilelist;
	}

	public void setCurrentfilelist(ArrayList<Object> currentfilelist) {
		this.currentfilelist = currentfilelist;

	}

	public ArrayList<Object> getDestinationfilelist() {
		return destinationfilelist;
	}

	public void setDestinationfilelist(ArrayList<Object> destinationfilelist) {
		this.destinationfilelist = destinationfilelist;
	}

	public File getDestinationtfile() {
		return destinationtfile;
	}

	public void setDestinationtfile(File destinationtfile) {
		this.destinationtfile = destinationtfile;
	}

	public String getFileDestinationFullName() {
		return fileDestinationFullName;
	}

	public void setFileDestinationFullName(String fileDestinationFullName) {
		this.fileDestinationFullName = fileDestinationFullName;
	}

	public String getFileDestinationFirstName() {
		return fileDestinationFirstName;
	}

	public void setFileDestinationFirstName(String fileDestinationFirstName) {
		this.fileDestinationFirstName = fileDestinationFirstName;
	}

	public String getFileDestinationExtention() {
		return fileDestinationExtention;
	}

	public void setFileDestinationExtention(String fileDestinationExtention) {
		this.fileDestinationExtention = fileDestinationExtention;
	}

	public String getFileDestinationCopyName() {
		return fileDestinationCopyName;
	}

	public void setFileDestinationCopyName(String fileDestinationCopyName) {
		this.fileDestinationCopyName = fileDestinationCopyName;
	}

	public File getFileDestinationeditfile() {
		return fileDestinationeditfile;
	}

	public void setFileDestinationeditfile(File fileDestinationeditfile) {
		this.fileDestinationeditfile = fileDestinationeditfile;
	}
}
