package br.com.tonim.petclinic.controllers;

import br.com.tonim.petclinic.model.Owner;
import br.com.tonim.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        var mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model){
        if(owner.getLastName() == null){
            owner.setLastName("");
        }

        var owners = ownerService.findAllByLastNameLike(owner.getLastName());
        if(owners.isEmpty()){
            bindingResult.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if(owners.size() == 1) {
            return "redirect:/owners/" + owners.get(0).getId();
        } else {
            model.addAttribute("selections", owners);
            return "owners/ownersList";
        }
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE;
    }

    @PostMapping("/new")
    public String processCreationForm(Owner owner, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE;
        } else {
            var savedOwner = ownerService.save(owner);
            return "redirect:/owners/"+savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(Owner owner, BindingResult bindingResult, @PathVariable Long ownerId){
        if(bindingResult.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE;
        } else {
            owner.setId(ownerId);
            var ownerSaved = ownerService.save(owner);
            return "redirect:/owners/" + ownerSaved.getId();
        }
    }

}
