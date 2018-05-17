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
public class Picture {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idPicture;
	
	@Column(nullable = false, columnDefinition = "VARCHAR(45)")
	private String name;
	
	@Column(nullable = false)
	private LocalDateTime updateDateTime;

	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(length=1048576)
	private byte[] picture;
	
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="idTeam")
	private Team team;

	//SETTER & GETTER
	public Integer getIdPicture() {
		return idPicture;
	}


	public void setIdPicture(Integer idPicture) {
		this.idPicture = idPicture;
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


	public byte[] getPicture() {
		return picture;
	}


	public void setPicture(byte[] picture) {
		this.picture = picture;
	}


	public Team getTeam() {
		return team;
	}


	public void setTeam(Team team) {
		this.team = team;
	}
	
}
