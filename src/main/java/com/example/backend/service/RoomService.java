package com.example.backend.service;

import com.example.backend.dto.request.RoomRequest;
import com.example.backend.dto.response.CinemaResponse;
import com.example.backend.dto.response.RoomResponse;
import com.example.backend.entity.Cinema;
import com.example.backend.entity.Room;
import com.example.backend.entity.Seat;
import com.example.backend.enums.SeatStatus;
import com.example.backend.enums.SeatType;
import com.example.backend.mapper.RoomMapper;
import com.example.backend.repository.CinemaRepository;
import com.example.backend.repository.RoomRepository;
import com.example.backend.repository.SeatRepository;
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
    private SeatRepository seatRepository;

    @Autowired
    private RoomMapper roomMapper;

    public List<RoomResponse> getRooms() {
        return roomMapper.toRoomsResponse(roomRepository.findAll());
    }

    public List<RoomResponse> getRoomsByCinemaId(int cinemaId) {
        return roomMapper.toRoomsResponse(roomRepository.findByCinema_CinemaId(cinemaId));
    }

    public RoomResponse getRoom(int roomId) {
        return roomMapper.toRoomResponse(roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Phòng không tồn tại!")));
    }

    public RoomResponse createRoom(RoomRequest roomRequest) {
        Room room = roomMapper.toRoom(roomRequest);
        Cinema cinema = cinemaRepository.findById(roomRequest.getCinemaId()).orElseThrow(() -> new RuntimeException("Rạp không tồn tại!"));
        room.setCinema(cinema);
        Room result = roomRepository.save(room);
        for(int i = 0; i < room.getRow(); i++) {
            for(int j = 0; j < room.getColumn(); j++) {
                char row = (char) ('A' + i);
                Seat seat = new Seat();
                seat.setSeatRow(row);
                seat.setSeatColumn(j+1);
                seat.setStatus(SeatStatus.AVAILABLE);
                seat.setRoom(room);
                if(i < 3) {
                    seat.setSeatType(SeatType.STANDARD);
                } else if(i < 7) {
                    seat.setSeatType(SeatType.VIP);
                } else {
                    seat.setSeatType(SeatType.COUPLE);
                }
                seatRepository.save(seat);
            }
        }
        return roomMapper.toRoomResponse(result);
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
