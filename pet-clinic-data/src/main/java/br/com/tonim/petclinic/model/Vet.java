package br.com.tonim.petclinic.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person{
    private Set<Speciality> specialities;

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        if(this.specialities == null)
            this.specialities = new HashSet<>();
        this.specialities = specialities;
    }
}
