package fi.haagahelia.course.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MotivoRepository extends CrudRepository<Motivo, Long>  {
    
	List<Motivo> findByName(String name);
}

