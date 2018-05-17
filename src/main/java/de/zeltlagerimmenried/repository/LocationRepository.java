package de.zeltlagerimmenried.repository;

import org.springframework.data.repository.CrudRepository;
import de.zeltlagerimmenried.entity.Location;

public interface LocationRepository extends CrudRepository<Location, Long>{

}