package br.com.tonim.petclinic.repositories;

import br.com.tonim.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
