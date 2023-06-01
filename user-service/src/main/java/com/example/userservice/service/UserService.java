package com.example.userservice.service;

import com.example.userservice.dto.*;
import com.example.userservice.entity.FavoriteEntity;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.repository.FavoriteRepository;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final String NEWSPAPER_URL  = "http://localhost:8000/newspaper";

    public ServerResponseDto getProfile(Long userId) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ServerResponseDto.ERROR;
        }
        return ServerResponseDto.success(convertFromEntity(userOpt.get()));
    }

    public ServerResponseDto save(UserRequest request) {
        Long userId = request.getId();
        UserEntity user;
        if (userId == null) {
            user = convertFromRequest(request);
        } else {
            Optional<UserEntity> userOpt = userRepository.findById(userId);
            if (userOpt.isEmpty()) {
                return ServerResponseDto.ERROR;
            }
            user = userOpt.get();
            user.setName(request.getName());
            user.setPassword(request.getPassword());
        }
        userRepository.save(user);
        return ServerResponseDto.SUCCESS;
    }

    public ServerResponseDto login(LoginRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            return ServerResponseDto.ERROR;
        }
        if (request.getPassword().equals(user.getPassword())) {
            return ServerResponseDto.SUCCESS;
        }
        return ServerResponseDto.with(ResponseCase.PASSWORD_NOT_RIGHT);
    }

    private UserResponse convertFromEntity(UserEntity entity) {
        UserResponse response = new UserResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        response.setUsername(entity.getUsername());
        response.setPassword(entity.getPassword());

        return response;
    }

    private UserEntity convertFromRequest(UserRequest request) {
        UserEntity entity = new UserEntity();
        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setUsername(request.getUsername());
        entity.setPassword(request.getPassword());

        return entity;
    }

    public ServerResponseDto changePassword(Long id, String newPassword) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return ServerResponseDto.ERROR;
        }
        UserEntity user = userOpt.get();
        user.setPassword(newPassword);
        userRepository.save(user);
        return ServerResponseDto.SUCCESS;
    }

    public ServerResponseDto like(LikeDto likeDto) {
        FavoriteEntity favorite = new FavoriteEntity();
        favorite.setUserId(likeDto.getUserId());
        favorite.setNewsId(likeDto.getNewsId());
        favoriteRepository.save(favorite);
        return ServerResponseDto.SUCCESS;
    }

    public ServerResponseDto removeFavorite(LikeDto likeDto) {
        Long userId = likeDto.getUserId();
        Long newsId = likeDto.getNewsId();
        FavoriteEntity favorite = favoriteRepository.findByUserIdAndNewsId(userId, newsId);
        favoriteRepository.deleteById(favorite.getId());
        return ServerResponseDto.SUCCESS;
    }

    public ServerResponseDto getFavorite(Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        List<Long> listNewsId = new ArrayList<>();
        List<FavoriteEntity> listFavorite = favoriteRepository.findByUserId(userId);
        for (FavoriteEntity favorite : listFavorite) {
            listNewsId.add(favorite.getNewsId());
        }
        URI url = UriComponentsBuilder.fromHttpUrl(NEWSPAPER_URL + "/list-favorite")
                .queryParam("listNewsId", listNewsId)
                .build()
                .toUri();
        ResponseEntity<NewspaperForFavorite[]> response = restTemplate.exchange(url, HttpMethod.GET, null, NewspaperForFavorite[].class);
        NewspaperForFavorite[] result = response.getBody();
        return ServerResponseDto.success(result);
    }

}
