package br.com.tonim.petclinic.services.springdatajpa;

import br.com.tonim.petclinic.model.Owner;
import br.com.tonim.petclinic.repositories.OwnerRepository;
import br.com.tonim.petclinic.repositories.PetRepository;
import br.com.tonim.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    static final String LAST_NAME = "Rocha";
    final Long ownerId = 1L;
    Owner owner;

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(ownerId).lastName(LAST_NAME).build();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(owner));
        var ownerReturned = ownerSDJpaService.findById(ownerId);
        assertNotNull(ownerReturned);
        assertEquals(ownerId, ownerReturned.getId());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(ownerId)).thenReturn(Optional.empty());
        var ownerReturned = ownerSDJpaService.findById(ownerId);
        assertNull(ownerReturned);
    }

    @Test
    void save() {
        when(ownerRepository.save(owner)).thenReturn(owner);
        var ownerSaved = ownerSDJpaService.save(owner);

        assertNotNull(ownerSaved);
    }

    @Test
    void findAll() {
        var returnedOwners = new HashSet<Owner>();
        returnedOwners.add(owner);
        returnedOwners.add(Owner.builder().id(2L).lastName("qq").build());
        when(ownerRepository.findAll()).thenReturn(returnedOwners);

        var owners = ownerSDJpaService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());

    }

    @Test
    void delete() {
        ownerSDJpaService.delete(owner);
        verify(ownerRepository).delete(owner);
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(owner.getId());
        verify(ownerRepository).deleteById(owner.getId());
    }

    @Test
    void findByLastName() {
        var idOwner = 1L;
        var owner = Owner.builder().id(idOwner).lastName(LAST_NAME).build();

        when(ownerRepository.findByLastName(LAST_NAME)).thenReturn(owner);
        var ownerReturned = ownerSDJpaService.findByLastName(LAST_NAME);

        assertNotNull(ownerReturned);
        assertEquals(idOwner, ownerReturned.getId());
        assertEquals(LAST_NAME, ownerReturned.getLastName());
    }
}