package de.zeltlagerimmenried.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.zeltlagerimmenried.entity.Picture;

public interface PictureRepository extends CrudRepository<Picture, Long>{
	Optional<Picture> findByIdPicture(Integer idPicture);
	Optional<Picture> findByIdTeam(Integer idTeam);
}