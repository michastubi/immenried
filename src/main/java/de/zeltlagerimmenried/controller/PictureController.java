package de.zeltlagerimmenried.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import de.zeltlagerimmenried.dto.PictureDto;
import de.zeltlagerimmenried.dto.PictureUploadDto;
import de.zeltlagerimmenried.entity.Picture;
import de.zeltlagerimmenried.helper.ReturnMessage;
import de.zeltlagerimmenried.repository.PictureRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/api/picture") // This means URL's start with /api/picture (after Application path)
public class PictureController {

	@Autowired
	private PictureRepository pictureRepository;
	
	@GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getPictureWithoutBlob(@PathVariable Integer id) {
		ModelMapper modelMapper = new ModelMapper();
		Optional<Picture> optionalPicture = pictureRepository.findByIdPicture(id);
		try {
			Picture picture = optionalPicture.get();
			PictureDto pictureDto = modelMapper.map(picture, PictureDto.class);
			return pictureDto;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return new ReturnMessage(e.getMessage());
			}
	}
	
	@GetMapping(path = "/team/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getPictureWithoutBlobByTeamId(@PathVariable Integer id) {
		ModelMapper modelMapper = new ModelMapper();
		Optional<Picture> optionalPicture = pictureRepository.findByIdTeam(id);
		try {
			Picture picture = optionalPicture.get();
			PictureDto pictureDto = modelMapper.map(picture, PictureDto.class);
			return pictureDto;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return new ReturnMessage(e.getMessage());
			}
	}
	
	@GetMapping(path = "/file/id/{id}")
	public void getPictureFile(@PathVariable Integer id, HttpServletResponse response) {
		Optional<Picture> optionalPicture = pictureRepository.findByIdPicture(id);
		
		try {
			Picture picture = optionalPicture.get();
			if(picture.getPicture() == null) {
				return;
			}
			// Get your file stream from wherever.
			ByteArrayInputStream stream = new ByteArrayInputStream(picture.getPicture());

			// Set the content type and attachment header.
			response.addHeader("Content-disposition", "attachment;filename=" + picture.getIdPicture() + picture.getFileExtension());
			if(picture.getFileExtension() == ".jpg" || picture.getFileExtension() == ".jpeg") {
				response.setContentType("image/jpeg");
			}
			if(picture.getFileExtension() == "png") {
				response.setContentType("image/png");
			}
			
			// Copy the stream to the response's output stream.
			IOUtils.copy(stream, response.getOutputStream());
			response.flushBuffer();
			
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
	}
	
	@GetMapping(path = "/file/team/{id}")
	public void getPictureFileByTeamId(@PathVariable Integer id, HttpServletResponse response) {
		Optional<Picture> optionalPicture = pictureRepository.findByIdTeam(id);
		try {
			Picture picture = optionalPicture.get();
			// Get your file stream from wherever.
			ByteArrayInputStream stream = new ByteArrayInputStream(picture.getPicture());

			// Set the content type and attachment header.
			response.addHeader("Content-disposition", "attachment;filename=" + picture.getIdPicture() + picture.getFileExtension());
			if(picture.getFileExtension() == ".jpg" || picture.getFileExtension() == ".jpeg") {
				response.setContentType("image/jpeg");
			}
			if(picture.getFileExtension() == "png") {
				response.setContentType("image/png");
			}
			
			// Copy the stream to the response's output stream.
			IOUtils.copy(stream, response.getOutputStream());
			response.flushBuffer();
			
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
	}
	
	@PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object addNewPicture(@RequestBody PictureUploadDto picture) {
		//Delete old Picture
		Optional<Picture> optionalPicture = pictureRepository.findByIdTeam(picture.getIdTeam());
		try {
			Picture newPicture = new Picture();
			
			newPicture.setDateTime(LocalDateTime.now());
			newPicture.setFileExtension(picture.getFileExtension());
			newPicture.setIdTeam(picture.getIdTeam());
			newPicture.getPicture();
			newPicture.setPicture(Base64.decodeBase64(picture.getFile()));
			
			if(optionalPicture.isPresent()) {
				Picture oldPicture = optionalPicture.get();
				pictureRepository.delete(oldPicture);
			}
			pictureRepository.save(newPicture);
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("addNewPicture: Error");
				
			}
		
		return new ReturnMessage("Picture Upload complete");
	}
	
	@PostMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object addNewPictureFile(@PathVariable Integer id, @RequestParam("File") String file) {
		Optional<Picture> optionalPicture = pictureRepository.findByIdPicture(id);
		Picture picture = null;
		byte[] pictureBytes = null;
		try {
			picture = optionalPicture.get();	
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return new ReturnMessage(e.getMessage() + ": Wrong Picture ID");
		}
		
		
		pictureBytes = file.getBytes();
		
		
		picture.getPicture();
		picture.setPicture(pictureBytes);
		pictureRepository.save(picture);

		
		return new ReturnMessage(true);
	}
	
	@DeleteMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ReturnMessage deletePicture(@RequestBody Picture picture) {
		ReturnMessage msg = new ReturnMessage();
		Optional<Picture> optionalDeletePicture = pictureRepository.findByIdPicture(picture.getIdPicture());
		try {
		Picture deletePicture = optionalDeletePicture.get();
		pictureRepository.delete(deletePicture);
		msg.setSuccess();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			msg.setStatus(e.getMessage());
		}
		
		return msg;
	}
	
}
