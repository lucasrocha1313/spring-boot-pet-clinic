package br.com.tonim.petclinic.formatters;

import br.com.tonim.petclinic.model.PetType;
import br.com.tonim.petclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {
    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        var findPetTypes = petTypeService.findAll();

        for(var type: findPetTypes) {
            if(type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("Type not found: " + text, 0);
    }
}
