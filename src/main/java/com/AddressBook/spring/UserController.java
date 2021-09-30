package com.AddressBook.spring;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController implements ErrorController {

	private final ContactRepository contactRep;

	@Autowired
	private UserController(ContactRepository contactRep) {
		this.contactRep = contactRep;
	}

	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	public String error() {
		return "Error handling";
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

	@GetMapping("/")
	public String index(Contact contact) {
		return "index";
	}

	@GetMapping("/add")
	public String contactForm(@Valid Contact contact, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "index";
		}

		model.addAttribute("contacts", contactRep.findAll());
		return "add";
	}

	@PostMapping("/add")
	public String result(@ModelAttribute Contact contact) {

		contactRep.save(new Contact(contact.getId(), contact.getName(), contact.getSurname(), contact.getEmail(),
				contact.getPhoneNumber()));

		return "result";
	}

	@GetMapping("/list")
	public String showAllContacts(Model model) {
		model.addAttribute("contacts", contactRep.findAll());
		return "list";
	}

	@GetMapping("/search")
	public String searchContact(@RequestParam(value = "search", required = false) String surname, Model model) {

		model.addAttribute("search", contactRep.findBySurname(surname));
		return "search";
	}

	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable("id") Long id, Contact contact, Model model) {
		contact.setId(id);
		return "edit";
	}

	@PostMapping("/edit/{id}")
	public String editContact(@PathVariable("id") Long id, Contact contact, Model model) {
		contactRep.findById(id);
		model.addAttribute("contact", contact);
		contactRep.save(contact);

		return "result";
	}

	@GetMapping("/delete")
	public String deleteForm(Contact contact, @RequestParam(value = "surname", required = false) String surname,
			Model model) {

		return "delete";
	}

	@PostMapping("/delete")
	public String deleteContact(Contact contact, @RequestParam(value = "surname", required = false) String surname,
			Model model) {

		model.addAttribute("delete", contactRep.removeBySurname(surname));
		return "redirect:/";
	}
}
