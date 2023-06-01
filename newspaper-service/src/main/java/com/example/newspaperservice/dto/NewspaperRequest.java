package com.example.newspaperservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class NewspaperRequest {
    private Long id;

    private String title;
    private String thumbnail;
    private Long categoryId;
    private Date createTime;
}
