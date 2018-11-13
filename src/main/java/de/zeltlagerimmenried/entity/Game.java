package de.zeltlagerimmenried.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import de.zeltlagerimmenried.helper.ReturnMessage;

@Entity
public class Game  extends ReturnMessage  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idGame;

	@Column(nullable = false, columnDefinition = "VARCHAR(45)", unique=true)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "idGame")
	private List<Team> teams = new ArrayList<>();

	

	//SETTER & GETTER
	public Integer getIdGame() {
		return idGame;
	}

	public void setIdGame(Integer idGame) {
		this.idGame = idGame;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	
	
	

}
