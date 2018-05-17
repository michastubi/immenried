package de.zeltlagerimmenried.repository;

import org.springframework.data.repository.CrudRepository;
import de.zeltlagerimmenried.entity.Picture;

public interface PictureRepository extends CrudRepository<Picture, Long>{

}