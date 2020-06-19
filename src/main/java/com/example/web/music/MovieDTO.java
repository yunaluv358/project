package com.example.web.music;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data
@Component
public class MovieDTO {
    private String movieSeq, rank, title, rankDate;

}