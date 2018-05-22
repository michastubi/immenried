package de.zeltlagerimmenried.controller;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.zeltlagerimmenried.entity.Game;
import de.zeltlagerimmenried.entity.Team;
import de.zeltlagerimmenried.helper.ReturnMessage;
import de.zeltlagerimmenried.repository.GameRepository;
import de.zeltlagerimmenried.repository.TeamRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/api/team") // This means URL's start with /api/team (after Application path)
public class TeamController {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private GameRepository gameRepository;

	private Integer giveUnusedPin() {
		Random rand = new Random();
		int randomNumber;
		Optional<Team> optionalTeam;
		boolean isUsed;
		do{
			isUsed = false;
			randomNumber = rand.nextInt(999999);
			optionalTeam = teamRepository.findByMitarbeiterPin(randomNumber);
			if(optionalTeam.isPresent()) {
				isUsed = true;
			}
			optionalTeam = teamRepository.findByTeamPin(randomNumber);
			if(optionalTeam.isPresent()) {
				isUsed = true;
			}
		}while(isUsed == true);
		
		return new Integer(randomNumber);
	}
	
	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object addNewTeam(@RequestBody Team team) {
		Team newTeam = new Team();
		
		newTeam.setName(team.getName());
		newTeam.setAbhoerenCount(team.getAbhoerenCount());
		newTeam.setFotoCount(team.getFotoCount());
		newTeam.setOrtungCount(team.getOrtungCount());
		newTeam.setProviantRequest(team.getProviantRequest());
		newTeam.setMitarbeiterPin(this.giveUnusedPin());
		newTeam.setTeamPin(this.giveUnusedPin());
		
		Optional<Game> optionalGame = gameRepository.findByIdGame(team.getIdGame());
		if(optionalGame.isPresent()) {
			newTeam.setIdGame(team.getIdGame());
		}
		else {
			return new ReturnMessage("Error: Wrong Game ID");
		}

		teamRepository.save(newTeam);
		return newTeam;
	}
	
	
	@PostMapping(path = "/changename", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object changeTeamName(@RequestBody Team postTeam) {
		Optional<Team> optionalTeam = teamRepository.findByIdTeam(postTeam.getIdTeam());
		
		try {
			Team team = optionalTeam.get();
			team.setName(postTeam.getName());
			teamRepository.save(team);
			return team;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return new ReturnMessage(e.getMessage() + ": Wrong Team ID");
				
			}
		
		
		
	}
	
	@PostMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ReturnMessage deleteTeam(@RequestBody Team team) {
		ReturnMessage msg = new ReturnMessage();
		Optional<Team> optionalDeleteTeam = teamRepository.findByIdTeam(team.getIdTeam());
		
		try {
		Team deleteTeam = optionalDeleteTeam.get();
		teamRepository.delete(deleteTeam);
		msg.setSuccess();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
}
