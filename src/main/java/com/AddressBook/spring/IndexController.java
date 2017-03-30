package com.AddressBook.spring;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController implements ErrorController {

	private final ContactRepository contactRep;

	@Autowired
	private IndexController(ContactRepository contactRep) {
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
	public String showAllContacts(Contact contact, Model model) {
		model.addAttribute("contacts", contactRep.findAll());
		return "list";
	}

	@GetMapping("/search")
	public String searchContact(@RequestParam(value = "search", required = false) String surname, Model model) {

		model.addAttribute("search", contactRep.findBySurname(surname));
		return "search";
	}

	@GetMapping("/edit")
	public String editForm(Contact contact, @RequestParam(value = "id", required = false) Long id, Model model) {

		return "edit";
	}

	@PostMapping("/edit")
	public String editContact(Contact contact, @RequestParam(value = "id", required = false) Long id, Model model) {

		model.addAttribute("edit", contactRep.findOne(id));
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
