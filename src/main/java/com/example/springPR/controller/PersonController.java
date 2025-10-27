package com.example.springPR.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springPR.Model.entity.Person;
import com.example.springPR.PersonNotFoundException;
import com.example.springPR.repository.PersonRepo;

import lombok.AllArgsConstructor;



@RestController
// just pour le type de requete recuperer
@RequestMapping("/api/persons")
@AllArgsConstructor
public class PersonController {
    private final PersonRepo personRepo;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPerson() {
        return new ResponseEntity<>(personRepo.findAll(),HttpStatus.OK) ;
    }
    
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person personCreated = personRepo.save(person);
        return new ResponseEntity<>(personCreated, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personRepo.findById(id);
        return person.map(value->new ResponseEntity<>(value,HttpStatus.OK)).orElseThrow(()-> new PersonNotFoundException("person not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id , @RequestBody Person personDetails){
        Optional<Person> person = personRepo.findById(id);
        if(person.isPresent()){
            Person existingPerson =person.get();
            existingPerson.setCity(personDetails.getCity());
            existingPerson.setPhoneNumber(personDetails.getPhoneNumber());  
            
            Person updatePerson = personRepo.save(existingPerson);
            return new ResponseEntity<>(updatePerson, HttpStatus.OK);
        }
        throw new PersonNotFoundException("Personne not found");
    }
    
    @DeleteMapping("/id")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id){
        Optional<Person> person =personRepo.findById(id);
        if(person.isPresent()){
            personRepo.delete(person.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new PersonNotFoundException("Personne not found");
    }
}
