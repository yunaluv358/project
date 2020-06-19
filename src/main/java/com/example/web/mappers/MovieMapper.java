package com.example.web.mappers;

import com.example.web.music.MovieDTO;
import com.example.web.proxy.Pager;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovieMapper {
    public void insertMovie(MovieDTO movie);
    public void updateMovie(MovieDTO movie);
    public void deleteMovie(MovieDTO movie);
    public int count();
    public List<MovieDTO> selectMovies(Pager pager);
    public MovieDTO selectMovie(String movieSeq);
}