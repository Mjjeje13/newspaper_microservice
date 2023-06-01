package com.example.newspaperservice.service;

import com.example.newspaperservice.dto.ServerResponseDto;
import com.example.newspaperservice.entity.NewspaperEntity;
import com.example.newspaperservice.repository.NewspaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewspaperService {
    private final NewspaperRepository newspaperRepository;

    public List<NewspaperEntity> getAllByType(Integer type) {
        return newspaperRepository.getAllByType(type);
    }

    public List<NewspaperEntity> getListByCategoryIdAndType(Long categoryId, Integer type) {
        return newspaperRepository.findByCategoryIdAndType(categoryId, type);
    }

    public List<NewspaperEntity> getListFavorite(List<Long> listNewsId) {
        return newspaperRepository.findAllById(listNewsId);
    }

    public ServerResponseDto detail(Long id) {
        Optional<NewspaperEntity> newsOpt = newspaperRepository.findById(id);
        if (newsOpt.isEmpty()) {
            return ServerResponseDto.ERROR;
        }
        return ServerResponseDto.success(newsOpt.get());
    }
}
