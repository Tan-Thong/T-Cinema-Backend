package com.example.backend.controller;

import com.example.backend.dto.request.RoomRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.RoomResponse;
import com.example.backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping()
    public ApiResponse<List<RoomResponse>> getRooms() {
        ApiResponse<List<RoomResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(roomService.getRooms());
        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<RoomResponse> getRooms(@PathVariable("id") int roomId) {
        ApiResponse<RoomResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(roomService.getRoom(roomId));
        return apiResponse;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ApiResponse<RoomResponse> createRoom(@RequestBody RoomRequest roomRequest) {
        ApiResponse<RoomResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(roomService.createRoom(roomRequest));
        return apiResponse;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ApiResponse<RoomResponse> updateRoom(@PathVariable("id") int roomId,@RequestBody RoomRequest roomRequest) {
        ApiResponse<RoomResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(roomService.updateRoom(roomId, roomRequest));
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ApiResponse<RoomResponse> deleteCinema(@PathVariable("id") int roomId) {
        ApiResponse<RoomResponse> apiResponse = new ApiResponse<>();
        roomService.deleteRoom(roomId);
        return apiResponse;
    }
}
