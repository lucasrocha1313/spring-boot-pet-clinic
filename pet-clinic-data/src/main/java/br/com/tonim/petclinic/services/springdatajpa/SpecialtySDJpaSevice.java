package br.com.tonim.petclinic.services.springdatajpa;

import br.com.tonim.petclinic.model.Speciality;
import br.com.tonim.petclinic.repositories.SpecialityRepository;
import br.com.tonim.petclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialtySDJpaSevice implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialtySDJpaSevice(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Speciality findById(Long id) {
        return specialityRepository.findById(id).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public Set<Speciality> findAll() {
        var specialties = new HashSet<Speciality>();
        specialityRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        specialityRepository.deleteById(id);
    }
}
