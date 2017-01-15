package de.isr.Imagesortierer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Hashcheck {

public boolean mdFiveHash(File currentfile, File destinationSortPath){
		
		File filein = currentfile;
		File fileout = destinationSortPath;

		InputStream fisIn = null;
		InputStream fisOut = null;
		String md5srFileIn = null;
		String md5dtFileOut = null;
		
		boolean hashMDFiveStatus=false;
		
		if (fileout.exists()==true) {					
			
			try {
				fisIn = new FileInputStream(filein);
				fisOut = new FileInputStream(fileout);
				//OutputStream out = new FileOutputStream(fileout);

			} catch (IOException e) {				
				e.printStackTrace();
			}

			try {
				md5srFileIn = org.apache.commons.codec.digest.DigestUtils.md5Hex(fisIn);
				System.out.println("Hash der ersten Datei "+md5srFileIn);
				fisIn.close();

			} catch (IOException e1) {				
				e1.printStackTrace();
			}
			try {
				md5dtFileOut = org.apache.commons.codec.digest.DigestUtils.md5Hex(fisOut);
				System.out.println("Hash der zweiten Datei "+md5dtFileOut);
				fisOut.close();
			} catch (IOException e1) {			
				e1.printStackTrace();
			}
			
			if (md5srFileIn.equals(md5dtFileOut)) {

				System.out.println(md5srFileIn + " und " + md5dtFileOut + " sind gleich");
				hashMDFiveStatus=true;
			}

			if (!md5srFileIn.equals(md5dtFileOut)) {

				System.out.println(md5srFileIn + " und " + md5dtFileOut + " sind nicht gleich");
				hashMDFiveStatus=false;
			}
		}		
		return hashMDFiveStatus;
	}

	
	
}
