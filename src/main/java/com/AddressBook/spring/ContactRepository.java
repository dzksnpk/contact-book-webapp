package com.AddressBook.spring;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface ContactRepository extends CrudRepository<Contact, Long>{
	
	@Transactional
	List<Contact> findBySurname(String surname);

	@Transactional
	List<Contact> findById(Long id);
	
    @Transactional
	List<Contact> removeBySurname(String surname);
    
}
