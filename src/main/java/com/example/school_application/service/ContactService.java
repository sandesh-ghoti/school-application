package com.example.school_application.service;

import com.example.school_application.dto.ContactDto;
import com.example.school_application.exception.ResourceNotFoundException;
import com.example.school_application.mapper.ContactMapper;
import com.example.school_application.model.Contact;
import com.example.school_application.repository.ContactRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

interface IContactService {
  public List<ContactDto> getAllContacts();

  public ContactDto getContact(Long id);

  public boolean saveContact(ContactDto contactDto);
}

@Service
public class ContactService implements IContactService {
  private final ContactRepository contactRepository;

  public ContactService(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  @Override
  public List<ContactDto> getAllContacts() {
    List<Contact> contacts = contactRepository.findAll();

    return contacts.stream().map(ContactMapper::toContactDto).toList();
  }

  @Override
  public ContactDto getContact(Long id) {
    Optional<Contact> contact = contactRepository.findById(id);

    if (contact.isEmpty()) {
      throw new ResourceNotFoundException("Contact", "id", id.toString());
    }
    return ContactMapper.toContactDto(contact.get());
  }

  @Override
  public boolean saveContact(@Valid ContactDto contactDto) {

    contactRepository.save(ContactMapper.toContact(contactDto));
    return true;
  }
}
