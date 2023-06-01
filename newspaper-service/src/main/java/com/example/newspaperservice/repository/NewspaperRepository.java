package com.example.newspaperservice.repository;

import com.example.newspaperservice.entity.NewspaperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewspaperRepository extends JpaRepository<NewspaperEntity, Long> {

    @Query(value = "select * from newspaper n " +
            "where n.type = ?1 " +
            "order by n.create_time desc", nativeQuery = true)
    List<NewspaperEntity> getAllByType(Integer type);
    List<NewspaperEntity> findByCategoryIdAndType(Long categoryId, Integer type);
}
