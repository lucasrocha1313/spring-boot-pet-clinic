package br.com.tonim.petclinic.services.map;

import br.com.tonim.petclinic.model.Speciality;
import br.com.tonim.petclinic.services.SpecialitiesService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialitiesService {
    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }
}
