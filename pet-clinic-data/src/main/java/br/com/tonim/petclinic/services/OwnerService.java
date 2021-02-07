package br.com.tonim.petclinic.services;

import br.com.tonim.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner,Long>{
    Owner findByLastName(String lastName);
}
