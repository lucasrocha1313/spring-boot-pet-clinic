package br.com.tonim.petclinic.repositories;

import br.com.tonim.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
