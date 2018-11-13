package de.zeltlagerimmenried.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import de.zeltlagerimmenried.entity.Message;
import de.zeltlagerimmenried.helper.ReturnMessage;
import de.zeltlagerimmenried.repository.MessageRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/api/message") // This means URL's start with /api/location (after Application path)
public class MessageController {
	@Autowired
	private MessageRepository messageRepository;

	@GetMapping(path = "/{idTeam}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getMessageByIdTeam(@PathVariable Integer idTeam) {
		Optional<Message> optionalMessage = messageRepository.findFirstByIdTeamOrderByIdMessageDesc(idTeam);
		try {
			Message message = optionalMessage.get();
			return message;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return new ReturnMessage(false);
			}
	}
	
	@PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object addMessage(@RequestBody Message message) {
		Message newMessage = new Message();
		
		newMessage.setIdTeam(message.getIdTeam());
		newMessage.setMsg(message.getMsg());
		
		messageRepository.save(newMessage);
		
		return newMessage;
	}
	
	
}
