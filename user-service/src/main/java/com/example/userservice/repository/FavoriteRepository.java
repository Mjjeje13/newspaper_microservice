package com.example.userservice.repository;

import com.example.userservice.entity.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {

    List<FavoriteEntity> findByUserId(Long userId);

    FavoriteEntity findByUserIdAndNewsId(Long userId, Long newsId);
}
