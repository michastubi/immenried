package de.zeltlagerimmenried.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import de.zeltlagerimmenried.entity.Location;
import de.zeltlagerimmenried.helper.ReturnMessage;
import de.zeltlagerimmenried.repository.LocationRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/api/location") // This means URL's start with /api/location (after Application path)
public class LocationController {
	
	@Autowired
	private LocationRepository locationRepository;

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getLocationById(@PathVariable Integer id) {
		Optional<Location> optionalLocation = locationRepository.findByIdLocation(id);
		try {
			Location location = optionalLocation.get();
			return location;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return new ReturnMessage(false);
			}
	}
	
	@GetMapping(path = "/idTeam/{id}/mitarbeiter/{mitarbeiter}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getLocationByTeamIdAndMitarbeiter(@PathVariable("id") Integer idTeam, @PathVariable("mitarbeiter") boolean mitarbeiter) {
		List<Location> listLocation = locationRepository.findByIdTeamAndMitarbeiter(idTeam, mitarbeiter);
		
		if (listLocation.isEmpty()) {
			return new ReturnMessage("No Locations found");
		}
		else {
			return listLocation;
		}
		
	}
	
	@GetMapping(path = "/current/idTeam/{id}/mitarbeiter/{mitarbeiter}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getCurrentLocationByTeamIdAndMitarbeiter(@PathVariable("id") Integer idTeam, @PathVariable("mitarbeiter") boolean mitarbeiter) {
		Optional<Location> optionalLocation = locationRepository.findFirstByIdTeamAndMitarbeiterOrderByUpdateDateTimeDesc(idTeam, mitarbeiter);
		try {
			Location location = optionalLocation.get();
			return location;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return new ReturnMessage(false);
			}
	}
	
	@PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object addNewLocation(@RequestBody Location location) {
		Location newLocation = new Location();
		
		newLocation.setIdTeam(location.getIdTeam());
		newLocation.setLatitude(location.getLatitude());
		newLocation.setLongitude(location.getLongitude());
		newLocation.setMitarbeiter(location.getMitarbeiter());
		newLocation.setUpdateDateTime(LocalDateTime.now());
		
		
		locationRepository.save(newLocation);
		
		return newLocation;
	}
	
	@DeleteMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ReturnMessage deleteLocation(@RequestBody Location location) {
		ReturnMessage msg = new ReturnMessage();
		Optional<Location> optionalDeleteLocation = locationRepository.findByIdLocation(location.getIdLocation());
		try {
		Location deleteLocation = optionalDeleteLocation.get();
		locationRepository.delete(deleteLocation);
		msg.setSuccess();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
	
}
