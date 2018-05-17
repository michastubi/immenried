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

	
	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object addNewGame(@RequestBody Team team) {
		Team newTeam = new Team();
		
		newTeam.setName(team.getName());
		newTeam.setAbhoerenCount(team.getAbhoerenCount());
		newTeam.setFotoCount(team.getFotoCount());
		newTeam.setOrtungCount(team.getOrtungCount());
		newTeam.setProviantRequest(team.getProviantRequest());
		newTeam.setMitarbeiterPin(this.giveUnusedPin());
		newTeam.setTeamPin(this.giveUnusedPin());
		
		try {
		Optional<Game> optionalGame = gameRepository.findByIdGame(team.getGame().getIdGame());
		Game game = optionalGame.get();
		newTeam.setGame(game);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return new ReturnMessage(e.getMessage() + ": Wrong Game ID");
		}

		teamRepository.save(newTeam);
		return newTeam;
	}
	
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
	
	
	/*@PostMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ReturnMessage deleteGame(@RequestBody Game game) {
		ReturnMessage msg = new ReturnMessage();
		Optional<Game> optionalDeleteGame = gameRepository.findByIdGame(game.getIdGame());
		
		try {
		Game deleteGame = optionalDeleteGame.get();
		gameRepository.delete(deleteGame);
		msg.setSuccess();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}*/
	
}
