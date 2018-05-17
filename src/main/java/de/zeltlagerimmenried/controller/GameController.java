package de.zeltlagerimmenried.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.zeltlagerimmenried.entity.Game;
import de.zeltlagerimmenried.entity.Team;
import de.zeltlagerimmenried.repository.GameRepository;
import de.zeltlagerimmenried.helper.*;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/api/game") // This means URL's start with /api/game (after Application path)
public class GameController {
	
	@Autowired
	private GameRepository gameRepository;

	
	
	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Game addNewGame(@RequestBody Game game) {
		Game newGame = new Game();
		newGame.setName(game.getName());
		gameRepository.save(newGame);
		
		return newGame;
	}
	
	
	@PostMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
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
	}
	
}
