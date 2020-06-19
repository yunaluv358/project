package com.example.web.music;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieSeq;
    @Column(length = 4)
    private String rank;
    @Column(length = 100)
    private String title;
    @Column(length = 10)
    private String rankDate;


    @Builder
    public Movie(String rank, String title, String rankDate) {
        this.rank = rank;
        this.title = title;
        this.rankDate = rankDate;
    }

}