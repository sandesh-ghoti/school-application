package com.example.school_application.controller;

import com.example.school_application.model.Contact;
import com.example.school_application.service.ContactService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/contact")
@RestController
public class ContactController {
  private final ContactService contactService;

  public ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  @GetMapping("/{filter}")
  public ResponseEntity<List<Contact>> getContacts(@Valid @PathVariable String filter) {
    System.out.println("filter" + filter);
    if (filter.equals("all")) {
      return ResponseEntity.ok().body(contactService.getAllContacts());
    }
    return ResponseEntity.ok().body(contactService.getContact(filter));
  }

  @PostMapping(path = "")
  public ResponseEntity<Boolean> saveContact(@Valid @RequestBody Contact contact) {
    boolean isSaved = contactService.saveContact(contact);
    return ResponseEntity.ok().body(isSaved);
  }
}
