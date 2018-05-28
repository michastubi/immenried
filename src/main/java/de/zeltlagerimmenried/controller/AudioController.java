package de.zeltlagerimmenried.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import de.zeltlagerimmenried.dto.AudioDto;
import de.zeltlagerimmenried.entity.Audio;
import de.zeltlagerimmenried.helper.ReturnMessage;
import de.zeltlagerimmenried.repository.AudioRepository;



@Controller    // This means that this class is a Controller
@RequestMapping(path="/api/audio") // This means URL's start with /api/audio (after Application path)
public class AudioController {
	
	@Autowired
	private AudioRepository audioRepository;
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AudioDto getAudioWithoutBlob(@PathVariable Integer id) {
		ModelMapper modelMapper = new ModelMapper();
		Optional<Audio> optionalAudio = audioRepository.findByIdAudio(id);
		try {
			Audio audio = optionalAudio.get();
			AudioDto audioDto = modelMapper.map(audio, AudioDto.class);
			return audioDto;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
	}
	
	@GetMapping(path = "/file/{id}")
	public void getAudioFile(@PathVariable Integer id, HttpServletResponse response) {
		Optional<Audio> optionalAudio = audioRepository.findByIdAudio(id);
		try {
			Audio audio = optionalAudio.get();
			// Get your file stream from wherever.
			ByteArrayInputStream stream = new ByteArrayInputStream(audio.getAudio());

			// Set the content type and attachment header.
			response.addHeader("Content-disposition", "attachment;filename=" + audio.getName() + ".mp3");
			response.setContentType("audio/mpeg");

			// Copy the stream to the response's output stream.
			IOUtils.copy(stream, response.getOutputStream());
			response.flushBuffer();
			
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
	}
	
	@GetMapping(path = "/team/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AudioDto> getAllAudiosForTeam(@PathVariable Integer id) {
		ModelMapper modelMapper = new ModelMapper();
		List<AudioDto> listAudioDto = new ArrayList<AudioDto>();
		List<Audio> listAudio = audioRepository.findByIdTeamOrderByNameAsc(id);
		for (Audio audio : listAudio) {
			AudioDto audioDto = modelMapper.map(audio, AudioDto.class);
			listAudioDto.add(audioDto);
		}
		return listAudioDto;
	}
	

	@PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object addNewAudio(@RequestBody Audio audio) {
		Audio newAudio = new Audio();
		
		newAudio.setActive(false);
		newAudio.setIdTeam(audio.getIdTeam());
		newAudio.setName(audio.getName());
		newAudio.setUpdateDateTime(null);
		newAudio.setAudio(null);
		
		audioRepository.save(newAudio);
		
		return newAudio;
	}
	
	@PostMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object addNewAudioFile(@PathVariable Integer id, @RequestParam("File") MultipartFile file) {
		Optional<Audio> optionalAudio = audioRepository.findByIdAudio(id);
		Audio audio = null;
		byte[] audioBytes = null;
		try {
			audio = optionalAudio.get();	
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return new ReturnMessage(e.getMessage() + ": Wrong Audio ID");
		}
		
		try {
			audioBytes = file.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
			return new ReturnMessage(e.getMessage());
		}
		audio.setUpdateDateTime(LocalDateTime.now());
		audio.getAudio();
		audio.setAudio(audioBytes);
		audioRepository.save(audio);

		
		return new ReturnMessage(true);
	}
	
	@PutMapping(path = "/activate", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ReturnMessage setAudioActive(@RequestBody Audio audio) {
		ReturnMessage msg = new ReturnMessage();
				
		//Find Audio, which should be activated
		Optional<Audio> optionalAudio = audioRepository.findByIdAudio(audio.getIdAudio());
		Audio newAudio = null;
		
		try {
		newAudio = optionalAudio.get();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			msg.setMessage(e.getMessage());
			return msg;
		}
		
		//Find all active and set inactive
		List<Audio> activeAudios = audioRepository.findByIdTeamAndActive(newAudio.getIdTeam(), true);
		for (Audio activeAudio : activeAudios) {
			activeAudio.setActive(false);
			audioRepository.save(activeAudio);
		}
		
		newAudio.setActive(true);
		audioRepository.save(newAudio);
		msg.setSuccess();
		
		return msg;
	}
		

	@DeleteMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ReturnMessage deleteAudio(@RequestBody Audio audio) {
		ReturnMessage msg = new ReturnMessage();
		Optional<Audio> optionalDeleteAudio = audioRepository.findByIdAudio(audio.getIdAudio());
		try {
		Audio deleteAudio = optionalDeleteAudio.get();
		audioRepository.delete(deleteAudio);
		msg.setSuccess();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
	
	

}
