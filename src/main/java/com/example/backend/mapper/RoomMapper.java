package com.example.backend.mapper;

import com.example.backend.dto.request.RoomRequest;
import com.example.backend.dto.response.RoomResponse;
import com.example.backend.dto.response.UserResponse;
import com.example.backend.entity.Room;
import com.example.backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    Room toRoom(RoomRequest roomRequest);
    RoomResponse toRoomResponse(Room room);
    void updateRoom(@MappingTarget Room room, RoomRequest roomRequest);
    List<RoomResponse> toRoomsResponse(List<Room> rooms);
}
