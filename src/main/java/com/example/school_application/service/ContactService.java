package com.example.school_application.service;

import com.example.school_application.model.Contact;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

interface IContactService {
  public List<Contact> getAllContacts();

  public List<Contact> getContact(String mobileno);

  public boolean saveContact(Contact contact);
}

@Component
public class ContactService implements IContactService {
  private List<Contact> contacts = new ArrayList<>();

  @Override
  public List<Contact> getAllContacts() {
    return contacts;
  }

  @Override
  public List<Contact> getContact(String mobileno) {
    List<Contact> contact =
        contacts.stream()
            .filter(c -> c.getMobileno().equals(mobileno))
            .collect(Collectors.toList());
    return contact;
  }

  @Override
  public boolean saveContact(@Valid Contact contact) {
    contacts.add(contact);
    return true;
  }
}
