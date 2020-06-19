package com.example.web.proxy;

import com.example.web.music.Music;
import com.example.web.music.MusicRepository;
import com.example.web.music.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
@RestController
public class ProxyController {
    @Autowired Box<Object> box;
    @Autowired Crawler crawler;
    @Autowired FileUploader uploader;
    @Autowired Proxy pxy;
    @Autowired MusicRepository musicRepository;
    @Autowired MovieRepository movieRepository;
    //@Autowired FileUploader loader;

    @GetMapping("naver-movie/{searchWord}")
    public void naverMovie(@PathVariable String searchWord){
        pxy.print("넘어온 거 : "+searchWord);
        crawler.naverMovie();
    }

    /*public HashMap<String, Object> movie(@PathVariable String searchWord){
        pxy.print("넘어온 키워드:"+ searchWord);
        box.clear();
        if(movieRepository.count() == 0) crawler.naverMovie();
        List<Music> list = musicRepository.findAll();
        pxy.print("count : "+list.size());
        box.put("list",list);
        box.put("count", list.size());
        return box.get();
    }*/

    @PostMapping("/bugsmusic")
    public HashMap<String, Object> bugsmusic(@RequestBody String searchWord){
        pxy.print("넘어온 키워드: "+searchWord);
        box.clear();
        if(musicRepository.count() == 0) crawler.bugsMusic();
        List<Music> list = musicRepository.findAll();
        pxy.print("count : "+list.size());
        box.put("list",list);
        box.put("count", list.size());
        return box.get();
    }

    @GetMapping("/soccer/{searchWord}")
    public HashMap<String,String> soccer(@PathVariable String searchWord){
        pxy.print("넘어온 키워드:"+ searchWord);
        uploader.upload();
        return null;
    }


}