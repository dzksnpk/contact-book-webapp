package com.AddressBook.spring;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Contact {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	private String name;
	private String surname;
	private String phoneNumber;
	private String email;
	

	public Contact(Long id, String name, String surname, String phoneNumber, String email) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	protected Contact() {}
	
	public Long getId() {
		return id;
	}
	
	public String getFullName() {
		return name + " " + surname;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Contact [name=" + name + ", surname=" + surname + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ "]";
	}
}
