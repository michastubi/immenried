package de.zeltlagerimmenried.dto;

import java.time.LocalDateTime;


public class PictureDto {
	
	private Integer idPicture;
	
	private String fileExtension;
	
	private LocalDateTime dateTime;
	
	private Integer idTeam;

	public Integer getIdPicture() {
		return idPicture;
	}

	public void setIdPicture(Integer idPicture) {
		this.idPicture = idPicture;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}
	
	
	
}
