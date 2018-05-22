package de.zeltlagerimmenried.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import de.zeltlagerimmenried.entity.Game;

public interface GameRepository extends CrudRepository<Game, Long>{
	Optional<Game> findByIdGame(Integer idGame);
	Optional<Game> findByName(String name);
}
