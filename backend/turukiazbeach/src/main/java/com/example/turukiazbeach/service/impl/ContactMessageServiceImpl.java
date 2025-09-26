package com.example.turukiazbeach.service.impl;

import com.example.turukiazbeach.model.ContactMessage;
import com.example.turukiazbeach.repository.ContactMessageRepository;
import com.example.turukiazbeach.service.ContactMessageService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactMessageServiceImpl implements ContactMessageService {

    private final ContactMessageRepository repo;

    public ContactMessageServiceImpl(ContactMessageRepository repo) {
        this.repo = repo;
    }

    @Override
    public ContactMessage save(ContactMessage message) {
        return repo.save(message);
    }

    @Override
    public List<ContactMessage> findAll() {
        return repo.findAll();
    }

    @Override
    public ContactMessage findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact message with ID " + id + " not found"));
    }

    @Override
    public ContactMessage update(Long id, ContactMessage updatedMessage) {
        ContactMessage existingMessage = findById(id);

        existingMessage.setFullName(updatedMessage.getFullName());
        existingMessage.setEmail(updatedMessage.getEmail());
        existingMessage.setSubject(updatedMessage.getSubject());
        existingMessage.setMessage(updatedMessage.getMessage());
        existingMessage.setSentAt(updatedMessage.getSentAt());

        return repo.save(existingMessage);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
