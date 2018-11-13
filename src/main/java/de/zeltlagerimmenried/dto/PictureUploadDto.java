package de.zeltlagerimmenried.dto;

import java.time.LocalDateTime;



public class PictureUploadDto {

	
	private LocalDateTime dateTime;
	

	private String fileExtension;


	private String file;
	

	private Integer idTeam;


	public LocalDateTime getDateTime() {
		return dateTime;
	}


	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	public String getFileExtension() {
		return fileExtension;
	}


	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}


	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}


	public Integer getIdTeam() {
		return idTeam;
	}


	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}
	
	
}
