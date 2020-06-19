package com.example.web.soccer;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="schedule")

public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheNo;
    @NotNull
    @NotNull @Column(length = 10) private String gubun;
    @Column(length = 10) private String hometeamId;
    @Column(length = 10) private String awayteamId;
    @Column(length = 10) private String homeScore;
    @Column(length = 10) private String awayScore;
    @Builder
    public Schedule(String gubun, String hometeamId, String awayteamId,
                    String homeScore, String awayScore){
        this.gubun=gubun;
        this.hometeamId=hometeamId;
        this.awayteamId=awayteamId;
        this.homeScore=homeScore;
        this.awayScore=awayScore;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id") @NotNull
    private Stadium stadium;
}