package de.zeltlagerimmenried.controller;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.zeltlagerimmenried.dto.AudioDto;
import de.zeltlagerimmenried.entity.Audio;
import de.zeltlagerimmenried.repository.AudioRepository;



@Controller    // This means that this class is a Controller
@RequestMapping(path="/api/audio") // This means URL's start with /api/audio (after Application path)
public class AudioController {
	ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private AudioRepository audioRepository;
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AudioDto getAudioWithoutBlob(@PathVariable Integer id) {
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
	public @ResponseBody List<Audio> getAllAudiosForTeam(@PathVariable Integer id) {
		List<Audio> listAudio = audioRepository.findByIdTeamOrderByNameAsc(id);
		for (Audio audio : listAudio) {
			audio.setAudio(null);
		}
		return listAudio;
	}
	

		

}
