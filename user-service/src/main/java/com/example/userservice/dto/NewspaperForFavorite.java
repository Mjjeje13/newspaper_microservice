package com.example.userservice.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewspaperForFavorite {
    private Long id;
    private String title;
    private String thumbnail;
    private String content;
    private Long categoryId;
    private String author;
    private Integer type;
    private Date createTime;
}
