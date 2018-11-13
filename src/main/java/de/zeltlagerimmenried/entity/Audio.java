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
public class Audio  extends ReturnMessage  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idAudio;
	
	@Column(nullable = false, columnDefinition = "VARCHAR(45)")
	private String name;
	
	@Column(nullable = true)
	private LocalDateTime updateDateTime;

	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(length=20971520)
	private byte[] audio;
	
	@Column(nullable = false)
	private Boolean active;
		
	@Column(nullable = false)
	private Integer idTeam;

	//SETTER & GETTER
	public Integer getIdAudio() {
		return idAudio;
	}


	public void setIdAudio(Integer idAudio) {
		this.idAudio = idAudio;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}


	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}


	public byte[] getAudio() {
		return audio;
	}


	public void setAudio(byte[] audio) {
		this.audio = audio;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public Integer getIdTeam() {
		return idTeam;
	}


	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}

}
