package de.isr.Imagesortierer;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.iptc.IptcReader;

public class Datecheck {
	private String Originaldatum = "";

	private String planedDate = null;
	private boolean copyTyp = false;
	private File rootfile;
	private String fileYear;
	private String fileMonth;
	private String fileDay;
	
	CurrentFile currentFile;
	
	public String getPlanedDate() {
		return planedDate;
	}

	public void setPlanedDate(String planedDate) {
		this.planedDate = planedDate;
	}

	public boolean isCopyTyp() {
		return copyTyp;
	}

	public void setCopyTyp(boolean copyTyp) {
		this.copyTyp = copyTyp;
	}

	public File getRootfile() {
		return rootfile;
	}

	public void setRootfile(File rootfile) {
		this.rootfile = rootfile;
	}

	public String getFileYear() {
		return fileYear;
	}

	public void setFileYear(String fileYear) {
		this.fileYear = fileYear;
	}

	public String getFileMonth() {
		return fileMonth;
	}

	public void setFileMonth(String fileMonth) {
		this.fileMonth = fileMonth;
	}

	public String getFileDay() {
		return fileDay;
	}

	public void setFileDay(String fileDay) {
		this.fileDay = fileDay;
	}
	
	public String getOriginaldatum() {
		return Originaldatum;
	}

	public void setOriginaldatum(String originaldatum) {
		Originaldatum = originaldatum;
	}

	public void main(File rootfile,CurrentFile curentfile) {
		this.currentFile = curentfile;
		Planedatechecker(rootfile);
		planedDate = getOriginaldatum();
		copyTyp = false;
		TitelDatechecker(rootfile);
	}

	private void Planedatechecker(File rootfile) {
		File file = rootfile;

		try {
			Metadata metadata = ImageMetadataReader.readMetadata(file);

			print(metadata);
		} catch (ImageProcessingException e) {
			// handle exception
		} catch (IOException e) {
			// handle exception
		}

		try {
			Metadata metadata = JpegMetadataReader.readMetadata(file);

			print(metadata);
		} catch (JpegProcessingException e) {
			// handle exception
		} catch (IOException e) {
			// handle exception
		}

		try {
			// We are only interested in handling
			Iterable<JpegSegmentMetadataReader> readers = Arrays.asList(new ExifReader(), new IptcReader());

			Metadata metadata = JpegMetadataReader.readMetadata(file, readers);

			print(metadata);
		} catch (JpegProcessingException e) {
			// handle exception
		} catch (IOException e) {
			// handle exception
		}
	}

	private void print(Metadata metadata) {
		for (Directory directory : metadata.getDirectories()) {
			//
			// Each Directory stores values in Tag objects
			//
			for (Tag tag : directory.getTags()) {
				if (tag.toString().contains("[Exif IFD0] Date/Time") == true) {
					String x = tag.toString().substring(24, 34).replace(":", ".");
					String Jahr = x.substring(0, 4);
					String Monat = x.substring(5, 7);
					String Tag = x.substring(8, 10);
					Originaldatum = Tag + "." + Monat + "." + Jahr;
				}
			}
			//
			// Each Directory may also contain error messages
			//
			if (directory.hasErrors()) {
				for (String error : directory.getErrors()) {
					System.err.println("ERROR: " + error);
				}
			}
		}

	}

	public void TitelDatechecker(File rootfile) {

		boolean copyTyp = false;
		String dateTyp="";

			
		
		
		// wenn die Datei ein Aufnahmedatum enthält.
		if (!planedDate.equals("") && copyTyp == false) {
			try {
				fileYear = planedDate.substring(6, 10);
				fileMonth = planedDate.substring(3, 5);
				fileDay = planedDate.substring(0, 2);
				
				try {
					DateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
					dateformat.setLenient(false);
					dateTyp = "planedDate";
					copyTyp = true;
				} catch (Exception e) {
					copyTyp = false;
					e.printStackTrace();
				}				
			} catch (Exception e) {	
			}
		}
		
		// wenn Die Datei kein Aufnahmedatum enthält
		// Wird nach einem Datum im Titel gesucht (Datetyp yyy.MM.dd).
		if (planedDate.equals("") && copyTyp == false) {
			try {
				fileDay = currentFile.getFileFirstName().substring(0, 4);
				fileMonth = currentFile.getFileFirstName().substring(4, 6);
				fileYear = currentFile.getFileFirstName().substring(6, 8);
				
				String nameDate = fileDay + "." + fileMonth + "." + fileYear;
				System.out.println("Datei Datum = "+nameDate);
				DateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
				dateformat.setLenient(false);
				try {
					Date date = dateformat.parse(nameDate);
					Originaldatum = date.toString();
					dateTyp = "Datetyp yyy.MM.dd";
					copyTyp = true;
				} catch (Exception e) {
					copyTyp = false;
				}				
			} catch (Exception e1) {
			}
			
			
		}
		
		// wenn Die Datei kein Aufnahmedatum enthält
		// Wird nach einem Datum im Titel gesucht (Datetyp dd.MM.yyyy).
		if (planedDate.equals("") && copyTyp == false) {
			try {
				fileDay = currentFile.getFileFirstName().substring(0, 2);
				fileMonth = currentFile.getFileFirstName().substring(2, 4);
				fileYear = currentFile.getFileFirstName().substring(4, 8);
				String nameDate = fileDay + "." + fileMonth + "." + fileYear;
				System.out.println("Datei Datum = "+nameDate);
				DateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
				dateformat.setLenient(false);
				try {
					System.out.println("Datei Datum = "+Originaldatum);
					Date date = dateformat.parse(nameDate);
					Originaldatum = date.toString();
					dateTyp = "Datetyp dd.MM.yyyy";
					copyTyp = true;
				} catch (Exception e) {
					copyTyp = false;
				}
			} catch (Exception e1) {
			}
			
		}	
		
		if (planedDate.equals("") && copyTyp == false && dateTyp.equals("")) {
			Originaldatum="";
		}

	}

}