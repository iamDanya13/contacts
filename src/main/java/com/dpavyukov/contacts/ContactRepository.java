package com.dpavyukov.contacts;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    @Query("from Contact where name like ?1")
    List<Contact> findByName(String name);
}
