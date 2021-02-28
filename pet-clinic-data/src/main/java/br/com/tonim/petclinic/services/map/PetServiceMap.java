package br.com.tonim.petclinic.services.map;

import br.com.tonim.petclinic.model.Pet;
import br.com.tonim.petclinic.services.CrudService;
import br.com.tonim.petclinic.services.PetService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet,Long> implements PetService {
    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }
}
