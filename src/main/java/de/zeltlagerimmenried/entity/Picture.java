package de.zeltlagerimmenried.entity;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import de.zeltlagerimmenried.helper.ReturnMessage;

@Entity
public class Picture extends ReturnMessage  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idPicture;
	
	@Column(nullable = true)
	private LocalDateTime dateTime;
	
	@Column(nullable = false, columnDefinition = "VARCHAR(45)")
	private String fileExtension;

	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(length=1048576)
	private byte[] picture;
	
	@Column(nullable = false)
	private Integer idTeam;

	//SETTER & GETTER
	public Integer getIdPicture() {
		return idPicture;
	}


	public void setIdPicture(Integer idPicture) {
		this.idPicture = idPicture;
	}


	public LocalDateTime getDateTime() {
		return dateTime;
	}


	public String getFileExtension() {
		return fileExtension;
	}


	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}


	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	public byte[] getPicture() {
		return picture;
	}


	public void setPicture(byte[] picture) {
		this.picture = picture;
	}


	public Integer getIdTeam() {
		return idTeam;
	}


	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}

	
}