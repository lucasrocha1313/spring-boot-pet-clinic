package br.com.tonim.petclinic.repositories;

import br.com.tonim.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLikeIgnoreCase(String lastName);
}
