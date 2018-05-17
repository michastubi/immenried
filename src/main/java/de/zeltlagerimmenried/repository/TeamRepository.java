package de.zeltlagerimmenried.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.zeltlagerimmenried.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Long>{
	Optional<Team> findByIdTeam(Integer idTeam);
	Optional<Team> findByTeamPin(Integer TeamPin);
	Optional<Team> findByMitarbeiterPin(Integer MitarbeiterPin);
}