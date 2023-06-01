package com.example.newspaperservice.controller;

import com.example.newspaperservice.dto.ServerResponseDto;
import com.example.newspaperservice.entity.NewspaperEntity;
import com.example.newspaperservice.service.NewspaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewspaperController {
    private final NewspaperService newspaperService;

    @GetMapping("/list-all/{type}")
    public ResponseEntity<List<NewspaperEntity>> getAllByType(@PathVariable Integer type) {
        return ResponseEntity.ok(newspaperService.getAllByType(type));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ServerResponseDto> detail(@PathVariable Long id) {
        return ResponseEntity.ok(newspaperService.detail(id));
    }

    @GetMapping("/list/{categoryId}/{type}")
    public ResponseEntity<List<NewspaperEntity>> getListByCategoryIdAndType(@PathVariable Long categoryId,
                                                                            @PathVariable Integer type) {
        return ResponseEntity.ok(newspaperService.getListByCategoryIdAndType(categoryId, type));
    }

    @GetMapping("/list-favorite")
    public ResponseEntity<List<NewspaperEntity>> getListFavorite(@RequestParam List<Long> listNewsId) {
        return ResponseEntity.ok(newspaperService.getListFavorite(listNewsId));
    }
}
