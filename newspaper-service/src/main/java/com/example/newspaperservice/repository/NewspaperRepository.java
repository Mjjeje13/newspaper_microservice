package com.example.newspaperservice.repository;

import com.example.newspaperservice.entity.NewspaperEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewspaperRepository extends JpaRepository<NewspaperEntity, Long> {
    List<NewspaperEntity> findByCategoryIdAndType(Long categoryId, Integer type);
}
