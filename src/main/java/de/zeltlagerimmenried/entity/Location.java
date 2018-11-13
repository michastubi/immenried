package de.zeltlagerimmenried.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.UpdateTimestamp;

import de.zeltlagerimmenried.helper.ReturnMessage;

@Entity
public class Location extends ReturnMessage  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idLocation;
	
	@Column(nullable = false)
	private Double latitude;
	
	@Column(nullable = false)
	private Double longitude;
	
	@Column(nullable = true)
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	@Column(nullable = false)
	private Boolean mitarbeiter;
	
	@Column(nullable = false)
	private Integer idTeam;

	//SETTER & GETTER
	
	public Integer getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(Integer idLocation) {
		this.idLocation = idLocation;
	}
	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public Boolean getMitarbeiter() {
		return mitarbeiter;
	}

	public void setMitarbeiter(Boolean mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	public Integer getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}
	
}
