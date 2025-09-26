package com.example.turukiazbeach.service.impl;

import com.example.turukiazbeach.model.Room;
import com.example.turukiazbeach.repository.RoomRepository;
import com.example.turukiazbeach.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Long id, Room room) {
        Room existing = getRoomById(id);
        existing.setName(room.getName());
        existing.setDescription(room.getDescription());
        existing.setPricePerNight(room.getPricePerNight());
        existing.setCapacity(room.getCapacity());
        existing.setImageUrl(room.getImageUrl());
        return roomRepository.save(existing);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
