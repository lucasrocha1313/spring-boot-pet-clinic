package br.com.tonim.petclinic.repositories;

import br.com.tonim.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
