package de.zeltlagerimmenried.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.zeltlagerimmenried.entity.Location;

public interface LocationRepository extends CrudRepository<Location, Long>{
	@Query(value = "select id_location, latitude, longitude, mitarbeiter, update_date_time, id_team from location WHERE id_location IN" + 
			"(SELECT MAX(id_location) " + 
			"FROM location " + 
			"GROUP BY id_team, mitarbeiter)", nativeQuery = true)
	List<Location> findLocation();
	Optional<Location> findByIdLocation(Integer idLocation);
	List<Location> findByIdTeamAndMitarbeiter(Integer idTeam, Boolean active);
	Optional<Location> findFirstByIdTeamAndMitarbeiterOrderByUpdateDateTimeDesc(Integer idTeam, Boolean active);
}