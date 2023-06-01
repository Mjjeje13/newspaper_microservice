package com.example.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LikeDto {
    private Long userId;
    private Long newsId;
}
