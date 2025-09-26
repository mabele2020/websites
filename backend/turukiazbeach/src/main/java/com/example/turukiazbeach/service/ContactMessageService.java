package com.example.turukiazbeach.service;

import com.example.turukiazbeach.model.ContactMessage;

import java.util.List;

public interface ContactMessageService {
    ContactMessage save(ContactMessage m);
    List<ContactMessage> findAll();
    ContactMessage findById(Long id);
    ContactMessage update(Long id, ContactMessage m);
    void delete(Long id);
}
