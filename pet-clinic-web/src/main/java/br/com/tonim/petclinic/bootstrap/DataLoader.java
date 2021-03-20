package br.com.tonim.petclinic.bootstrap;

import br.com.tonim.petclinic.model.Owner;
import br.com.tonim.petclinic.model.Pet;
import br.com.tonim.petclinic.model.PetType;
import br.com.tonim.petclinic.model.Vet;
import br.com.tonim.petclinic.services.OwnerService;
import br.com.tonim.petclinic.services.PetTypeService;
import br.com.tonim.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        var dog = new PetType();
        dog.setName("dog");
        var dogSaved = petTypeService.save(dog);

        var cat = new PetType();
        dog.setName("cat");
        var catSaved = petTypeService.save(cat);

        var owner1 = new Owner();
        owner1.setFirstName("Tonim");
        owner1.setLastName("Tunado");
        owner1.setAddress("Rua Altinopolis, 751");
        owner1.setCity("BH");
        owner1.setTelephone("666666666");
        ownerService.save(owner1);

        var tonimPet = new Pet();
        tonimPet.setPetType(dogSaved);
        tonimPet.setOwner(owner1);
        tonimPet.setBirthDate(LocalDate.now().minusDays(15));
        tonimPet.setName("Luna");
        owner1.setPets(new HashSet<>(Arrays.asList(tonimPet)));


        var owner2 = new Owner();
        owner2.setFirstName("Roshcarch");
        owner2.setLastName("Mad");
        owner2.setAddress("Rua Buruburu, 666");
        owner2.setCity("Smallville");
        owner2.setTelephone("982901889183");

        var rosCat = new Pet();
        rosCat.setName("Fiona");
        rosCat.setBirthDate(LocalDate.now().minusDays(23));
        rosCat.setPetType(catSaved);
        rosCat.setOwner(owner2);
        owner2.setPets(new HashSet<>(Arrays.asList(rosCat)));

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        var vet1 = new Vet();
        vet1.setFirstName("Zoy");
        vet1.setLastName("D'Lula");
        vetService.save(vet1);

        var vet2 = new Vet();
        vet2.setFirstName("Luna");
        vet2.setLastName("Chata");
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
