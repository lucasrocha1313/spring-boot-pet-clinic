package br.com.tonim.petclinic.bootstrap;

import br.com.tonim.petclinic.model.*;
import br.com.tonim.petclinic.services.OwnerService;
import br.com.tonim.petclinic.services.PetTypeService;
import br.com.tonim.petclinic.services.SpecialityService;
import br.com.tonim.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(petTypeService.findAll().size() == 0)
            loadData();
    }

    private void loadData() {
        var dog = new PetType();
        dog.setName("dog");
        var dogSaved = petTypeService.save(dog);

        var cat = new PetType();
        dog.setName("cat");
        var catSaved = petTypeService.save(cat);

        var radiology = new Speciality();
        radiology.setDescription("Radiology");
        var savedRadiology = specialityService.save(radiology);

        var surgery = new Speciality();
        surgery.setDescription("Surgery");
        var savedSurgery = specialityService.save(surgery);

        var dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        var savedDentistry = specialityService.save(dentistry);

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
        vet1.setSpecialities(new HashSet<>(Arrays.asList(radiology)));
        vetService.save(vet1);

        var vet2 = new Vet();
        vet2.setFirstName("Luna");
        vet2.setLastName("Chata");
        vet2.setSpecialities(new HashSet<>(Arrays.asList(surgery)));
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
