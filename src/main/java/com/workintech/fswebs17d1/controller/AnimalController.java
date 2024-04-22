package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {
    private final Map<Integer, Animal> animals;

    public AnimalController(Map<Integer, Animal> animals) {
        this.animals = animals;
    }
    // Tüm animal mapinin value değerlerini bir List olarak döndür
    @GetMapping("/workintech/animal")
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals.values());
    }
    @GetMapping("/hi")
    public String sayHi() {
        return "Hello Spring";
    }
    @GetMapping("/workintech/animal/{id}")
    public ResponseEntity<?> getAnimalById(@PathVariable int id) {
        // İlgili ID'deki hayvanı bul ve döndür
        Animal animal = animals.get(id);
        if (animal != null) {
            return ResponseEntity.ok(animal);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal not found with id: " + id);
        }
    }
    @PostMapping("/workintech/animal")
    public ResponseEntity<String> addAnimal(@RequestParam int id, @RequestParam String name) {
        // animals map'ine yeni hayvanı ekle
        Animal newAnimal = new Animal(id, name);
        animals.put(id, newAnimal);
        return ResponseEntity.status(HttpStatus.CREATED).body("Animal added successfully with id: " + id);
    }
    @PutMapping("/workintech/animal/{id}")
    public ResponseEntity<String> updateAnimal(@PathVariable int id, @RequestBody Animal updatedAnimal) {
        // İlgili ID'deki hayvanı güncelle
        if (animals.containsKey(id)) {
            Animal existingAnimal = animals.get(id);
            existingAnimal.setName(updatedAnimal.getName());
            return ResponseEntity.ok("Animal updated successfully with id: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal not found with id: " + id);
        }
    }
    @DeleteMapping("/workintech/animal/{id}")
    public Animal delete(@PathVariable int id){
        Animal animal=animals.get(id);
        animals.remove(id,animal);
        return animal;
    }

}
