package com.AddressBook.spring;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController implements ErrorController {

	@Autowired
	private ContactRepository contactRep;

	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	public String error() {
		return "Error handling";
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Contact contact) {
		return "index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String contactForm(@Valid Contact contact, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "index";
		}

		model.addAttribute("contacts", contactRep.findAll());
		return "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String result(@ModelAttribute Contact contact) {

		contactRep.save(
				new Contact(contact.getName(), contact.getSurname(), contact.getEmail(), contact.getPhoneNumber()));

		return "result";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String showAllContacts(Contact contact, Model model) {
		model.addAttribute("contacts", contactRep.findAll());
		return "list";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchContact(@RequestParam(value = "search", required = false) String surname, Model model) {
		
		model.addAttribute("search", contactRep.findBySurname(surname));
		return "search";
	}
	@RequestMapping(value= "/edit", method = RequestMethod.GET)
	public String editForm(Contact contact,  @RequestParam(value ="id", required = false) Long id, Model model) {
		
		return "edit";
	}
	@RequestMapping(value= "/edit", method = RequestMethod.POST)
	public String editContact(Contact contact, @RequestParam(value ="id", required = false) Long id, Model model) {
		
		model.addAttribute("edit", contactRep.findOne(id));
		contactRep.save(contact);
		
		return "result";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteForm(Contact contact,@RequestParam(value = "surname", required = false) String surname, Model model) {
		
		return "delete";
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteContact(Contact contact,@RequestParam (value = "surname", required = false) String surname, Model model) {
		
		model.addAttribute("delete", contactRep.removeBySurname(surname));
		return "redirect:/";
	}
}
