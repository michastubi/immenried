package de.zeltlagerimmenried.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.zeltlagerimmenried.entity.Location;

public interface LocationRepository extends CrudRepository<Location, Long>{
	Optional<Location> findByIdLocation(Integer idLocation);
	List<Location> findByIdTeamAndMitarbeiter(Integer idTeam, Boolean active);
	Optional<Location> findFirstByIdTeamAndMitarbeiterOrderByUpdateDateTimeDesc(Integer idTeam, Boolean active);
}