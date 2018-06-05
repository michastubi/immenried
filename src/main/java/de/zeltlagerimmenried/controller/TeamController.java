package de.zeltlagerimmenried.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping(path = "/game/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Team> getTeamById(@PathVariable Integer id) {
		Optional<Game> optionalGame = gameRepository.findByIdGame(id);
		
		try {
			Game game = optionalGame.get();
			return game.getTeams();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
	}
	
	@GetMapping(path = "/code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Team getTeamByCode(@PathVariable Integer code) {
		Optional<Team> optionalTeam = teamRepository.findByTeamPin(code);
		if(!optionalTeam.isPresent()) {
			optionalTeam = teamRepository.findByMitarbeiterPin(code);
		}
		
		try {
			Team team = optionalTeam.get();
			return team;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
	}	
	
	@PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	
	@PutMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object changeTeamName(@RequestBody Team putTeam) {
		Optional<Team> optionalTeam = teamRepository.findByIdTeam(putTeam.getIdTeam());
		
		try {
			Team team = optionalTeam.get();
			team.setName(putTeam.getName());
			team.setFotoCount(putTeam.getFotoCount());
			team.setOrtungCount(putTeam.getOrtungCount());
			team.setAbhoerenCount(putTeam.getAbhoerenCount());
			team.setProviantRequest(putTeam.getProviantRequest());
			
			teamRepository.save(team);
			return team;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return new ReturnMessage(e.getMessage() + ": Wrong Team ID");
				
			}
		
		
		
	}
	
	@DeleteMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
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
