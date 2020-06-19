package com.example.web.music;

import com.example.web.mappers.MovieMapper;
import com.example.web.proxy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;  
import java.util.function.Function;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired Pager pager;
    @Autowired MovieMapper movieMapper;
    @Autowired Proxy pxy;
    @Autowired Box<Object> box;
    @GetMapping("/{searchWord}/{pageNumber}")
    public Map<?,?> list(@PathVariable("pageNumber") String pageNumber,
                         @PathVariable("searchWord") String searchWord){

        if(searchWord.equals("null")){
            pxy.print("검색어가 없음");
            pager.setSearchWord("");
        }else{
            pxy.print("검색어가 "+searchWord);
            pager.setSearchWord(searchWord);
        }
        pxy.print("넘어온 페이지번호: "+pageNumber);
        pager.setPageNow(pxy.integer(pageNumber));
        pager.setBlockSize(5);
        pager.setPageSize(5);
        pager.paging();
        Function<Pager, List<MovieDTO>> f = p ->  movieMapper.selectMovies(p);
        List<MovieDTO> list = f.apply(pager);
        pxy.print("***********");
        for(MovieDTO m : list){
            pxy.print(m.toString());
        }
        box.clear();
        box.put("pager", pager);
        box.put("list", list);
        return box.get();
    }
    @GetMapping("/{searchWord}")
    public MovieDTO detail(@PathVariable("searchWord") String searchWord){
        return movieMapper.selectMovie(searchWord);
    }

}