package br.com.tonim.petclinic.services.map;

import br.com.tonim.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final Long ownerId = 1L;
    final String lastName = "Rocha";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findById() {
        var owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveNewId() {
        var ownerId2 = 2L;
        var owner2 = Owner.builder().id(ownerId2).build();
        var ownerSaved = ownerMapService.save(owner2);

        assertEquals(ownerId2, ownerSaved.getId());
    }

    @Test
    void saveNoId() {
        var ownerToSave = Owner.builder().build();
        var ownerSaved = ownerMapService.save(ownerToSave);

        assertNotNull(ownerSaved);
        assertNotNull(ownerSaved.getId());
    }

    @Test
    void findAll() {
        var owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        var owner = ownerMapService.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastNameNotFound() {
        var owner = ownerMapService.findByLastName("qq coisa");
        assertNull(owner);
    }
}