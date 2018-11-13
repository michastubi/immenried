package de.zeltlagerimmenried.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import de.zeltlagerimmenried.entity.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{
	Optional<Message> findFirstByIdTeamOrderByIdMessageDesc(Integer idTeam);
	
}