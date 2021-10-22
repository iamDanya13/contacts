package com.dpavyukov.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    public ContactRepository repository;

    @GetMapping("/{id}")
    public Contact getContact(@PathVariable("id") long id) {
        return repository.findById(id).get();
    }
    @DeleteMapping("/{id}")
    void deleteContact(@PathVariable Long id) {
        repository.deleteById(id);
    }
    @PostMapping("/contacts")
    Contact newContact(@RequestBody Contact newContact){
       return repository.save(newContact);
    }


    @GetMapping
    public List<Contact> getContacts(@RequestParam(value = "name", required = false) String name) {
        if (name == null) {
            return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
        } else {
            return StreamSupport.stream(repository.findByName("%" + name + "%").spliterator(), false).collect(Collectors.toList());
        }
    }
}