package com.example.backend.service;

import com.example.backend.dto.request.RoomRequest;
import com.example.backend.dto.response.CinemaResponse;
import com.example.backend.dto.response.RoomResponse;
import com.example.backend.entity.Cinema;
import com.example.backend.entity.Room;
import com.example.backend.mapper.RoomMapper;
import com.example.backend.repository.CinemaRepository;
import com.example.backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private RoomMapper roomMapper;

    public List<RoomResponse> getRooms() {
        return roomMapper.toRoomsResponse(roomRepository.findAll());
    }

    public RoomResponse getRoom(int roomId) {
        return roomMapper.toRoomResponse(roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Phòng không tồn tại!")));
    }

    public RoomResponse createRoom(RoomRequest roomRequest) {
        Room room = roomMapper.toRoom(roomRequest);
        Cinema cinema = cinemaRepository.findById(roomRequest.getCinemaId()).orElseThrow(() -> new RuntimeException("Rạp không tồn tại!"));
        room.setCinema(cinema);
        return roomMapper.toRoomResponse(roomRepository.save(room));
    }

    public void deleteRoom(int roomId) {
        roomRepository.delete(roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Phòng không tồn tại!")));
    }

    public RoomResponse updateRoom(int roomId, RoomRequest roomRequest) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Phòng không tồn tại!"));
        roomMapper.updateRoom(room, roomRequest);
        return roomMapper.toRoomResponse(roomRepository.save(room));
    }
}
