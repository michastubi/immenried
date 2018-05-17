package de.zeltlagerimmenried.entity;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;


@Entity
public class Audio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idAudio;
	
	@Column(nullable = false, columnDefinition = "VARCHAR(45)")
	private String name;
	
	@Column(nullable = false)
	private LocalDateTime updateDateTime;

	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(length=20971520)
	private byte[] audio;
	
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="idTeam")
	private Team team;

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


	public Team getTeam() {
		return team;
	}


	public void setTeam(Team team) {
		this.team = team;
	}



}
