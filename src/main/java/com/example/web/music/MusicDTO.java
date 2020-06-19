package com.example.web.music;

import javax.persistence.Column;

public class MusicDTO {
    private Long musicSeq;
    @Column(length = 4, nullable = false)
    private String seq;
    @Column(length = 200, nullable = false)
    private String title;
    @Column(length = 200, nullable = false)
    private String artists;
    @Column(length = 500, nullable = false)
    private String thumbnail;

}
