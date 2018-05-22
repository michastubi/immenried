package de.zeltlagerimmenried.dto;

import java.time.LocalDateTime;

public class AudioDto {
	
	private Integer idAudio;
	private String name;
	private LocalDateTime updateDateTime;
	private Boolean active;	
	private Integer idTeam;

	
	
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
