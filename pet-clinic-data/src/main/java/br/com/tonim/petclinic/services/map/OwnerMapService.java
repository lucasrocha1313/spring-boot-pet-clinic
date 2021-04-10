package br.com.tonim.petclinic.services.map;

import br.com.tonim.petclinic.model.Owner;
import br.com.tonim.petclinic.model.Pet;
import br.com.tonim.petclinic.services.OwnerService;
import br.com.tonim.petclinic.services.PetService;
import br.com.tonim.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if(object != null) {
            savePets(object);
            return super.save(object);
        }else {
            return null;
        }
    }

    private void savePets(Owner object) {
        if(object.getPets() != null) {
            object.getPets().forEach(p -> {
                savePetType(p);
                savePet(p);
            });
        }
    }

    private void savePet(Pet p) {
        if(p.getId() == null) {
            var petSaved = petService.save(p);
            p.setId(petSaved.getId());
        }
    }

    private void savePetType(Pet p) {
        if(p.getPetType() != null) {
            if(p.getPetType().getId() == null)
                p.setPetType(petTypeService.save(p.getPetType()));
        }
        else {
            throw new RuntimeException("Pet type is required");
        }
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(owner -> lastName.equalsIgnoreCase(owner.getLastName()))
                .findFirst()
                .orElse(null);
    }
}
