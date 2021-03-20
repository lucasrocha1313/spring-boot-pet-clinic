package br.com.tonim.petclinic.model;

public class Vet extends Person{
    private Speciality speciality;

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}
