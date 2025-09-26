package com.example.turukiazbeach.controller;

import com.example.turukiazbeach.model.ContactMessage;
import com.example.turukiazbeach.service.ContactMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class ContactMessageController {

    private final ContactMessageService service;
    public ContactMessageController(ContactMessageService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<ContactMessage> save(@RequestBody ContactMessage m) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(m));
    }

    @GetMapping
    public ResponseEntity<List<ContactMessage>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactMessage> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactMessage> update(@PathVariable Long id, @RequestBody ContactMessage m) {
        return ResponseEntity.ok(service.update(id, m));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
