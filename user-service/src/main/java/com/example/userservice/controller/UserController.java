package com.example.userservice.controller;
import com.example.userservice.dto.LikeDto;
import com.example.userservice.dto.LoginRequest;
import com.example.userservice.dto.ServerResponseDto;
import com.example.userservice.dto.UserRequest;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("save")
    public ResponseEntity<ServerResponseDto> save(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.save(request));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ServerResponseDto> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getProfile(userId));
    }

    @PostMapping("login")
    public ResponseEntity<ServerResponseDto> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<ServerResponseDto> changePassword(@PathVariable Long id,
                                                            @RequestParam String newPassword){
        return ResponseEntity.ok(userService.changePassword(id, newPassword));
    }

    @PostMapping("/like")
    public ResponseEntity<ServerResponseDto> like(@RequestBody LikeDto likeDto){
        return ResponseEntity.ok(userService.like(likeDto));
    }

    @PostMapping("/remove-favorite")
    public ResponseEntity<ServerResponseDto> removeFavorite(@RequestBody LikeDto likeDto){
        return ResponseEntity.ok(userService.removeFavorite(likeDto));
    }

    @GetMapping("/list-favorite/{userId}")
    public ResponseEntity<ServerResponseDto> getFavorite(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getFavorite(userId));
    }
}
