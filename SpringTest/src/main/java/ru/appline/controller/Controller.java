package ru.appline.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.Logic.Pet;
import ru.appline.Logic.PetModel;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newid = new AtomicInteger(1);


    public final int first = newid.get();
    public String text;

    @GetMapping(value = "/getAll",produces ="application/json" )
    public Map<Integer,Pet> getAll(){
        return petModel.getAll();
    }

    @GetMapping(value = "/getPet",consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String,Integer> id){
        return petModel.getFromList(id.get("id"));
    }
    @PostMapping(value = "/createPet",consumes = "application/json",produces ="application/json")
    public String createPet(@RequestBody Pet pet) {
        petModel.add(pet,newid.getAndIncrement());

            return text ="Ваш питомец создан";
    }
    @DeleteMapping(value = "/deletePet",consumes = "application/json",produces = "application/json")
    public String delPet(@RequestBody Map<String,Integer> id){
        petModel.delete(id.get("id"));
        return text = "Питомец мертв";
    }
    @PutMapping(value = "/changePet",consumes ="application/json",produces = "application/json")
    public String changePet(@RequestBody Map<String,Integer>id,Pet pet){
        petModel.change(pet,id.get("id"));

        return text = "Питомец поменялся";
    }
}

