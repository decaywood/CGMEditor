package net.sf.jcgm.properties;

import java.io.ObjectInputStream.GetField;
import java.io.Serializable;

public class FileNewProp implements Serializable{
	private double imageUnitSize ;
	private String cgmProfile;
	private String fileName;
	private double width;
	private double height;
	public FileNewProp(){
		
	}
	
	public String getCgmProfile() {
		return cgmProfile;
	}
	public String getFileName() {
		return fileName;
	}
	 
	public double getImageUnitSize() {
		return imageUnitSize;
	}
	public void setCgmProfile(String cgmProfile) {
		this.cgmProfile = cgmProfile;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setImageLengthHeight(String imageLengthHeight) {
		
		 if(imageLengthHeight.equalsIgnoreCase("8.0 x 10.5 Letter (ANSI A)")){
			 width = 8.0;
			 height = 10.5;
		 }
		 if(imageLengthHeight.equalsIgnoreCase("10.5 x 8.0 Letter (ANSI A) Rotated")){
			 width = 10.5;
			 height = 8.0;
		 }
		 if(imageLengthHeight.equalsIgnoreCase("10.0 x 16.0 Tabloid (ANSI B)")){
			 width = 10.0;
			 height = 16.0;
		 }
		 if(imageLengthHeight.equalsIgnoreCase("16.0 x 10.0 Tabloid (ANSI B) Rotated")){
			 width = 16.0;
			 height = 10.0;
		 }
		 if(imageLengthHeight.equalsIgnoreCase("16.0 x 21.0 ANSI C")){
			 width = 16.0;
			 height = 21.0;
		 }
		 if(imageLengthHeight.equalsIgnoreCase("21.0 x 16.0 ANSI C Rotated")){
			 width = 21.0;
			 height = 16.0;
		 }
		 if(imageLengthHeight.equalsIgnoreCase("21.0 x 33.0 ANSI D")){
			 width = 21.0;
			 height = 33.0;
		 }
		 if(imageLengthHeight.equalsIgnoreCase("33.0 x 21.0 ANSI D Rotated")){
			 width = 33.0;
			 height = 21.0;
		 }
		 if(imageLengthHeight.equalsIgnoreCase("33.0 x 43.0 ANSI E")){
			 width = 33.0;
			 height = 43.0;
			 
		 }
		 if(imageLengthHeight.equalsIgnoreCase("43.0 x 33.0 ANSI E Rotated")){
			 width = 43.0;
			 height = 33.0;
			 
		 }
		 if(imageLengthHeight.equalsIgnoreCase("198 x 285 A4")){
			 width = 198.0;
			 height = 285.0;
			 
		 }
		 if(imageLengthHeight.equalsIgnoreCase("285 x 198 A4 Rotated")){
			 width = 285.0;
			 height = 198.0;
			 
		 }
		 if(imageLengthHeight.equalsIgnoreCase("273 x 396 A3")){
			 width = 273.0;
			 height = 396.0;
			 
		 }
		 if(imageLengthHeight.equalsIgnoreCase("396 x 273 A3 Rotated")){
			 width = 396.0;
			 height = 273.0;
			 
		 }
		 if(imageLengthHeight.equalsIgnoreCase("396 x 570 A2")){
			 width = 396.0;
			 height =570.0;
			 
		 }
		 if(imageLengthHeight.equalsIgnoreCase("570 x 396 A2 Rotated")){
			 width = 570.0;
			 height =396.0;
			 
		 }
		 if(imageLengthHeight.equalsIgnoreCase("570 x 817 A1")){
			 width = 570.0;
			 height =817.0;
			 
		 }
		 if(imageLengthHeight.equalsIgnoreCase("817 x 570 A1 Rotated")){
			 width = 817.0;
			 height =570.0;
			 
		 }
		 if(imageLengthHeight.equalsIgnoreCase("817 x 1165 A0")){
			 width = 817.0;
			 height =1165.0;
			 
		 }
		 if(imageLengthHeight.equalsIgnoreCase("1165 x 817 A0 Rotated")){
			 width = 1165.0;
			 height = 817.0;
		 }
	}
	public void setImageUnitSize(double imageUnitSize) {
		this.imageUnitSize = imageUnitSize;
	}
	public double getHeight() {
		return height;
	}
	public double getWidth() {
		return width;
	}
	
}
