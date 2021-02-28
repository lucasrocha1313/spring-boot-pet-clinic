package br.com.tonim.petclinic.bootstrap;

import br.com.tonim.petclinic.model.Owner;
import br.com.tonim.petclinic.model.Vet;
import br.com.tonim.petclinic.services.OwnerService;
import br.com.tonim.petclinic.services.VetService;
import br.com.tonim.petclinic.services.map.OwnerServiceMap;
import br.com.tonim.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        var owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Tonim");
        owner1.setLastName("Tunado");
        ownerService.save(owner1);

        var owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Roshcarch");
        owner2.setLastName("Mad");
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        var vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Zoy");
        vet1.setLastName("D'Lula");
        vetService.save(vet1);

        var vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Luna");
        vet2.setLastName("Chata");
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
