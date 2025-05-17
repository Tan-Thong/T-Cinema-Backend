package com.example.backend.mapper;

import com.example.backend.dto.request.RoomRequest;
import com.example.backend.dto.response.RoomResponse;
import com.example.backend.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    Room toRoom(RoomRequest roomRequest);
    @Mapping(source = "cinema", target = "cinema")
    RoomResponse toRoomResponse(Room room);
    void updateRoom(@MappingTarget Room room, RoomRequest roomRequest);
    List<RoomResponse> toRoomsResponse(List<Room> rooms);
}
