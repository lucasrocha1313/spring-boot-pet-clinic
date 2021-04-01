package br.com.tonim.petclinic.repositories;

import br.com.tonim.petclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
