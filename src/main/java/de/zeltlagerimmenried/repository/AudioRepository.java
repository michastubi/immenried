package de.zeltlagerimmenried.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import de.zeltlagerimmenried.entity.Audio;

public interface AudioRepository extends CrudRepository<Audio, Long>{
	Optional<Audio> findByIdAudio(Integer idAudio);
	List<Audio> findByIdTeamOrderByNameAsc(Integer idTeam);
	List<Audio> findByIdTeamAndActive(Integer idTeam, Boolean active);
	
}
