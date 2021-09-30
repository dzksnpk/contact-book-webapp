package com.AddressBook.spring;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Contact {
	
	
	@Id
    	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", surname=" + surname + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ "]";
	}
}
