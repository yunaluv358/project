package com.example.web.music;

import com.example.web.mappers.MovieMapper;
import com.example.web.proxy.Crawler;
import com.example.web.proxy.ISupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MovieInitializer implements ApplicationRunner {
    @Autowired
    MovieMapper movieMapper;
    @Autowired Crawler crawler;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ISupplier<Integer> s =  movieMapper::count;
        if(s.get() == 0) crawler.naverMovie();
    }
}