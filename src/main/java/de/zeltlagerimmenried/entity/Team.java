package de.zeltlagerimmenried.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Team {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idTeam;
	
	@Column(nullable = false, columnDefinition = "VARCHAR(45)", unique=true)
	private String name;

	@Column(nullable = false)
	private Integer teamPin;
	
	@Column(nullable = false)
	private Integer mitarbeiterPin;
	
	@Column(nullable = false)
	private Integer ortungCount;
	
	@Column(nullable = false)
	private Integer abhoerenCount;
	
	@Column(nullable = false)
	private Integer fotoCount;
	
	@Column(nullable = false)
	private Boolean proviantRequest;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="idGame")
	private Game game;

	
	//SETTER & GETTER
	public Integer getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTeamPin() {
		return teamPin;
	}

	public void setTeamPin(Integer teamPin) {
		this.teamPin = teamPin;
	}

	public Integer getMitarbeiterPin() {
		return mitarbeiterPin;
	}

	public void setMitarbeiterPin(Integer mitarbeiterPin) {
		this.mitarbeiterPin = mitarbeiterPin;
	}

	public Integer getOrtungCount() {
		return ortungCount;
	}

	public void setOrtungCount(Integer ortungCount) {
		this.ortungCount = ortungCount;
	}

	public Integer getAbhoerenCount() {
		return abhoerenCount;
	}

	public void setAbhoerenCount(Integer abhoerenCount) {
		this.abhoerenCount = abhoerenCount;
	}

	public Integer getFotoCount() {
		return fotoCount;
	}

	public void setFotoCount(Integer fotoCount) {
		this.fotoCount = fotoCount;
	}

	public Boolean getProviantRequest() {
		return proviantRequest;
	}

	public void setProviantRequest(Boolean proviantRequest) {
		this.proviantRequest = proviantRequest;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	
	

}
