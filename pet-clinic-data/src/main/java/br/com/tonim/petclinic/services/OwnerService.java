package br.com.tonim.petclinic.services;

import br.com.tonim.petclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner,Long>{
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
