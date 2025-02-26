package com.example.school_application.mapper;

import com.example.school_application.dto.ContactDto;
import com.example.school_application.model.Contact;

public class ContactMapper {
  public static Contact toContact(ContactDto contactDto) {
    Contact contact = new Contact();
    contact.setName(contactDto.getName());
    contact.setMobileno(contactDto.getMobileno());
    contact.setEmail(contactDto.getEmail());
    contact.setSubject(contactDto.getSubject());
    contact.setMessage(contactDto.getMessage());
    return contact;
  }

  public static ContactDto toContactDto(Contact contact) {
    ContactDto contactDto = new ContactDto();
    contactDto.setContactId(contact.getContactId());
    contactDto.setName(contact.getName());
    contactDto.setMobileno(contact.getMobileno());
    contactDto.setEmail(contact.getEmail());
    contactDto.setSubject(contact.getSubject());
    contactDto.setMessage(contact.getMessage());
    return contactDto;
  }
}
