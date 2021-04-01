package br.com.tonim.petclinic.repositories;

import br.com.tonim.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
